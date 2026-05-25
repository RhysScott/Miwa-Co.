package org.miwa.miwa.service;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.entity.HomeClient;
import org.miwa.miwa.exception.BusinessException;
import org.miwa.miwa.mapper.HomeClientMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeClientService {
    private final HomeClientMapper mapper;

    public List<HomeClient> list() { return mapper.findAll(); }

    public HomeClient create(HomeClient item) {
        if (item.getSortOrder() == null) item.setSortOrder(0);
        mapper.insert(item);
        return item;
    }

    public HomeClient update(Long id, HomeClient item) {
        item.setId(id);
        mapper.update(item);
        return item;
    }

    public void delete(Long id) {
        if (mapper.deleteById(id) == 0) throw new BusinessException(404, "客户不存在");
    }
}
