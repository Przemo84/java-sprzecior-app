package com.nordgeo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Tool {

    public Tool() {
    }

    public Tool(String companyId, String model, String toolType, String serialNo, Date productionDate, Date calibrationDate, Boolean available) {
        this.companyId = companyId;
        this.model = model;
        this.toolType = toolType;
        this.serialNo = serialNo;
        this.productionDate = productionDate;
        this.calibrationDate = calibrationDate;
        this.available = available;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @NotNull
    @Size(max = 250)
    private String companyId;

    @NotEmpty
    @NotNull
    @Size(max = 250)
    private String model;

    @Size(max = 250)
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
    @Size(max = 250)
    private String toolType;

    private String image;

    private Boolean available = true;

    private Boolean unusable = false;

    @DateTimeFormat
    private Date unusableDate;

    @Size(max = 250)
    private String unusableReason;

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

    public String getUnusableReason() { return unusableReason; }

    public void setUnusableReason(String unusableReason) { this.unusableReason = unusableReason; }
}
