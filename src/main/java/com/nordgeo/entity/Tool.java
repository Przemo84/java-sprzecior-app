package com.nordgeo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @NotNull
    private String companyId;

    @NotEmpty
    @NotNull
    private String title;

    @NotNull
    private String toolType;

    private String image;

    private Boolean available = true;

    @OneToOne
    private User user;

    @JsonIgnore
    @CreationTimestamp
    @Column(updatable = false)
    private Date createDate;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getCompanyId() { return companyId; }

    public void setCompanyId(String companyId) { this.companyId = companyId; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    public User getUser() {return user; }

    public void setUser(User user) { this.user = user; }

    public String getToolType() { return toolType; }

    public void setToolType(String toolType) { this.toolType = toolType; }

    public Boolean getAvailable() { return available; }

    public void setAvailable(Boolean available) { this.available = available; }

    public Date getCreateDate() { return createDate; }

    public void setCreateDate(Date createDate) { this.createDate = createDate; }
}
