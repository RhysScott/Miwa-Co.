package org.miwa.miwa.controller.admin;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.service.OssService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class UploadController {

    private final OssService ossService;

    @PostMapping("/upload")
    public Result<Map<String, Object>> upload(@RequestParam("file") MultipartFile file,
                                              @RequestParam(defaultValue = "common") String dir) throws IOException {
        if (file.isEmpty()) {
            return Result.fail("文件不能为空");
        }
        return Result.ok(ossService.upload(file, dir));
    }

    @DeleteMapping("/upload")
    public Result<Void> delete(@RequestBody Map<String, String> body) {
        String key = body.get("key");
        if (key == null || key.isBlank()) {
            return Result.fail("key 不能为空");
        }
        ossService.delete(key);
        return Result.ok();
    }
}
