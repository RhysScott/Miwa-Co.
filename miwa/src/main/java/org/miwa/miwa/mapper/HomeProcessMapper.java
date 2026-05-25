package org.miwa.miwa.mapper;

import org.apache.ibatis.annotations.*;
import org.miwa.miwa.entity.HomeProcess;
import java.util.List;

@Mapper
public interface HomeProcessMapper {
    @Select("SELECT id, zh, en, desc_cn, sort_order, created_at FROM home_process ORDER BY sort_order")
    List<HomeProcess> findAll();

    @Insert("INSERT INTO home_process (zh, en, desc_cn, sort_order) VALUES (#{zh}, #{en}, #{descCn}, #{sortOrder})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(HomeProcess item);

    @Update("UPDATE home_process SET zh=#{zh}, en=#{en}, desc_cn=#{descCn}, sort_order=#{sortOrder} WHERE id=#{id}")
    int update(HomeProcess item);

    @Delete("DELETE FROM home_process WHERE id=#{id}")
    int deleteById(Long id);
}
