package com.cutm.erp.grievance.entity;

import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(length = 32)
    private String firstName;
    @Column(length = 32)
    private String lastName;
    @Column(length = 32)
    private String officialEmail;
    @Column(length = 12)
    private String phone1;
    @Column(length = 12)
    private String phone2;

    // for login
    private String loginId;
    private String password;

    @Enumerated(EnumType.ORDINAL)
    private UserType userType;

    @Nullable
    @OneToOne
    @JoinColumn(name = "campus_id")
    private Campus campus;

    public User() {
    }

    public User(int userId, String firstName, String lastName, String officialEmail, String phone1, String phone2, String fullName, String loginId, String password, UserType userType, @Nullable Campus campus) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.officialEmail = officialEmail;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.loginId = loginId;
        this.password = password;
        this.userType = userType;
        this.campus = campus;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOfficialEmail() {
        return officialEmail;
    }

    public void setOfficialEmail(String officialEmail) {
        this.officialEmail = officialEmail;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }


    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Nullable
    public Campus getCampus() {
        return campus;
    }

    public void setCampus(@Nullable Campus campus) {
        this.campus = campus;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", officialEmail='" + officialEmail + '\'' +
                ", phone1='" + phone1 + '\'' +
                ", phone2='" + phone2 + '\'' +
                ", loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", campus=" + campus +
                '}';
    }
}

