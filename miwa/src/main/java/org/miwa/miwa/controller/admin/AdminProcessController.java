package org.miwa.miwa.controller.admin;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.entity.HomeProcess;
import org.miwa.miwa.service.HomeProcessService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminProcessController {
    private final HomeProcessService service;

    @GetMapping("/process")
    public Result<java.util.List<HomeProcess>> list() { return Result.ok(service.list()); }

    @PostMapping("/process")
    public Result<HomeProcess> create(@RequestBody HomeProcess item) { return Result.ok(service.create(item)); }

    @PutMapping("/process/{id}")
    public Result<HomeProcess> update(@PathVariable Long id, @RequestBody HomeProcess item) { return Result.ok(service.update(id, item)); }

    @DeleteMapping("/process/{id}")
    public Result<Void> delete(@PathVariable Long id) { service.delete(id); return Result.ok(); }
}
