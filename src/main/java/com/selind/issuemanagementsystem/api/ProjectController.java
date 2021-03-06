package com.selind.issuemanagementsystem.api;

import com.selind.issuemanagementsystem.dto.ProjectDto;
import com.selind.issuemanagementsystem.service.implement.ProjectServiceImplement;
import com.selind.issuemanagementsystem.util.ApiPaths;
import com.selind.issuemanagementsystem.util.TPage;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.scene.control.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiPaths.ProjectController.CTRL)
@Api(value=ApiPaths.ProjectController.CTRL,description = "Project API's")
@Slf4j //loglama
@CrossOrigin //pagination işlemi yaparrken cross hatası aldığında verildi
public class ProjectController {
    //
    private final ProjectServiceImplement projectServiceImplement;

    public ProjectController(ProjectServiceImplement projectServiceImplement) {
        this.projectServiceImplement = projectServiceImplement;
    }

    @GetMapping("/pagination")
    @ApiOperation(value="Get Pagination Operation",response=ProjectDto.class)
    public ResponseEntity<TPage<ProjectDto>> getAllByPagination(Pageable pageable){
        TPage<ProjectDto> data= projectServiceImplement.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }
    //projectdto üzerinden nesneleri idsine göre getirme işlemi PathVariable: GetMapping de verilen idyi alıp getirecek
    @GetMapping("/{id}")
    @ApiOperation(value="Get by id",response=ProjectDto.class)
    public ResponseEntity<ProjectDto> getById(@PathVariable(value="id",required = true) Long id){
        log.info("ProjectController-> GetById");
        log.debug("ProjectController-> GetById-> Param"+id);
        ProjectDto projectDto=projectServiceImplement.getById(id);
        return ResponseEntity.ok(projectDto);
    }

    @PostMapping
    @ApiOperation(value="Create by id",response=ProjectDto.class)
    public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody ProjectDto project){
        return ResponseEntity.ok(projectServiceImplement.save(project));
    }

    @PutMapping("/{id}")
    @ApiOperation(value="Update by id",response=ProjectDto.class)
    public ResponseEntity<ProjectDto> updateProject(@PathVariable("id") Long id,@Valid @RequestBody ProjectDto project){
        return ResponseEntity.ok(projectServiceImplement.update(id,project));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="Delete by id",response=Boolean.class)
    public ResponseEntity<Boolean> delete(@PathVariable(value="id",required = true) Long id, @Valid @RequestBody ProjectDto project){
        return ResponseEntity.ok(projectServiceImplement.delete(id));
    }
}
