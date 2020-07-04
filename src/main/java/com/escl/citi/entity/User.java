package com.escl.citi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "username"})})
public class User {

    public enum RoleName {
        ADMIN_ROLE(1),
        DEALER_ROLE(2),
        PIN_SUPERVISOR_ROLE(3);

        private final int value;

        RoleName(final int value) { this.value = value; }

        public int getValue() { return value; }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @Size(min = 2, max = 250)
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 250)
    private String lastName;

    @JsonIgnore
    @NotEmpty
    @Size(min = 3, max = 250)
    private String username;

    @NotEmpty
    @Email
    @Size(max = 250)
    private String email;

    @JsonIgnore
    private String password;

    @JsonIgnore
    @Transient
    private String passwordConfirm;

    @JsonIgnore
    @CreationTimestamp
    @Column(updatable = false)
    private Date createDate;

    @JsonIgnore
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean active = true;

    @JsonIgnore
    @DateTimeFormat
    private Date lockDate;

    @JsonIgnore
    @DateTimeFormat
    private Date lastLoginDate;

    @Size(min = 5, max = 250)
    private String icon;

    @Size(min = 9 , max = 13, message = "Numer powinien posiadać format np. +48600700800")
    private String mobile;


    @JsonIgnore
    @NotNull
    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_USER_ROLE"))
    private Role role;

    @ElementCollection
    private List<String> userPasswords;

    @JsonIgnore
    @NotNull
    private Integer loginAttempts = 0;

    @JsonIgnore
    private Boolean mustChangePassword = true;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "maker_id")
    private User maker;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "checker_id")
    private User checker;

    public User() { }

    public User(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @JsonIgnore
    public String getStrId() {
        return id.toString();
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getActive() {
        if (active == null)
            return false;

        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getLockDate() {
        return lockDate;
    }

    public void setLockDate(Date lockDate) {
        this.lockDate = lockDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getIcon() { return icon; }

    public void setIcon(String icon) { this.icon = icon; }

    public String getMobile() { return mobile; }

    public void setMobile(String mobile) { this.mobile = mobile; }

    public List<String> getUserPasswords() {
        return userPasswords;
    }

    public void addUserPassword(String userPasswords) {
        if(this.userPasswords == null){
            this.userPasswords = new ArrayList<>();
        }

        if(this.userPasswords.size() == 6)
            this.userPasswords.remove(0);

        this.userPasswords.add(userPasswords);
    }

    public Integer getLoginAttempts() { return loginAttempts; }

    public void setLoginAttempts(Integer loginAttempts) { this.loginAttempts = loginAttempts; }

    public Boolean getMustChangePassword() { return mustChangePassword; }

    public void setMustChangePassword(Boolean mustChangePassword) { this.mustChangePassword = mustChangePassword; }

    public User getMaker() { return maker; }

    public void setMaker(User maker) { this.maker = maker; }

    public User getChecker() { return checker; }

    public void setChecker(User checker) { this.checker = checker; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
