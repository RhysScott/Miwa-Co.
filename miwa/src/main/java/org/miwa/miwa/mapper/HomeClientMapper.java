package org.miwa.miwa.mapper;

import org.apache.ibatis.annotations.*;
import org.miwa.miwa.entity.HomeClient;
import java.util.List;

@Mapper
public interface HomeClientMapper {
    @Select("SELECT * FROM home_client ORDER BY sort_order")
    List<HomeClient> findAll();

    @Insert("INSERT INTO home_client (name, zh, sort_order) VALUES (#{name}, #{zh}, #{sortOrder})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(HomeClient item);

    @Update("UPDATE home_client SET name=#{name}, zh=#{zh}, sort_order=#{sortOrder} WHERE id=#{id}")
    int update(HomeClient item);

    @Delete("DELETE FROM home_client WHERE id=#{id}")
    int deleteById(Long id);
}
