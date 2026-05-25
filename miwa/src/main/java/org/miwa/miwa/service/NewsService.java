package org.miwa.miwa.service;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.PageResult;
import org.miwa.miwa.dto.SortRequest;
import org.miwa.miwa.entity.News;
import org.miwa.miwa.exception.BusinessException;
import org.miwa.miwa.mapper.NewsMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsMapper newsMapper;

    public PageResult<News> list(int page, int pageSize, String sort) {
        String[] parts = sort.split(",");
        String sortField = "sort_order".equals(parts[0]) ? "sort_order" : "created_at";
        String dir = parts.length > 1 && "desc".equalsIgnoreCase(parts[1]) ? "DESC" : "ASC";
        int offset = (page - 1) * pageSize;
        List<News> records = newsMapper.findPage(offset, pageSize, sortField, dir);
        long total = newsMapper.count();
        return PageResult.of(records, total, page, pageSize);
    }

    public News getById(Long id) {
        News news = newsMapper.findById(id);
        if (news == null) throw new BusinessException(404, "新闻不存在");
        return news;
    }

    public News create(News news) {
        if (news.getSortOrder() == null) news.setSortOrder(0);
        newsMapper.insert(news);
        return news;
    }

    public News update(Long id, News news) {
        News existing = getById(id);
        if (news.getDate() != null) existing.setDate(news.getDate());
        if (news.getTitle() != null) existing.setTitle(news.getTitle());
        if (news.getImage() != null) existing.setImage(news.getImage());
        if (news.getExcerpt() != null) existing.setExcerpt(news.getExcerpt());
        if (news.getContent() != null) existing.setContent(news.getContent());
        if (news.getSortOrder() != null) existing.setSortOrder(news.getSortOrder());
        newsMapper.update(existing);
        return existing;
    }

    public void delete(Long id) {
        getById(id);
        newsMapper.deleteById(id);
    }

    public void updateSort(SortRequest request) {
        List<News> items = new ArrayList<>();
        for (SortRequest.SortItem si : request.getItems()) {
            News n = new News();
            n.setId(si.getId());
            n.setSortOrder(si.getSortOrder());
            items.add(n);
        }
        newsMapper.updateSortOrders(items);
    }
}
