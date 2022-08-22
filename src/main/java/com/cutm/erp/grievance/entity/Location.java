package com.cutm.erp.grievance.entity;

import com.cutm.erp.common.BaseDto;

import javax.persistence.*;

@Entity
public class Location extends BaseDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int locationId;
    @Column(length = 45)
    private String locationName;

    @ManyToOne
    @JoinColumn(name = "campus_id")
    private Campus campus;

    public Location() {
    }

    public Location(String locationName, Campus campus) {
        this.locationName = locationName;
        this.campus = campus;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", locationName='" + locationName + '\'' +
                ", campus=" + campus +
                '}';
    }
}

