package org.miwa.miwa.service;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.entity.HomeMarquee;
import org.miwa.miwa.exception.BusinessException;
import org.miwa.miwa.mapper.HomeMarqueeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeMarqueeService {
    private final HomeMarqueeMapper mapper;

    public List<HomeMarquee> list() { return mapper.findAll(); }

    public HomeMarquee create(HomeMarquee item) {
        if (item.getSortOrder() == null) item.setSortOrder(0);
        mapper.insert(item);
        return item;
    }

    public HomeMarquee update(Long id, HomeMarquee item) {
        HomeMarquee existing = mapper.findAll().stream().filter(m -> m.getId().equals(id)).findFirst().orElse(null);
        if (existing == null) throw new BusinessException(404, "词条不存在");
        item.setId(id);
        mapper.update(item);
        return item;
    }

    public void delete(Long id) {
        if (mapper.deleteById(id) == 0) throw new BusinessException(404, "词条不存在");
    }
}
