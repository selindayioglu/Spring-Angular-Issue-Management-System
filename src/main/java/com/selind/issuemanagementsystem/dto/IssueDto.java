package com.selind.issuemanagementsystem.dto;

import com.selind.issuemanagementsystem.entity.IssueStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.util.Date;
@Data
@ApiModel(value = "Issue Data Transfer Object")
public class IssueDto {
    @ApiModelProperty(value="Issue id")
    private Long id;
    @ApiModelProperty(required = true,value="Description")
    private String description;
    @ApiModelProperty(required = true,value="Details")
    private String details;
    @ApiModelProperty(required = true,value="Date")
    private Date date;
    @ApiModelProperty(required = true,value="Issue Status")
    private IssueStatus issueStatus;
    @ApiModelProperty(required = true,value="Assignee")
    private UserDto assignee;
    @ApiModelProperty(required = true,value="Project")
    private ProjectDto project;

}
