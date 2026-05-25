package org.miwa.miwa.controller.admin;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.entity.HomePhilosophy;
import org.miwa.miwa.service.HomePhilosophyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminPhilosophyController {
    private final HomePhilosophyService service;

    @GetMapping("/philosophy")
    public Result<HomePhilosophy> get() { return Result.ok(service.get()); }

    @PutMapping("/philosophy")
    public Result<Void> save(@RequestBody HomePhilosophy item) { service.save(item); return Result.ok(); }
}
