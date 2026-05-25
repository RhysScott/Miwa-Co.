package org.miwa.miwa.service;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.entity.HomePhilosophy;
import org.miwa.miwa.mapper.HomePhilosophyMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomePhilosophyService {
    private final HomePhilosophyMapper mapper;

    public HomePhilosophy get() { return mapper.findLatest(); }

    public void save(HomePhilosophy item) {
        HomePhilosophy existing = mapper.findLatest();
        if (existing != null) {
            item.setId(existing.getId());
            mapper.update(item);
        } else {
            mapper.insert(item);
        }
    }
}
