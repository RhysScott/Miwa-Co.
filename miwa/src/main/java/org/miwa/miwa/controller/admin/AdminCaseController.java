package org.miwa.miwa.controller.admin;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.dto.SortRequest;
import org.miwa.miwa.entity.CaseItem;
import org.miwa.miwa.service.CaseItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminCaseController {

    private final CaseItemService caseItemService;

    @PostMapping("/cases")
    public Result<CaseItem> create(@RequestBody CaseItem item) {
        return Result.ok(caseItemService.create(item));
    }

    @PutMapping("/cases/{id}")
    public Result<CaseItem> update(@PathVariable Long id, @RequestBody CaseItem item) {
        return Result.ok(caseItemService.update(id, item));
    }

    @DeleteMapping("/cases/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        caseItemService.delete(id);
        return Result.ok();
    }

    @PutMapping("/cases/sort")
    public Result<Void> sort(@RequestBody SortRequest request) {
        caseItemService.updateSort(request);
        return Result.ok();
    }
}
