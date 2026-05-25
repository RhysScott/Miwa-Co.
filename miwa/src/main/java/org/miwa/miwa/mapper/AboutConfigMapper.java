package org.miwa.miwa.mapper;

import org.apache.ibatis.annotations.*;
import org.miwa.miwa.entity.AboutConfig;

@Mapper
public interface AboutConfigMapper {

    @Select("SELECT * FROM about_config ORDER BY id DESC LIMIT 1")
    AboutConfig findLatest();

    @Insert("INSERT INTO about_config (zh_text, en_text) VALUES (#{zhText}, #{enText})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(AboutConfig config);
}
