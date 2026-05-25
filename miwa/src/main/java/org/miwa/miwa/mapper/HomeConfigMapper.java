package org.miwa.miwa.mapper;

import org.apache.ibatis.annotations.*;
import org.miwa.miwa.entity.HomeConfig;

@Mapper
public interface HomeConfigMapper {

    @Select("SELECT * FROM home_config ORDER BY id DESC LIMIT 1")
    HomeConfig findLatest();

    @Insert("INSERT INTO home_config (config) VALUES (#{config})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(HomeConfig config);
}
