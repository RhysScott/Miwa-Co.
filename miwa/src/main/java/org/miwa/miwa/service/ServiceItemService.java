package org.miwa.miwa.service;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.SortRequest;
import org.miwa.miwa.entity.ServiceItem;
import org.miwa.miwa.exception.BusinessException;
import org.miwa.miwa.mapper.ServiceItemMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceItemService {

    private final ServiceItemMapper serviceItemMapper;

    public List<ServiceItem> list() {
        return serviceItemMapper.findAll();
    }

    public ServiceItem getById(Long id) {
        ServiceItem s = serviceItemMapper.findById(id);
        if (s == null) throw new BusinessException(404, "服务不存在");
        return s;
    }

    public ServiceItem create(ServiceItem item) {
        if (item.getSortOrder() == null) item.setSortOrder(0);
        serviceItemMapper.insert(item);
        return item;
    }

    public ServiceItem update(Long id, ServiceItem item) {
        ServiceItem existing = getById(id);
        if (item.getZh() != null) existing.setZh(item.getZh());
        if (item.getEn() != null) existing.setEn(item.getEn());
        if (item.getDesc() != null) existing.setDesc(item.getDesc());
        if (item.getDetail() != null) existing.setDetail(item.getDetail());
        if (item.getIcon() != null) existing.setIcon(item.getIcon());
        if (item.getImage() != null) existing.setImage(item.getImage());
        if (item.getSortOrder() != null) existing.setSortOrder(item.getSortOrder());
        serviceItemMapper.update(existing);
        return existing;
    }

    public void delete(Long id) {
        getById(id);
        serviceItemMapper.deleteById(id);
    }

    public void updateSort(SortRequest request) {
        List<ServiceItem> items = new ArrayList<>();
        for (SortRequest.SortItem si : request.getItems()) {
            ServiceItem s = new ServiceItem();
            s.setId(si.getId());
            s.setSortOrder(si.getSortOrder());
            items.add(s);
        }
        serviceItemMapper.updateSortOrders(items);
    }
}
