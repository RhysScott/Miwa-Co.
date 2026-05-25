package org.miwa.miwa.controller.admin;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.dto.SortRequest;
import org.miwa.miwa.entity.CompanyValue;
import org.miwa.miwa.service.CompanyValueService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminValueController {

    private final CompanyValueService companyValueService;

    @PostMapping("/values")
    public Result<CompanyValue> create(@RequestBody CompanyValue value) {
        return Result.ok(companyValueService.create(value));
    }

    @PutMapping("/values/{id}")
    public Result<CompanyValue> update(@PathVariable Long id, @RequestBody CompanyValue value) {
        return Result.ok(companyValueService.update(id, value));
    }

    @DeleteMapping("/values/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        companyValueService.delete(id);
        return Result.ok();
    }

    @PutMapping("/values/sort")
    public Result<Void> sort(@RequestBody SortRequest request) {
        companyValueService.updateSort(request);
        return Result.ok();
    }
}
