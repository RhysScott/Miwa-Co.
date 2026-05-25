package org.miwa.miwa.mapper;

import org.apache.ibatis.annotations.*;
import org.miwa.miwa.entity.HomeStat;
import java.util.List;

@Mapper
public interface HomeStatMapper {
    @Select("SELECT * FROM home_stat ORDER BY sort_order")
    List<HomeStat> findAll();

    @Insert("INSERT INTO home_stat (num, zh, en, sort_order) VALUES (#{num}, #{zh}, #{en}, #{sortOrder})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(HomeStat item);

    @Update("UPDATE home_stat SET num=#{num}, zh=#{zh}, en=#{en}, sort_order=#{sortOrder} WHERE id=#{id}")
    int update(HomeStat item);

    @Delete("DELETE FROM home_stat WHERE id=#{id}")
    int deleteById(Long id);
}
