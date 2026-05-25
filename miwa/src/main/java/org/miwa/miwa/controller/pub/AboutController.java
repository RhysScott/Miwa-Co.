package org.miwa.miwa.controller.pub;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.service.AboutParagraphService;
import org.miwa.miwa.service.CompanyValueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AboutController {

    private final AboutParagraphService paragraphService;
    private final CompanyValueService companyValueService;

    @GetMapping("/about")
    public Result<Map<String, Object>> about() {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("zh", paragraphService.listByLang("zh").stream()
                .map(p -> p.getContent()).collect(Collectors.toList()));
        data.put("en", paragraphService.listByLang("en").stream()
                .map(p -> p.getContent()).collect(Collectors.toList()));
        data.put("values", companyValueService.list());
        return Result.ok(data);
    }
}
