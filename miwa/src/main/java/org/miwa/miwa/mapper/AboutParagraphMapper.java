package org.miwa.miwa.mapper;

import org.apache.ibatis.annotations.*;
import org.miwa.miwa.entity.AboutParagraph;
import java.util.List;

@Mapper
public interface AboutParagraphMapper {
    @Select("SELECT * FROM about_paragraph WHERE lang=#{lang} ORDER BY sort_order")
    List<AboutParagraph> findByLang(String lang);

    @Select("SELECT * FROM about_paragraph ORDER BY lang, sort_order")
    List<AboutParagraph> findAll();

    @Insert("INSERT INTO about_paragraph (lang, content, sort_order) VALUES (#{lang}, #{content}, #{sortOrder})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(AboutParagraph item);

    @Update("UPDATE about_paragraph SET content=#{content}, sort_order=#{sortOrder} WHERE id=#{id}")
    int update(AboutParagraph item);

    @Delete("DELETE FROM about_paragraph WHERE id=#{id}")
    int deleteById(Long id);
}
