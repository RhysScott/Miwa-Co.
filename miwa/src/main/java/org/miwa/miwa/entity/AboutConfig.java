package org.miwa.miwa.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AboutConfig {
    private Long id;
    private String zhText;
    private String enText;
    private LocalDateTime updatedAt;
}
