package org.miwa.miwa.controller.pub;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.service.AboutConfigService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AboutController {

    private final AboutConfigService aboutConfigService;

    @GetMapping("/about")
    public Result<Map<String, Object>> about() {
        return Result.ok(aboutConfigService.getConfig());
    }
}
