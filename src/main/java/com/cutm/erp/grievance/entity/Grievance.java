package com.cutm.erp.grievance.entity;

import com.cutm.erp.common.BaseDto;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
public class Grievance extends BaseDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int grievanceId;
    @Column(unique = true, length = 25)
    private String complaintNo;
    @Column(length = 122)
    private String description;
    @Lob
    private byte[] attachment;

    @Enumerated(EnumType.ORDINAL)
    private GrievanceStatus grievanceStatus;
    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;
    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "grievance")
    private List<GrievanceAction> grievanceActions;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User addedBy;

    public Grievance() {
    }

    public Grievance(int grievanceId, String complaintNo, String description, byte[] attachment, GrievanceStatus grievanceStatus, Location location, Category category, List<GrievanceAction> grievanceActions) {
        this.grievanceId = grievanceId;
        this.complaintNo = complaintNo;
        this.description = description;
        this.attachment = attachment;
        this.grievanceStatus = grievanceStatus;
        this.location = location;
        this.category = category;
        this.grievanceActions = grievanceActions;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public GrievanceStatus getGrievanceStatus() {
        return grievanceStatus;
    }

    public void setGrievanceStatus(GrievanceStatus grievanceStatus) {
        this.grievanceStatus = grievanceStatus;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<GrievanceAction> getGrievanceActions() {
        return grievanceActions;
    }

    public void setGrievanceActions(List<GrievanceAction> grievanceActions) {
        this.grievanceActions = grievanceActions;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

    @Override
    public String toString() {
        return "Grievance{" +
                "grievanceId=" + grievanceId +
                ", complaintNo='" + complaintNo + '\'' +
                ", description='" + description + '\'' +
                ", attachment=" + Arrays.toString(attachment) +
                ", grievanceStatus=" + grievanceStatus +
                ", location=" + location +
                ", category=" + category +
                ", grievanceActions=" + grievanceActions +
                '}';
    }
}
