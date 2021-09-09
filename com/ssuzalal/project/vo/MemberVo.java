package com.ssuzalal.project.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssuzalal.project.aop.PasswordForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberVo {
    @JsonProperty("id")
    @NotBlank
    @Length(max = 20)
    private String id;

    @JsonProperty("password")
    @NotBlank
    @PasswordForm
    private String password;

    @JsonProperty("email")
    @NotBlank
    private String email;

    @NotNull
    @JsonProperty("username")
    private String username;

    @NotNull
    @JsonProperty("major")
    private String major;

    @NotBlank
    @JsonProperty("minor")
    private String minor;

    @JsonProperty("grade")
    private int grade;

    @JsonProperty("createdTime")
    private Timestamp createdTime;

    @JsonProperty("memberType")
    private int memberType;

    @JsonProperty("authorized")
    private boolean authorized;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public int getMemberType() {
        return memberType;
    }

    public void setMemberType(int memberType) {
        this.memberType = memberType;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }
}
