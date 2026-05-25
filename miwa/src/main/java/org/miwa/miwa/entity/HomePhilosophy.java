package org.miwa.miwa.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HomePhilosophy {
    private Long id;
    private String zh;
    private String en;
    private String sub;
    private LocalDateTime updatedAt;
}
