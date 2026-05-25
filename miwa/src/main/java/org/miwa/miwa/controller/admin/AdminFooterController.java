package org.miwa.miwa.controller.admin;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.service.FooterConfigService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminFooterController {

    private final FooterConfigService footerConfigService;

    @PutMapping("/footer")
    public Result<Void> update(@RequestBody java.util.Map<String, Object> data) {
        footerConfigService.updateConfig(data);
        return Result.ok();
    }
}
