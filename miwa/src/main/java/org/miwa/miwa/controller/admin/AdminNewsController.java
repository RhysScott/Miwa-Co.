package org.miwa.miwa.controller.admin;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.dto.SortRequest;
import org.miwa.miwa.entity.News;
import org.miwa.miwa.service.NewsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminNewsController {

    private final NewsService newsService;

    @PostMapping("/news")
    public Result<News> create(@RequestBody News news) {
        return Result.ok(newsService.create(news));
    }

    @PutMapping("/news/{id}")
    public Result<News> update(@PathVariable Long id, @RequestBody News news) {
        return Result.ok(newsService.update(id, news));
    }

    @DeleteMapping("/news/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        newsService.delete(id);
        return Result.ok();
    }

    @PutMapping("/news/sort")
    public Result<Void> sort(@RequestBody SortRequest request) {
        newsService.updateSort(request);
        return Result.ok();
    }
}
