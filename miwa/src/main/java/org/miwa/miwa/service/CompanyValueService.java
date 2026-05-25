package org.miwa.miwa.service;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.SortRequest;
import org.miwa.miwa.entity.CompanyValue;
import org.miwa.miwa.exception.BusinessException;
import org.miwa.miwa.mapper.CompanyValueMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyValueService {

    private final CompanyValueMapper companyValueMapper;

    public List<CompanyValue> list() {
        return companyValueMapper.findAll();
    }

    public CompanyValue getById(Long id) {
        CompanyValue v = companyValueMapper.findById(id);
        if (v == null) throw new BusinessException(404, "价值观不存在");
        return v;
    }

    public CompanyValue create(CompanyValue value) {
        if (value.getSortOrder() == null) value.setSortOrder(0);
        companyValueMapper.insert(value);
        return value;
    }

    public CompanyValue update(Long id, CompanyValue value) {
        CompanyValue existing = getById(id);
        if (value.getZh() != null) existing.setZh(value.getZh());
        if (value.getEn() != null) existing.setEn(value.getEn());
        if (value.getDesc() != null) existing.setDesc(value.getDesc());
        if (value.getSortOrder() != null) existing.setSortOrder(value.getSortOrder());
        companyValueMapper.update(existing);
        return existing;
    }

    public void delete(Long id) {
        getById(id);
        companyValueMapper.deleteById(id);
    }

    public void updateSort(SortRequest request) {
        List<CompanyValue> items = new ArrayList<>();
        for (SortRequest.SortItem si : request.getItems()) {
            CompanyValue v = new CompanyValue();
            v.setId(si.getId());
            v.setSortOrder(si.getSortOrder());
            items.add(v);
        }
        companyValueMapper.updateSortOrders(items);
    }
}
