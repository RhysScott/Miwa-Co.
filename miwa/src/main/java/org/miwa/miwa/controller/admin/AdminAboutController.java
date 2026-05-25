package org.miwa.miwa.controller.admin;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.service.AboutConfigService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminAboutController {

    private final AboutConfigService aboutConfigService;

    @PutMapping("/about")
    public Result<Void> update(@RequestBody java.util.Map<String, Object> data) {
        aboutConfigService.updateConfig(data);
        return Result.ok();
    }
}
