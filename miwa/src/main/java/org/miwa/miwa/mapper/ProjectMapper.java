package org.miwa.miwa.mapper;

import org.apache.ibatis.annotations.*;
import org.miwa.miwa.entity.Project;
import java.util.List;

@Mapper
public interface ProjectMapper {

    @Select("SELECT id, zh, en, desc_cn AS `desc`, image, content, sort_order, created_at, updated_at " +
            "FROM projects ORDER BY sort_order ASC")
    List<Project> findAll();

    @Select("SELECT id, zh, en, desc_cn AS `desc`, image, content, sort_order, created_at, updated_at " +
            "FROM projects WHERE id = #{id}")
    Project findById(@Param("id") Long id);

    @Insert("INSERT INTO projects (zh, en, desc_cn, image, content, sort_order) " +
            "VALUES (#{zh}, #{en}, #{desc}, #{image}, #{content}, #{sortOrder})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Project project);

    @Update("UPDATE projects SET zh = #{zh}, en = #{en}, desc_cn = #{desc}, " +
            "image = #{image}, content = #{content}, sort_order = #{sortOrder} WHERE id = #{id}")
    int update(Project project);

    @Delete("DELETE FROM projects WHERE id = #{id}")
    int deleteById(@Param("id") Long id);

    @Update("<script>" +
            "<foreach collection='items' item='item' separator=';'>" +
            "UPDATE projects SET sort_order = #{item.sortOrder} WHERE id = #{item.id}" +
            "</foreach>" +
            "</script>")
    int updateSortOrders(@Param("items") List<Project> items);
}
