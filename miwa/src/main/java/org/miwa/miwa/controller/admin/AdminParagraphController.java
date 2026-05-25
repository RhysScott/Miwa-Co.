package org.miwa.miwa.controller.admin;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.entity.AboutParagraph;
import org.miwa.miwa.service.AboutParagraphService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminParagraphController {
    private final AboutParagraphService service;

    @GetMapping("/paragraphs")
    public Result<java.util.List<AboutParagraph>> list() { return Result.ok(service.listAll()); }

    @PostMapping("/paragraphs")
    public Result<AboutParagraph> create(@RequestBody AboutParagraph item) { return Result.ok(service.create(item)); }

    @PutMapping("/paragraphs/{id}")
    public Result<AboutParagraph> update(@PathVariable Long id, @RequestBody AboutParagraph item) { return Result.ok(service.update(id, item)); }

    @DeleteMapping("/paragraphs/{id}")
    public Result<Void> delete(@PathVariable Long id) { service.delete(id); return Result.ok(); }
}
