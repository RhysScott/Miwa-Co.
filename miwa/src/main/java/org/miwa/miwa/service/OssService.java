package org.miwa.miwa.service;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.miwa.miwa.config.CosConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OssService {

    private final COSClient cosClient;
    private final CosConfig cosConfig;

    public Map<String, Object> upload(MultipartFile file, String dir) throws IOException {
        if (dir == null || dir.isBlank()) dir = "common";

        String originalName = file.getOriginalFilename();
        String ext = "";
        if (originalName != null && originalName.contains(".")) {
            ext = originalName.substring(originalName.lastIndexOf("."));
        }
        String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
        String key = dir + "/" + datePath + "/" + UUID.randomUUID().toString().substring(0, 8) + ext;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        PutObjectRequest request = new PutObjectRequest(cosConfig.getBucket(), key,
                file.getInputStream(), metadata);
        cosClient.putObject(request);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("url", cosConfig.getBaseUrl() + "/" + key);
        result.put("key", key);
        result.put("name", originalName);
        result.put("size", file.getSize());
        return result;
    }

    public void delete(String key) {
        cosClient.deleteObject(cosConfig.getBucket(), key);
    }
}
