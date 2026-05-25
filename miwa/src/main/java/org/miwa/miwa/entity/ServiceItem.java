package org.miwa.miwa.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ServiceItem {
    private Long id;
    private String zh;
    private String en;
    private String desc;
    private String detail;
    private String icon;
    private String image;
    private Integer sortOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
