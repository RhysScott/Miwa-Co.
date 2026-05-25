package org.miwa.miwa.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Person {
    private Long id;
    private String name;
    private String en;
    private String role;
    private String roleEn;
    private String bio;
    private String image;
    private String content;
    private Integer sortOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
