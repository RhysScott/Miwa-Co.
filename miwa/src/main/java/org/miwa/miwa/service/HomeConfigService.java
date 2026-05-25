package org.miwa.miwa.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.HomeData;
import org.miwa.miwa.entity.HomeConfig;
import org.miwa.miwa.mapper.HomeConfigMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class HomeConfigService {

    private final HomeConfigMapper homeConfigMapper;
    private final ObjectMapper objectMapper;

    @SuppressWarnings("unchecked")
    public Map<String, Object> getConfig() {
        HomeConfig config = homeConfigMapper.findLatest();
        if (config == null || config.getConfig() == null) return null;
        try {
            return objectMapper.readValue(config.getConfig(), Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("首页配置解析失败", e);
        }
    }

    public void updateConfig(Object data) {
        HomeConfig config = new HomeConfig();
        try {
            config.setConfig(objectMapper.writeValueAsString(data));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("首页配置序列化失败", e);
        }
        homeConfigMapper.insert(config);
    }
}
