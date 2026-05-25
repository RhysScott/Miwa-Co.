package org.miwa.miwa.mapper;

import org.apache.ibatis.annotations.*;
import org.miwa.miwa.entity.CompanyValue;
import java.util.List;

@Mapper
public interface CompanyValueMapper {

    @Select("SELECT id, zh, en, desc_cn AS `desc`, sort_order, created_at, updated_at " +
            "FROM company_values ORDER BY sort_order ASC")
    List<CompanyValue> findAll();

    @Select("SELECT id, zh, en, desc_cn AS `desc`, sort_order, created_at, updated_at " +
            "FROM company_values WHERE id = #{id}")
    CompanyValue findById(@Param("id") Long id);

    @Insert("INSERT INTO company_values (zh, en, desc_cn, sort_order) " +
            "VALUES (#{zh}, #{en}, #{desc}, #{sortOrder})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(CompanyValue value);

    @Update("UPDATE company_values SET zh = #{zh}, en = #{en}, desc_cn = #{desc}, " +
            "sort_order = #{sortOrder} WHERE id = #{id}")
    int update(CompanyValue value);

    @Delete("DELETE FROM company_values WHERE id = #{id}")
    int deleteById(@Param("id") Long id);

    @Update("<script>" +
            "<foreach collection='items' item='item' separator=';'>" +
            "UPDATE company_values SET sort_order = #{item.sortOrder} WHERE id = #{item.id}" +
            "</foreach>" +
            "</script>")
    int updateSortOrders(@Param("items") List<CompanyValue> items);
}
