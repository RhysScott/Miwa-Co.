package org.miwa.miwa.mapper;

import org.apache.ibatis.annotations.*;
import org.miwa.miwa.entity.HomeMarquee;
import java.util.List;

@Mapper
public interface HomeMarqueeMapper {
    @Select("SELECT * FROM home_marquee ORDER BY sort_order")
    List<HomeMarquee> findAll();

    @Insert("INSERT INTO home_marquee (zh, en, sort_order) VALUES (#{zh}, #{en}, #{sortOrder})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(HomeMarquee item);

    @Update("UPDATE home_marquee SET zh=#{zh}, en=#{en}, sort_order=#{sortOrder} WHERE id=#{id}")
    int update(HomeMarquee item);

    @Delete("DELETE FROM home_marquee WHERE id=#{id}")
    int deleteById(Long id);
}
