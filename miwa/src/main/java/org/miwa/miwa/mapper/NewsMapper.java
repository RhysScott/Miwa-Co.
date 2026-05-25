package org.miwa.miwa.mapper;

import org.apache.ibatis.annotations.*;
import org.miwa.miwa.entity.News;
import java.util.List;

@Mapper
public interface NewsMapper {

    @Select("SELECT * FROM news ORDER BY sort_order ASC, created_at DESC")
    List<News> findAll();

    @Select("SELECT * FROM news ORDER BY ${sort} ${dir} LIMIT #{offset}, #{pageSize}")
    List<News> findPage(@Param("offset") int offset, @Param("pageSize") int pageSize,
                        @Param("sort") String sort, @Param("dir") String dir);

    @Select("SELECT COUNT(*) FROM news")
    long count();

    @Select("SELECT * FROM news WHERE id = #{id}")
    News findById(@Param("id") Long id);

    @Insert("INSERT INTO news (date, title, image, excerpt, content, sort_order) " +
            "VALUES (#{date}, #{title}, #{image}, #{excerpt}, #{content}, #{sortOrder})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(News news);

    @Update("UPDATE news SET date = #{date}, title = #{title}, image = #{image}, " +
            "excerpt = #{excerpt}, content = #{content}, sort_order = #{sortOrder} WHERE id = #{id}")
    int update(News news);

    @Delete("DELETE FROM news WHERE id = #{id}")
    int deleteById(@Param("id") Long id);

    @Update("<script>" +
            "<foreach collection='items' item='item' separator=';'>" +
            "UPDATE news SET sort_order = #{item.sortOrder} WHERE id = #{item.id}" +
            "</foreach>" +
            "</script>")
    int updateSortOrders(@Param("items") List<News> items);
}
