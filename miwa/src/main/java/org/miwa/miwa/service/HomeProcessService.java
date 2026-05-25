package org.miwa.miwa.service;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.entity.HomeProcess;
import org.miwa.miwa.exception.BusinessException;
import org.miwa.miwa.mapper.HomeProcessMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeProcessService {
    private final HomeProcessMapper mapper;

    public List<HomeProcess> list() { return mapper.findAll(); }

    public HomeProcess create(HomeProcess item) {
        if (item.getSortOrder() == null) item.setSortOrder(0);
        mapper.insert(item);
        return item;
    }

    public HomeProcess update(Long id, HomeProcess item) {
        item.setId(id);
        mapper.update(item);
        return item;
    }

    public void delete(Long id) {
        if (mapper.deleteById(id) == 0) throw new BusinessException(404, "流程不存在");
    }
}
