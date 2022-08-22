
package com.cutm.erp.grievance.entity;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
public class GrievanceAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int actionId;

    private ZonedDateTime date;

    @Column(length = 55)
    private String remark;

    @Enumerated(EnumType.ORDINAL)
    private GrievanceStatus grievanceStatus;

    @ManyToOne
    @JoinColumn(name = "grievance_id")
    private Grievance grievance;

    @OneToOne //who currently take on the grievance or to whom grievance is assigned by supervisor
    @JoinColumn(name = "assignee_id")
    private Assignee actionBy;

    @Nullable
    @OneToOne //who assignned any grievance to other assignee
    @JoinColumn(name = "assigner_id")
    private Assignee assignedBy;

    public GrievanceAction() {
    }

    public GrievanceAction(int actionId, ZonedDateTime date, String action, String remark, GrievanceStatus grievanceStatus, Grievance grievance, Assignee actionBy) {
        this.actionId = actionId;
        this.date = date;
        this.remark = remark;
        this.grievanceStatus = grievanceStatus;
        this.grievance = grievance;
        this.actionBy = actionBy;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public GrievanceStatus getGrievanceStatus() {
        return grievanceStatus;
    }

    public void setGrievanceStatus(GrievanceStatus grievanceStatus) {
        this.grievanceStatus = grievanceStatus;
    }

    public Grievance getGrievance() {
        return grievance;
    }

    public void setGrievance(Grievance grievance) {
        this.grievance = grievance;
    }

    public Assignee getActionBy() {
        return actionBy;
    }

    public void setActionBy(Assignee actionBy) {
        this.actionBy = actionBy;
    }

    public Assignee getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(Assignee assignedBy) {
        this.assignedBy = assignedBy;
    }

    @Override
    public String toString() {
        return "GrievanceAction{" +
                "actionId=" + actionId +
                ", date=" + date +

                ", remark='" + remark + '\'' +
                ", grievanceStatus=" + grievanceStatus +
                ", grievance=" + grievance +
                ", actionBy=" + actionBy +
                '}';
    }

}

