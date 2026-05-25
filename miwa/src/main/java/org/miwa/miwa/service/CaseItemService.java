package org.miwa.miwa.service;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.SortRequest;
import org.miwa.miwa.entity.CaseItem;
import org.miwa.miwa.exception.BusinessException;
import org.miwa.miwa.mapper.CaseItemMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CaseItemService {

    private final CaseItemMapper caseItemMapper;

    public List<CaseItem> list() {
        return caseItemMapper.findAll();
    }

    public CaseItem getById(Long id) {
        CaseItem c = caseItemMapper.findById(id);
        if (c == null) throw new BusinessException(404, "案例不存在");
        return c;
    }

    public CaseItem create(CaseItem item) {
        if (item.getSortOrder() == null) item.setSortOrder(0);
        caseItemMapper.insert(item);
        return item;
    }

    public CaseItem update(Long id, CaseItem item) {
        CaseItem existing = getById(id);
        if (item.getZh() != null) existing.setZh(item.getZh());
        if (item.getDetail() != null) existing.setDetail(item.getDetail());
        if (item.getSortOrder() != null) existing.setSortOrder(item.getSortOrder());
        caseItemMapper.update(existing);
        return existing;
    }

    public void delete(Long id) {
        getById(id);
        caseItemMapper.deleteById(id);
    }

    public void updateSort(SortRequest request) {
        List<CaseItem> items = new ArrayList<>();
        for (SortRequest.SortItem si : request.getItems()) {
            CaseItem c = new CaseItem();
            c.setId(si.getId());
            c.setSortOrder(si.getSortOrder());
            items.add(c);
        }
        caseItemMapper.updateSortOrders(items);
    }
}
