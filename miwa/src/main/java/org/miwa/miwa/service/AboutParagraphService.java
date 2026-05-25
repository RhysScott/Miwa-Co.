package org.miwa.miwa.service;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.entity.AboutParagraph;
import org.miwa.miwa.exception.BusinessException;
import org.miwa.miwa.mapper.AboutParagraphMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AboutParagraphService {
    private final AboutParagraphMapper mapper;

    public List<AboutParagraph> listByLang(String lang) { return mapper.findByLang(lang); }

    public List<AboutParagraph> listAll() { return mapper.findAll(); }

    public AboutParagraph create(AboutParagraph item) {
        if (item.getSortOrder() == null) item.setSortOrder(0);
        mapper.insert(item);
        return item;
    }

    public AboutParagraph update(Long id, AboutParagraph item) {
        item.setId(id);
        mapper.update(item);
        return item;
    }

    public void delete(Long id) {
        if (mapper.deleteById(id) == 0) throw new BusinessException(404, "段落不存在");
    }
}
