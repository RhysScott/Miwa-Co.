package org.miwa.miwa.service;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.entity.HomeStat;
import org.miwa.miwa.exception.BusinessException;
import org.miwa.miwa.mapper.HomeStatMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeStatService {
    private final HomeStatMapper mapper;

    public List<HomeStat> list() { return mapper.findAll(); }

    public HomeStat create(HomeStat item) {
        if (item.getSortOrder() == null) item.setSortOrder(0);
        mapper.insert(item);
        return item;
    }

    public HomeStat update(Long id, HomeStat item) {
        item.setId(id);
        mapper.update(item);
        return item;
    }

    public void delete(Long id) {
        if (mapper.deleteById(id) == 0) throw new BusinessException(404, "数据不存在");
    }
}
