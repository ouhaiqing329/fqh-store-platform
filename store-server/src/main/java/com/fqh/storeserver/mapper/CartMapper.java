package com.fqh.storeserver.mapper;

import com.fqh.storeserver.entity.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {
    @Insert("INSERT INTO cart(user_id, product_id, quantity, selected) VALUES(#{userId}, #{productId}, #{quantity}, #{selected})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Cart cart);

    @Update("UPDATE cart SET quantity = #{quantity}, selected = #{selected} WHERE id = #{id}")
    int update(Cart cart);

    @Delete("DELETE FROM cart WHERE id = #{id}")
    int delete(String id);

    @Delete("DELETE FROM cart WHERE user_id = #{userId}")
    int clearCart(String userId);

    @Select("SELECT * FROM cart WHERE user_id = #{userId}")
    @Results({@Result(property = "product", column = "product_id", one = @One(select = "com.example.shoppingcart.mapper.ProductMapper.selectById"))})
    List<Cart> selectByUserId(String userId);

    @Select("SELECT * FROM cart WHERE user_id = #{userId} AND product_id = #{productId}")
    Cart selectByUserIdAndProductId(@Param("userId") String userId, @Param("productId") String productId);
}