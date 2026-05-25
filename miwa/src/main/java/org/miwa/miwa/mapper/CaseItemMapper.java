package org.miwa.miwa.mapper;

import org.apache.ibatis.annotations.*;
import org.miwa.miwa.entity.CaseItem;
import java.util.List;

@Mapper
public interface CaseItemMapper {

    @Select("SELECT * FROM cases ORDER BY sort_order ASC")
    List<CaseItem> findAll();

    @Select("SELECT * FROM cases WHERE id = #{id}")
    CaseItem findById(@Param("id") Long id);

    @Insert("INSERT INTO cases (zh, detail, sort_order) VALUES (#{zh}, #{detail}, #{sortOrder})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(CaseItem item);

    @Update("UPDATE cases SET zh = #{zh}, detail = #{detail}, sort_order = #{sortOrder} WHERE id = #{id}")
    int update(CaseItem item);

    @Delete("DELETE FROM cases WHERE id = #{id}")
    int deleteById(@Param("id") Long id);

    @Update("<script>" +
            "<foreach collection='items' item='item' separator=';'>" +
            "UPDATE cases SET sort_order = #{item.sortOrder} WHERE id = #{item.id}" +
            "</foreach>" +
            "</script>")
    int updateSortOrders(@Param("items") List<CaseItem> items);
}
