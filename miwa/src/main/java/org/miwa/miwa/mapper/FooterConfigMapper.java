package org.miwa.miwa.mapper;

import org.apache.ibatis.annotations.*;
import org.miwa.miwa.entity.FooterConfig;

@Mapper
public interface FooterConfigMapper {

    @Select("SELECT * FROM footer_config ORDER BY id DESC LIMIT 1")
    FooterConfig findLatest();

    @Insert("INSERT INTO footer_config (email, phone, address, copyright, location, en_email, en_phone, en_address) " +
            "VALUES (#{email}, #{phone}, #{address}, #{copyright}, #{location}, #{enEmail}, #{enPhone}, #{enAddress})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(FooterConfig config);
}
