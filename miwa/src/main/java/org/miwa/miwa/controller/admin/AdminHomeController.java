package org.miwa.miwa.controller.admin;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.service.HomeConfigService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminHomeController {

    private final HomeConfigService homeConfigService;

    @PutMapping("/home")
    public Result<Void> update(@RequestBody Object config) {
        homeConfigService.updateConfig(config);
        return Result.ok();
    }
}
