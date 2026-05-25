package org.miwa.miwa.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HomeProcess {
    private Long id;
    private String zh;
    private String en;
    private String descCn;
    private Integer sortOrder;
    private LocalDateTime createdAt;
}
