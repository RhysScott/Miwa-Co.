package org.miwa.miwa.controller.admin;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.entity.HomeMarquee;
import org.miwa.miwa.service.HomeMarqueeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminMarqueeController {
    private final HomeMarqueeService service;

    @GetMapping("/marquee")
    public Result<java.util.List<HomeMarquee>> list() { return Result.ok(service.list()); }

    @PostMapping("/marquee")
    public Result<HomeMarquee> create(@RequestBody HomeMarquee item) { return Result.ok(service.create(item)); }

    @PutMapping("/marquee/{id}")
    public Result<HomeMarquee> update(@PathVariable Long id, @RequestBody HomeMarquee item) { return Result.ok(service.update(id, item)); }

    @DeleteMapping("/marquee/{id}")
    public Result<Void> delete(@PathVariable Long id) { service.delete(id); return Result.ok(); }
}
