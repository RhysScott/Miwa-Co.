package org.miwa.miwa.service;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.entity.FooterConfig;
import org.miwa.miwa.mapper.FooterConfigMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FooterConfigService {

    private final FooterConfigMapper footerConfigMapper;

    public Map<String, Object> getConfig() {
        FooterConfig config = footerConfigMapper.findLatest();
        if (config == null) return null;
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("email", config.getEmail());
        result.put("phone", config.getPhone());
        result.put("address", config.getAddress());
        result.put("copyright", config.getCopyright());
        result.put("location", config.getLocation());
        Map<String, String> en = new LinkedHashMap<>();
        en.put("email", config.getEnEmail());
        en.put("phone", config.getEnPhone());
        en.put("address", config.getEnAddress());
        result.put("en", en);
        return result;
    }

    @SuppressWarnings("unchecked")
    public void updateConfig(Map<String, Object> data) {
        FooterConfig config = new FooterConfig();
        config.setEmail((String) data.get("email"));
        config.setPhone((String) data.get("phone"));
        config.setAddress((String) data.get("address"));
        config.setCopyright((String) data.get("copyright"));
        config.setLocation((String) data.get("location"));
        Map<String, String> en = (Map<String, String>) data.get("en");
        if (en != null) {
            config.setEnEmail(en.get("email"));
            config.setEnPhone(en.get("phone"));
            config.setEnAddress(en.get("address"));
        }
        footerConfigMapper.insert(config);
    }
}
