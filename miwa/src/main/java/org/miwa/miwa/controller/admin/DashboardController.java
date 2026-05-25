package org.miwa.miwa.controller.admin;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class DashboardController {
    private final NewsService newsService;
    private final ProjectService projectService;
    private final PeopleService peopleService;
    private final ServiceItemService serviceItemService;
    private final CaseItemService caseItemService;
    private final CompanyValueService companyValueService;

    @GetMapping("/dashboard")
    public Result<Map<String, Object>> stats() {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("news", newsService.list(1, 1, "created_at").getTotal());
        data.put("projects", projectService.list().size());
        data.put("people", peopleService.list().size());
        data.put("services", serviceItemService.list().size());
        data.put("cases", caseItemService.list().size());
        data.put("values", companyValueService.list().size());
        return Result.ok(data);
    }
}
