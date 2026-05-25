package org.miwa.miwa.mapper;

import org.apache.ibatis.annotations.*;
import org.miwa.miwa.entity.HomeHero;

@Mapper
public interface HomeHeroMapper {

    @Select("SELECT * FROM home_hero ORDER BY id DESC LIMIT 1")
    HomeHero findLatest();

    @Insert("INSERT INTO home_hero (zh, en, sub) VALUES (#{zh}, #{en}, #{sub})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(HomeHero hero);
}
