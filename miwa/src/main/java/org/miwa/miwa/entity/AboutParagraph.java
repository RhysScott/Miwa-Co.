package org.miwa.miwa.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AboutParagraph {
    private Long id;
    private String lang;
    private String content;
    private Integer sortOrder;
    private LocalDateTime createdAt;
}
