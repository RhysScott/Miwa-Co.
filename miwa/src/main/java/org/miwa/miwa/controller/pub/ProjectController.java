package org.miwa.miwa.controller.pub;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.entity.Project;
import org.miwa.miwa.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/projects")
    public Result<List<Project>> list() {
        return Result.ok(projectService.list());
    }

    @GetMapping("/projects/{id}")
    public Result<Project> detail(@PathVariable Long id) {
        return Result.ok(projectService.getById(id));
    }
}
