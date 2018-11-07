package com.czxy.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 *@author XueQiWen
 *@email xqwQAQwq@163.com
 *@Date  M
*/
public interface MapperUtils<T> extends Mapper<T>, MySqlMapper<T> {

}
