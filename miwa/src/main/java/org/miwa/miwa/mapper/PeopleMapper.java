package org.miwa.miwa.mapper;

import org.apache.ibatis.annotations.*;
import org.miwa.miwa.entity.Person;
import java.util.List;

@Mapper
public interface PeopleMapper {

    @Select("SELECT id, name, en, role_cn AS role, role_en AS roleEn, bio, image, content, " +
            "sort_order, created_at, updated_at FROM people ORDER BY sort_order ASC")
    List<Person> findAll();

    @Select("SELECT id, name, en, role_cn AS role, role_en AS roleEn, bio, image, content, " +
            "sort_order, created_at, updated_at FROM people WHERE id = #{id}")
    Person findById(@Param("id") Long id);

    @Insert("INSERT INTO people (name, en, role_cn, role_en, bio, image, content, sort_order) " +
            "VALUES (#{name}, #{en}, #{role}, #{roleEn}, #{bio}, #{image}, #{content}, #{sortOrder})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Person person);

    @Update("UPDATE people SET name = #{name}, en = #{en}, role_cn = #{role}, role_en = #{roleEn}, " +
            "bio = #{bio}, image = #{image}, content = #{content}, sort_order = #{sortOrder} WHERE id = #{id}")
    int update(Person person);

    @Delete("DELETE FROM people WHERE id = #{id}")
    int deleteById(@Param("id") Long id);

    @Update("<script>" +
            "<foreach collection='items' item='item' separator=';'>" +
            "UPDATE people SET sort_order = #{item.sortOrder} WHERE id = #{item.id}" +
            "</foreach>" +
            "</script>")
    int updateSortOrders(@Param("items") List<Person> items);
}
