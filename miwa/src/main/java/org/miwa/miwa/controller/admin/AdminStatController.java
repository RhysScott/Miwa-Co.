package org.miwa.miwa.controller.admin;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.entity.HomeStat;
import org.miwa.miwa.service.HomeStatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminStatController {
    private final HomeStatService service;

    @GetMapping("/stats")
    public Result<java.util.List<HomeStat>> list() { return Result.ok(service.list()); }

    @PostMapping("/stats")
    public Result<HomeStat> create(@RequestBody HomeStat item) { return Result.ok(service.create(item)); }

    @PutMapping("/stats/{id}")
    public Result<HomeStat> update(@PathVariable Long id, @RequestBody HomeStat item) { return Result.ok(service.update(id, item)); }

    @DeleteMapping("/stats/{id}")
    public Result<Void> delete(@PathVariable Long id) { service.delete(id); return Result.ok(); }
}
