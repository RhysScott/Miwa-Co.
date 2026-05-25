package org.miwa.miwa.controller.pub;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.service.CaseItemService;
import org.miwa.miwa.service.ProjectService;
import org.miwa.miwa.service.ServiceItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExploreController {

    private final ProjectService projectService;
    private final ServiceItemService serviceItemService;
    private final CaseItemService caseItemService;

    @GetMapping("/explore")
    public Result<Map<String, Object>> explore() {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("services", serviceItemService.list());
        data.put("cases", caseItemService.list());
        data.put("projects", projectService.list());
        return Result.ok(data);
    }
}
