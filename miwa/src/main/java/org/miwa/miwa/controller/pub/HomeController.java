package org.miwa.miwa.controller.pub;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.entity.*;
import org.miwa.miwa.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HomeController {

    private final ServiceItemService serviceItemService;
    private final ProjectService projectService;
    private final PeopleService peopleService;
    private final FooterConfigService footerConfigService;
    private final HomeMarqueeService marqueeService;
    private final HomeStatService statService;
    private final HomeProcessService processService;
    private final HomeClientService clientService;
    private final HomePhilosophyService philosophyService;
    private final HomeHeroService heroService;

    @GetMapping("/home")
    public Result<Map<String, Object>> home() {
        Map<String, Object> data = new LinkedHashMap<>();

        // Hero
        HomeHero heroEntity = heroService.get();
        Map<String, String> hero = new LinkedHashMap<>();
        if (heroEntity != null) {
            hero.put("zh", heroEntity.getZh());
            hero.put("en", heroEntity.getEn());
            hero.put("sub", heroEntity.getSub() != null ? heroEntity.getSub() : "");
        } else {
            hero.put("zh", "让技术回归简单");
            hero.put("en", "Technology, Made Simple");
            hero.put("sub", "AI 应用 · 软件工程 · 物联网 — 从概念到落地，全程可信赖");
        }
        data.put("hero", hero);

        // Marquee
        data.put("marquee", marqueeService.list().stream().map(m -> {
            Map<String, String> item = new LinkedHashMap<>();
            item.put("zh", m.getZh());
            item.put("en", m.getEn());
            return item;
        }).collect(Collectors.toList()));

        // Services
        data.put("services", serviceItemService.list());

        // Stats
        data.put("stats", statService.list().stream().map(s -> {
            Map<String, String> item = new LinkedHashMap<>();
            item.put("num", s.getNum());
            item.put("zh", s.getZh());
            item.put("en", s.getEn());
            return item;
        }).collect(Collectors.toList()));

        // Projects
        data.put("projects", projectService.list());

        // Process
        data.put("process", processService.list().stream().map(p -> {
            Map<String, String> item = new LinkedHashMap<>();
            item.put("zh", p.getZh());
            item.put("en", p.getEn());
            item.put("desc", p.getDescCn());
            return item;
        }).collect(Collectors.toList()));

        // Clients
        data.put("clients", clientService.list().stream().map(c -> {
            Map<String, String> item = new LinkedHashMap<>();
            item.put("name", c.getName());
            item.put("zh", c.getZh());
            return item;
        }).collect(Collectors.toList()));

        // Philosophy
        HomePhilosophy ph = philosophyService.get();
        Map<String, String> philosophy = new LinkedHashMap<>();
        if (ph != null) {
            philosophy.put("zh", ph.getZh());
            philosophy.put("en", ph.getEn());
            philosophy.put("sub", ph.getSub());
        }
        data.put("philosophy", philosophy);

        // About (home page about section — kept as empty, about page has its own endpoint)
        data.put("about", Collections.emptyMap());

        // Footer
        Map<String, Object> footer = footerConfigService.getConfig();
        data.put("footer", footer != null ? footer : Collections.emptyMap());

        // People (not used in HomeView but kept for API compatibility)
        data.put("people", peopleService.list());

        return Result.ok(data);
    }
}
