package com.cutm.erp.grievance.dto;

import com.cutm.erp.common.BaseDto;
import com.cutm.erp.grievance.entity.Assignee;
import com.cutm.erp.grievance.entity.Category;
import com.cutm.erp.grievance.entity.GrievanceStatus;

import java.io.Serializable;
import java.util.List;


public class GrievanceDto extends BaseDto implements Serializable {
    //from grievance
    private int grievanceId;
    private String complaintNo;
    private GrievanceStatus grievanceStatus;
    private String categoryType;

    //that will come from GrievanceAction
    private String reportedBy;
    private String reportedOn;
    private String assignedTo;
    private String assignedOn;

    public GrievanceDto() {
    }

    public GrievanceDto(int grievanceId, String complaintNo, GrievanceStatus grievanceStatus, String categoryType, String reportedBy, String reportedOn, String assignedTo, String assignedOn) {
        this.grievanceId = grievanceId;
        this.complaintNo = complaintNo;
        this.grievanceStatus = grievanceStatus;
        this.categoryType = categoryType;
        this.reportedBy = reportedBy;
        this.reportedOn = reportedOn;
        this.assignedTo = assignedTo;
        this.assignedOn = assignedOn;
    }

    public int getGrievanceId() {
        return grievanceId;
    }

    public void setGrievanceId(int grievanceId) {
        this.grievanceId = grievanceId;
    }

    public String getComplaintNo() {
        return complaintNo;
    }

    public void setComplaintNo(String complaintNo) {
        this.complaintNo = complaintNo;
    }

    public GrievanceStatus getGrievanceStatus() {
        return grievanceStatus;
    }

    public void setGrievanceStatus(GrievanceStatus grievanceStatus) {
        this.grievanceStatus = grievanceStatus;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public String getReportedOn() {
        return reportedOn;
    }

    public void setReportedOn(String reportedOn) {
        this.reportedOn = reportedOn;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getAssignedOn() {
        return assignedOn;
    }

    public void setAssignedOn(String assignedOn) {
        this.assignedOn = assignedOn;
    }
}
