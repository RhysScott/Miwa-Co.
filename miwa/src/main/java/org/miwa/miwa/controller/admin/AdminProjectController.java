package org.miwa.miwa.controller.admin;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.dto.SortRequest;
import org.miwa.miwa.entity.Project;
import org.miwa.miwa.service.ProjectService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminProjectController {

    private final ProjectService projectService;

    @PostMapping("/projects")
    public Result<Project> create(@RequestBody Project project) {
        return Result.ok(projectService.create(project));
    }

    @PutMapping("/projects/{id}")
    public Result<Project> update(@PathVariable Long id, @RequestBody Project project) {
        return Result.ok(projectService.update(id, project));
    }

    @DeleteMapping("/projects/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        projectService.delete(id);
        return Result.ok();
    }

    @PutMapping("/projects/sort")
    public Result<Void> sort(@RequestBody SortRequest request) {
        projectService.updateSort(request);
        return Result.ok();
    }
}
