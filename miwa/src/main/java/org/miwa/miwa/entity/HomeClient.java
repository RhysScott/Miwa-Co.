package org.miwa.miwa.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HomeClient {
    private Long id;
    private String name;
    private String zh;
    private Integer sortOrder;
    private LocalDateTime createdAt;
}
