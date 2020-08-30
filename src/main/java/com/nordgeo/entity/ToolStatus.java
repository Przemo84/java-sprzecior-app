package com.nordgeo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class ToolStatus {

    public enum Action {
        TAKE_IN(1),
        TAKE_OUT(2);

        private final int value;

        Action(final int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @NotNull
    private String description;

    @OneToOne
    private User user;

    @OneToOne
    private Tool tool;

    @Min(1)
    @Max(5)
    private int rating = 5;

    @Enumerated(EnumType.STRING)
    private Action action;

    private Boolean needRepair = false;

    @JsonIgnore
    @CreationTimestamp
    @Column(updatable = false)
    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public int getRating() { return rating; }

    public void setRating(int rating) { this.rating = rating; }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Boolean getNeedRepair() {
        return needRepair;
    }

    public void setNeedRepair(Boolean needRepair) {
        this.needRepair = needRepair;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
