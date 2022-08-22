package com.cutm.erp.grievance.service;

import com.cutm.erp.grievance.Exception.GrievanceException;
import com.cutm.erp.grievance.dto.GrievanceDto;
import com.cutm.erp.grievance.entity.*;
import com.cutm.erp.grievance.repository.AssigneeRepository;
import com.cutm.erp.grievance.repository.GrievanceActionRepository;
import com.cutm.erp.grievance.repository.GrievanceRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class GrievanceService {
    Logger logger = Logger.getLogger(GrievanceService.class.getName());


    private GrievanceRepository grievanceRepository;
    private GrievanceActionRepository grievanceActionRepository;
    private AssigneeRepository assigneeRepository;
    private CategoryService categoryService;
    private LocationService locationService;
    private UserService userService;

    public GrievanceService(GrievanceRepository grievanceRepository,
                            GrievanceActionRepository grievanceActionRepository,
                            AssigneeRepository assigneeRepository,
                            CategoryService categoryService,
                            LocationService locationService,
                            UserService userService)
    {
        this.grievanceRepository = grievanceRepository;
        this.grievanceActionRepository = grievanceActionRepository;
        this.assigneeRepository = assigneeRepository;
        this.categoryService = categoryService;
        this.locationService = locationService;
        this.userService = userService;
    }

    public List<GrievanceDto> grievanceList()throws GrievanceException {
        List<Grievance> grievances = grievanceRepository.findAll();
        List dtoList = new ArrayList();
        for(Grievance grievance : grievances)
        {
            dtoList.add(convertGrievanceToDto(grievance));
        }
        addActions(dtoList);
       return dtoList;
    }



    private void addActions(List<GrievanceDto> entities) {
        for (GrievanceDto entity : entities) {
            if (entity.getGrievanceStatus() != GrievanceStatus.COMPLETED)
                entity.getActions().add("EDIT");
            switch (entity.getGrievanceStatus()) {
                case UNASSIGNED: entity.getActions().add("ASSIGNED"); break;
                case ASSIGNED: entity.getActions().add("COMPLETED"); break;
                case ESCALATED: entity.getActions().add("ASSIGNED"); break;
                case COMPLETED: entity.getActions().add("CLOSED"); break;
            }
        }
    }


    public Grievance getGrievanceById(Integer id) throws GrievanceException {
        Optional<Grievance> grievance = grievanceRepository.findById(id);
        if(grievance.isPresent())
            return grievance.get();
        else
            throw new GrievanceException("GRIEVANCE NOT EXIST");
    }

    public List<GrievanceAction> actionByExist(int assigneeId,GrievanceStatus grievanceStatus) {
        return grievanceActionRepository.findGrievanceActionsByActionByIsAndGrievanceStatus(assigneeId,grievanceStatus);
    }

    public List<Grievance> listByStatus(GrievanceStatus status) {
        return grievanceRepository.findGrievanceByGrievanceStatus(status);
    }

    private Grievance createGrievance(String description,byte[] attachment,int category_id,int location_id,int userId)throws GrievanceException
    {
        Grievance grievance = new Grievance();
        grievance.setDescription(description);
        grievance.setAttachment(attachment);
        grievance.setGrievanceStatus(GrievanceStatus.UNASSIGNED);
        grievance.setCategory(categoryService.getCategoryById(category_id));
        grievance.setLocation(locationService.getLocationById(location_id));
        User user = userService.getUserById(userId);
        grievance.setAddedBy(user);
        return grievance;
    }

    public Grievance addGrievance(int category_id,int location_id,String description,byte[] attachment,int user_id)
            throws GrievanceException {
        Grievance grievance = createGrievance(description,attachment,category_id,location_id,user_id);
        grievanceRepository.save(grievance);
        GrievanceAction grievanceAction = new GrievanceAction();
        grievanceAction.setGrievance(grievance);
        grievanceAction.setGrievanceStatus(GrievanceStatus.UNASSIGNED);
        grievanceAction.setDate(ZonedDateTime.now());
        grievanceActionRepository.save(grievanceAction);
        return getGrievanceById(grievance.getGrievanceId());
    }

    public Grievance updateGrievance(Grievance grievance)throws GrievanceException {
        List<Integer> ids = new ArrayList<Integer>();
        List<GrievanceAction> list = grievance.getGrievanceActions();
        for (GrievanceAction res : list) {
            ids.add(res.getActionBy().getAssigneeId());
        }
        Optional<Grievance> grievance1 = grievanceRepository.findById(grievance.getGrievanceId());
        if (grievance1.isPresent() && ids.isEmpty()) {
            return grievanceRepository.save(grievance);
        }
        throw new GrievanceException("Only existing Grievance can be updated");
    }

    public String deleteGrievance(int grievanceId) throws GrievanceException {
        Grievance grievance = getGrievanceById(grievanceId);
        if(grievance.getGrievanceStatus().equals(GrievanceStatus.UNASSIGNED))
        {
            grievanceRepository.delete(grievance);
            return "Deleted Successfully";
        }
        throw new GrievanceException("Only unassigned Grievance can be deleted");
    }

    private GrievanceAction createGrievanceAction(int user_id,String remark,Grievance grievance,GrievanceStatus grievanceStatus)
    {
        GrievanceAction action = new GrievanceAction();
        action.setDate(ZonedDateTime.now());
        action.setRemark(remark);
        action.setGrievance(grievance);
        action.setGrievanceStatus(grievanceStatus);
        Assignee assignee = assigneeRepository.findAssigneeByUser_UserId(user_id);
        logger.info("assigne name: "+assignee.getUser().getFirstName());
        action.setActionBy(assignee);
        grievanceActionRepository.save(action);
        grievance.setGrievanceStatus(grievanceStatus);
        grievanceRepository.save(grievance);
        return action;
    }

    private GrievanceDto convertGrievanceToDto(Grievance grievance)throws GrievanceException {
        GrievanceDto grievanceDto = new GrievanceDto();
        grievanceDto.setGrievanceId(grievance.getGrievanceId());
        grievanceDto.setComplaintNo("Gri-"+grievance.getGrievanceId());
        grievanceDto.setCategoryType(grievance.getCategory().getCategoryType());
        grievanceDto.setGrievanceStatus(grievance.getGrievanceStatus());
        Optional<GrievanceAction> action = grievanceActionRepository.
                findGrievanceActionByGrievance_GrievanceIdAndGrievanceStatus
                        (grievance.getGrievanceId(),grievance.getGrievanceStatus());
            if(action.isPresent())
            {
                grievanceDto.setReportedBy(grievance.getAddedBy().getFirstName()+" "+grievance.getAddedBy().getLastName());
                grievanceDto.setReportedOn(action.get().getDate().toString());
                //here i have doubt if logged in person is supervisor then he called assignedBy
                if(action.get().getAssignedBy()!=null)
                {
                    grievanceDto.setAssignedTo(action.get().getActionBy().getUser().getFirstName()+" "+action.get().getActionBy().getUser().getLastName());
                    grievanceDto.setAssignedOn(action.get().getDate().toString());
                }
                if(action.get().getActionBy()!=null)
                {
                    grievanceDto.setAssignedTo(action.get().getActionBy().getUser().getFirstName()+" "+action.get().getActionBy().getUser().getLastName());
                    grievanceDto.setAssignedOn(action.get().getDate().toString());
                }
            }

            return grievanceDto;
    }

    public GrievanceDto changeActionToAssigned(int grievanceId,int userId,String remark)throws GrievanceException {
        Grievance grievance = getGrievanceById(grievanceId);
        if(grievance.getGrievanceStatus().equals(GrievanceStatus.UNASSIGNED))
        {
            GrievanceAction action = createGrievanceAction(userId,remark,grievance,GrievanceStatus.ASSIGNED);
            return convertGrievanceToDto(getGrievanceById(grievanceId));
        }
        throw new GrievanceException("Only new Grievance can be assigned");
    }

    public GrievanceDto changeActionToEscalated(int grievanceId, int userId,String remark)throws GrievanceException {
        Grievance grievance = getGrievanceById(grievanceId);
        if(grievance.getGrievanceStatus().equals(GrievanceStatus.ASSIGNED))
        {
            GrievanceAction action = createGrievanceAction(userId,remark,grievance,GrievanceStatus.ESCALATED);
            return convertGrievanceToDto(getGrievanceById(grievanceId));
        }
        throw new GrievanceException("Only assigned Grievance can be escalated");
    }

    public GrievanceDto changeActionToCompleted(int grievanceId, int userId,String remark)throws GrievanceException {
        Grievance grievance = getGrievanceById(grievanceId);
        if(grievance.getGrievanceStatus().equals(GrievanceStatus.ASSIGNED))
        {
            GrievanceAction action = createGrievanceAction(userId,remark,grievance,GrievanceStatus.COMPLETED);
            return convertGrievanceToDto(getGrievanceById(grievanceId));
        }
        throw new GrievanceException("Only assigned Grievance can be completed");
    }

    public GrievanceDto changeActionToClose(int grievanceId, int userId,String remark)throws GrievanceException {
        Grievance grievance = getGrievanceById(grievanceId);
        if(grievance.getGrievanceStatus().equals(GrievanceStatus.COMPLETED))
        {
            GrievanceAction action = createGrievanceAction(userId,remark,grievance,GrievanceStatus.CLOSED);
            return convertGrievanceToDto(getGrievanceById(grievanceId));
        }
        throw new GrievanceException("Only completed Grievance can be closed");
    }

    //here login user's id becomes assignerId and which assignee that user select to tackel,
    // that assignee's id becomes assigneeId here
    public GrievanceDto reassign(int grievanceId, int assigneeId,int assignerId,String remark)throws GrievanceException {
        Grievance grievance = getGrievanceById(grievanceId);
        if(grievance.getGrievanceStatus().equals(GrievanceStatus.ESCALATED))
        {
            GrievanceAction action = new GrievanceAction();
            action.setGrievance(grievance);
            action.setGrievanceStatus(GrievanceStatus.ASSIGNED);
            action.setDate(ZonedDateTime.now());
            action.setRemark(remark);
            action.setAssignedBy(assigneeRepository.findAssigneeByUser_UserId(assignerId));
            Assignee assignee = assigneeRepository.findById(assigneeId).get();
            action.setActionBy(assignee);
            grievance.setGrievanceStatus(GrievanceStatus.ASSIGNED);
            grievanceRepository.save(grievance);
            grievanceActionRepository.save(action);
            return convertGrievanceToDto(getGrievanceById(grievanceId));
        }
        throw new GrievanceException("Only escalated Grievance can be assigned");
    }

}
