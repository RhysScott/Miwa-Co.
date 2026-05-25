package org.miwa.miwa.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FooterConfig {
    private Long id;
    private String email;
    private String phone;
    private String address;
    private String copyright;
    private String location;
    private String enEmail;
    private String enPhone;
    private String enAddress;
    private LocalDateTime updatedAt;
}
