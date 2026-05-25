package org.miwa.miwa.controller.pub;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HomeController {

    private final HomeConfigService homeConfigService;
    private final ServiceItemService serviceItemService;
    private final ProjectService projectService;
    private final NewsService newsService;
    private final PeopleService peopleService;
    private final FooterConfigService footerConfigService;
    private final AboutConfigService aboutConfigService;

    @GetMapping("/home")
    public Result<Map<String, Object>> home() {
        Map<String, Object> config = homeConfigService.getConfig();
        if (config == null) {
            config = buildDefault();
        } else {
            enrichWithData(config);
        }
        return Result.ok(config);
    }

    private void enrichWithData(Map<String, Object> config) {
        // Always add fresh data from DB tables
        config.put("services", serviceItemService.list());
        config.put("projects", projectService.list());
        config.put("people", peopleService.list());
        // Ensure defaults for fields not in config JSON
        config.putIfAbsent("hero", buildDefaultHero());
        config.putIfAbsent("about", Collections.emptyMap());
        Map<String, Object> footer = footerConfigService.getConfig();
        config.putIfAbsent("footer", footer != null ? footer : Collections.emptyMap());
    }

    private Map<String, String> buildDefaultHero() {
        Map<String, String> hero = new LinkedHashMap<>();
        hero.put("zh", "让技术回归简单");
        hero.put("en", "Technology, Made Simple");
        hero.put("sub", "AI 应用 · 软件工程 · 物联网 — 从概念到落地，全程可信赖");
        return hero;
    }

    private Map<String, Object> buildDefault() {
        Map<String, Object> data = new LinkedHashMap<>();
        Map<String, String> hero = new LinkedHashMap<>();
        hero.put("zh", "让技术回归简单");
        hero.put("en", "Technology, Made Simple");
        hero.put("sub", "AI 应用 · 软件工程 · 物联网 — 从概念到落地，全程可信赖");
        data.put("hero", hero);
        data.put("marquee", Collections.emptyList());
        data.put("services", serviceItemService.list());
        data.put("stats", Collections.emptyList());
        data.put("projects", projectService.list());
        data.put("process", Collections.emptyList());
        data.put("clients", Collections.emptyList());
        data.put("philosophy", Collections.emptyMap());
        data.put("about", Collections.emptyMap());
        Map<String, Object> footer = footerConfigService.getConfig();
        data.put("footer", footer != null ? footer : Collections.emptyMap());
        return data;
    }
}
