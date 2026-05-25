package org.miwa.miwa.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.miwa.miwa.entity.AboutConfig;
import org.miwa.miwa.entity.CompanyValue;
import org.miwa.miwa.mapper.AboutConfigMapper;
import org.miwa.miwa.mapper.CompanyValueMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AboutConfigService {

    private final AboutConfigMapper aboutConfigMapper;
    private final CompanyValueMapper companyValueMapper;
    private final ObjectMapper objectMapper;

    @SuppressWarnings("unchecked")
    public Map<String, Object> getConfig() {
        AboutConfig config = aboutConfigMapper.findLatest();
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            if (config != null && config.getZhText() != null) {
                result.put("zh", objectMapper.readValue(config.getZhText(), List.class));
            } else {
                result.put("zh", Collections.emptyList());
            }
            if (config != null && config.getEnText() != null) {
                result.put("en", objectMapper.readValue(config.getEnText(), List.class));
            } else {
                result.put("en", Collections.emptyList());
            }
        } catch (JsonProcessingException e) {
            result.put("zh", Collections.emptyList());
            result.put("en", Collections.emptyList());
        }
        result.put("values", companyValueMapper.findAll());
        return result;
    }

    @SuppressWarnings("unchecked")
    public void updateConfig(Map<String, Object> data) {
        AboutConfig config = new AboutConfig();
        try {
            if (data.containsKey("zh")) {
                config.setZhText(objectMapper.writeValueAsString(data.get("zh")));
            }
            if (data.containsKey("en")) {
                config.setEnText(objectMapper.writeValueAsString(data.get("en")));
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("关于页配置序列化失败", e);
        }
        aboutConfigMapper.insert(config);
    }
}
