package org.miwa.miwa.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HomeConfig {
    private Long id;
    private String config;
    private LocalDateTime updatedAt;
}
