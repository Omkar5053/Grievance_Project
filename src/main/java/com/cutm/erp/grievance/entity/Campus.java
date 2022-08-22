package com.cutm.erp.grievance.entity;

import com.cutm.erp.common.BaseDto;

import javax.persistence.*;
import java.util.List;

@Entity
public class Campus extends BaseDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int campusId;

    @Column(length = 45)
    private String campusName;


    public Campus() {
    }

    public Campus(String campusName) {
        this.campusName = campusName;
    }

    public int getCampusId() {
        return campusId;
    }

    public void setCampusId(int campusId) {
        this.campusId = campusId;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    @Override
    public String toString() {
        return "Campus{" +
                "campusId=" + campusId +
                ", campusName='" + campusName + '\'' +
                '}';
    }
}

