package org.miwa.miwa.controller.admin;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.entity.HomeHero;
import org.miwa.miwa.service.HomeHeroService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminHeroController {
    private final HomeHeroService service;

    @GetMapping("/hero")
    public Result<HomeHero> get() { return Result.ok(service.get()); }

    @PutMapping("/hero")
    public Result<Void> save(@RequestBody HomeHero item) { service.save(item); return Result.ok(); }
}
