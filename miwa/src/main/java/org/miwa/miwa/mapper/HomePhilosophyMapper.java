package org.miwa.miwa.mapper;

import org.apache.ibatis.annotations.*;
import org.miwa.miwa.entity.HomePhilosophy;

@Mapper
public interface HomePhilosophyMapper {
    @Select("SELECT * FROM home_philosophy ORDER BY id DESC LIMIT 1")
    HomePhilosophy findLatest();

    @Insert("INSERT INTO home_philosophy (zh, en, sub) VALUES (#{zh}, #{en}, #{sub})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(HomePhilosophy item);

    @Update("UPDATE home_philosophy SET zh=#{zh}, en=#{en}, sub=#{sub} WHERE id=#{id}")
    int update(HomePhilosophy item);
}
