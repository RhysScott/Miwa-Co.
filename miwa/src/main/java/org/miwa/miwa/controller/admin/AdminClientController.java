package org.miwa.miwa.controller.admin;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.entity.HomeClient;
import org.miwa.miwa.service.HomeClientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminClientController {
    private final HomeClientService service;

    @GetMapping("/clients")
    public Result<java.util.List<HomeClient>> list() { return Result.ok(service.list()); }

    @PostMapping("/clients")
    public Result<HomeClient> create(@RequestBody HomeClient item) { return Result.ok(service.create(item)); }

    @PutMapping("/clients/{id}")
    public Result<HomeClient> update(@PathVariable Long id, @RequestBody HomeClient item) { return Result.ok(service.update(id, item)); }

    @DeleteMapping("/clients/{id}")
    public Result<Void> delete(@PathVariable Long id) { service.delete(id); return Result.ok(); }
}
