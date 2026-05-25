package org.miwa.miwa.dto;

import lombok.Data;
import java.util.List;

@Data
public class SortRequest {
    private List<SortItem> items;

    @Data
    public static class SortItem {
        private Long id;
        private Integer sortOrder;
    }
}
