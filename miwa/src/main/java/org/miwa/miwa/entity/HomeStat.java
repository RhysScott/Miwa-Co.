package org.miwa.miwa.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HomeStat {
    private Long id;
    private String num;
    private String zh;
    private String en;
    private Integer sortOrder;
    private LocalDateTime createdAt;
}
