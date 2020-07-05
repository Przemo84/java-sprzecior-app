package com.escl.citi.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    public void setAvailable(Boolean available) { available = available; }
}
