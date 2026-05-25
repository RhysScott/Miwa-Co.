package org.miwa.miwa.controller.pub;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.PageResult;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.entity.News;
import org.miwa.miwa.service.NewsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/news")
    public Result<PageResult<News>> list(@RequestParam(defaultValue = "1") int page,
                                         @RequestParam(defaultValue = "10") int pageSize,
                                         @RequestParam(defaultValue = "sortOrder,asc") String sort) {
        if (pageSize > 50) pageSize = 50;
        if (page < 1) page = 1;
        return Result.ok(newsService.list(page, pageSize, sort));
    }

    @GetMapping("/news/{id}")
    public Result<News> detail(@PathVariable Long id) {
        return Result.ok(newsService.getById(id));
    }
}
