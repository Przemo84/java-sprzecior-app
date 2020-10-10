package com.nordgeo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private String model;

    private String serialNo;

    @DateTimeFormat
    private Date productionDate;

    @DateTimeFormat
    private Date calibrationDate;

    @DateTimeFormat
    private Date takenDate;

    @OneToMany(mappedBy = "tool", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<ToolStatus> toolStatuses = new ArrayList<>();

    @NotNull
    private String toolType;

    private String image;

    private Boolean available = true;

    private Boolean unusable = false;

    @DateTimeFormat
    private Date unusableDate;

    @Formula(value = "(select avg(tool_status.rating) from tool_status where tool_status.tool_id = id)")
    private Double averageRating;

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

    public String getModel() { return model; }

    public void setModel(String model) { this.model = model; }

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

    public String getSerialNo() { return serialNo; }

    public void setSerialNo(String serialNo) { this.serialNo = serialNo; }

    public Date getProductionDate() { return productionDate; }

    public void setProductionDate(String productionDate) throws ParseException {
        if (!productionDate.equals(""))
            this.productionDate = new SimpleDateFormat("yyyy-MM-dd").parse(productionDate);
    }

    public Date getCalibrationDate() { return calibrationDate; }

    public void setCalibrationDate(String calibrationDate) throws ParseException {
        if (!calibrationDate.equals(""))
            this.calibrationDate = new SimpleDateFormat("yyyy-MM-dd").parse(calibrationDate);;
    }

    public Date getTakenDate() { return takenDate; }

    public void setTakenDate(Date takenDate) { this.takenDate = takenDate; }

    public Double getAverageRating() { return averageRating; }

    public Boolean getUnusable() { return unusable; }

    public void setUnusable(Boolean unusable) { this.unusable = unusable; }

    public Date getUnusableDate() { return unusableDate; }

    public void setUnusableDate(Date unusableDate) { this.unusableDate = unusableDate; }
}
