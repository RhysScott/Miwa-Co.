package org.miwa.miwa.controller.admin;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.dto.SortRequest;
import org.miwa.miwa.entity.ServiceItem;
import org.miwa.miwa.service.ServiceItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminServiceController {

    private final ServiceItemService serviceItemService;

    @PostMapping("/services")
    public Result<ServiceItem> create(@RequestBody ServiceItem item) {
        return Result.ok(serviceItemService.create(item));
    }

    @PutMapping("/services/{id}")
    public Result<ServiceItem> update(@PathVariable Long id, @RequestBody ServiceItem item) {
        return Result.ok(serviceItemService.update(id, item));
    }

    @DeleteMapping("/services/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        serviceItemService.delete(id);
        return Result.ok();
    }

    @PutMapping("/services/sort")
    public Result<Void> sort(@RequestBody SortRequest request) {
        serviceItemService.updateSort(request);
        return Result.ok();
    }
}
