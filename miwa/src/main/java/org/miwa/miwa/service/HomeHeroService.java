package org.miwa.miwa.service;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.entity.HomeHero;
import org.miwa.miwa.mapper.HomeHeroMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeHeroService {

    private final HomeHeroMapper mapper;

    public HomeHero get() {
        return mapper.findLatest();
    }

    public void save(HomeHero hero) {
        mapper.insert(hero);
    }
}
