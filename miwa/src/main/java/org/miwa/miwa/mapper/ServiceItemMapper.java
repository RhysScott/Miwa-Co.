package org.miwa.miwa.mapper;

import org.apache.ibatis.annotations.*;
import org.miwa.miwa.entity.ServiceItem;
import java.util.List;

@Mapper
public interface ServiceItemMapper {

    @Select("SELECT id, zh, en, desc_cn AS `desc`, detail, icon, image, sort_order, created_at, updated_at " +
            "FROM services ORDER BY sort_order ASC")
    List<ServiceItem> findAll();

    @Select("SELECT id, zh, en, desc_cn AS `desc`, detail, icon, image, sort_order, created_at, updated_at " +
            "FROM services WHERE id = #{id}")
    ServiceItem findById(@Param("id") Long id);

    @Insert("INSERT INTO services (zh, en, desc_cn, detail, icon, image, sort_order) " +
            "VALUES (#{zh}, #{en}, #{desc}, #{detail}, #{icon}, #{image}, #{sortOrder})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(ServiceItem item);

    @Update("UPDATE services SET zh = #{zh}, en = #{en}, desc_cn = #{desc}, " +
            "detail = #{detail}, icon = #{icon}, image = #{image}, sort_order = #{sortOrder} WHERE id = #{id}")
    int update(ServiceItem item);

    @Delete("DELETE FROM services WHERE id = #{id}")
    int deleteById(@Param("id") Long id);

    @Update("<script>" +
            "<foreach collection='items' item='item' separator=';'>" +
            "UPDATE services SET sort_order = #{item.sortOrder} WHERE id = #{item.id}" +
            "</foreach>" +
            "</script>")
    int updateSortOrders(@Param("items") List<ServiceItem> items);
}
