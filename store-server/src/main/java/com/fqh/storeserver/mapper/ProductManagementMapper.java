package com.fqh.storeserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fqh.storeserver.entity.Product;
import org.apache.ibatis.annotations.Select;

public interface ProductManagementMapper extends BaseMapper<Product> {

    @Select("SELECT * FROM product WHERE id = #{id}")
    Product selectById(Long id);

}
