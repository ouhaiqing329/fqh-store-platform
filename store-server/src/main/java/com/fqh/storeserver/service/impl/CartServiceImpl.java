package com.fqh.storeserver.service.impl;

import com.fqh.storeserver.config.BusinessException;
import com.fqh.storeserver.config.ExceptionCode;
import com.fqh.storeserver.dto.CartDTO;
import com.fqh.storeserver.dto.CartItemDTO;
import com.fqh.storeserver.entity.Cart;
import com.fqh.storeserver.entity.Product;
import com.fqh.storeserver.mapper.CartMapper;
import com.fqh.storeserver.mapper.ProductManagementMapper;
import com.fqh.storeserver.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductManagementMapper productMapper;

    @Override
    public CartDTO getCart(String userId) {
        List<Cart> cartList = cartMapper.selectByUserId(userId);

        List<CartItemDTO> cartItemDTOs = cartList.stream().map(cart -> {
            Product product = cart.getProduct();
            CartItemDTO itemDTO = new CartItemDTO();
            itemDTO.setProductId(product.getId());
            itemDTO.setProductName(product.getName());
            itemDTO.setProductImage(product.getImageUrl());
            itemDTO.setPrice(product.getPrice());
            itemDTO.setQuantity(cart.getQuantity());
            itemDTO.setSelected(cart.getSelected() == 1);
            itemDTO.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())));
            return itemDTO;
        }).collect(Collectors.toList());

        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartItems(cartItemDTOs);

        // 计算总金额、总数量、选中商品数量
        BigDecimal totalAmount = BigDecimal.ZERO;
        int totalQuantity = 0;
        int selectedItems = 0;

        for (CartItemDTO item : cartItemDTOs) {
            totalQuantity += item.getQuantity();
            if (item.getSelected()) {
                totalAmount = totalAmount.add(item.getTotalPrice());
                selectedItems++;
            }
        }

        cartDTO.setTotalAmount(totalAmount);
        cartDTO.setTotalQuantity(totalQuantity);
        cartDTO.setSelectedItems(selectedItems);

        return cartDTO;
    }

    @Override
    @Transactional
    public void addToCart(String userId, String productId, Integer quantity) {
        // 检查商品是否存在和库存
        Product product = productMapper.selectById(productId);
        if (product == null || product.getStatus() != 1) {
            throw new BusinessException(ExceptionCode.PRODUCT_NOT_EXIST);
        }
        if (product.getStock() < quantity) {
            throw new BusinessException(ExceptionCode.PRODUCT_STOCK_NOT_ENOUGH);
        }

        // 检查购物车是否已有该商品
        Cart cart = cartMapper.selectByUserIdAndProductId(userId, productId);
        if (cart != null) {
            // 已有商品，增加数量
            cart.setQuantity(cart.getQuantity() + quantity);
            cartMapper.update(cart);
        } else {
            // 新商品，添加到购物车
            cart = new Cart();
            cart.setUserId(userId);
            cart.setProductId(productId);
            cart.setQuantity(quantity);
            cart.setSelected(1); // 默认选中
            cartMapper.insert(cart);
        }
    }

    @Override
    @Transactional
    public void updateQuantity(String userId, String productId, Integer quantity) {
        if (quantity <= 0) {
            throw new BusinessException(ExceptionCode.INVALID_PARAMETER, "数量必须大于0");
        }

        // 检查商品库存
        Product product = productMapper.selectById(productId);
        if (product == null || product.getStatus() != 1) {
            throw new BusinessException(ExceptionCode.PRODUCT_NOT_EXIST);
        }
        if (product.getStock() < quantity) {
            throw new BusinessException(ExceptionCode.PRODUCT_STOCK_NOT_ENOUGH);
        }

        Cart cart = cartMapper.selectByUserIdAndProductId(userId, productId);
        if (cart == null) {
            throw new BusinessException(ExceptionCode.CART_ITEM_NOT_EXIST);
        }

        cart.setQuantity(quantity);
        cartMapper.update(cart);
    }

    @Override
    @Transactional
    public void selectItem(String userId, String productId, Boolean selected) {
        Cart cart = cartMapper.selectByUserIdAndProductId(userId, productId);
        if (cart == null) {
            throw new BusinessException(ExceptionCode.CART_ITEM_NOT_EXIST);
        }

        cart.setSelected(selected ? 1 : 0);
        cartMapper.update(cart);
    }

    @Override
    @Transactional
    public void selectAll(String userId, Boolean selected) {
        List<Cart> cartList = cartMapper.selectByUserId(userId);
        for (Cart cart : cartList) {
            cart.setSelected(selected ? 1 : 0);
            cartMapper.update(cart);
        }
    }

    @Override
    @Transactional
    public void deleteItem(String userId, String productId) {
        Cart cart = cartMapper.selectByUserIdAndProductId(userId, productId);
        if (cart != null) {
            cartMapper.delete(cart.getId());
        }
    }

    @Override
    @Transactional
    public void clearCart(String userId) {
        cartMapper.clearCart(userId);
    }

    @Override
    public List<CartItemDTO> getSelectedItems(String userId) {
        List<Cart> cartList = cartMapper.selectByUserId(userId);
        return cartList.stream()
                .filter(cart -> cart.getSelected() == 1)
                .map(cart -> {
                    Product product = cart.getProduct();
                    CartItemDTO itemDTO = new CartItemDTO();
                    itemDTO.setProductId(product.getId());
                    itemDTO.setProductName(product.getName());
                    itemDTO.setProductImage(product.getImageUrl());
                    itemDTO.setPrice(product.getPrice());
                    itemDTO.setQuantity(cart.getQuantity());
                    itemDTO.setSelected(true);
                    itemDTO.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())));
                    return itemDTO;
                }).collect(Collectors.toList());
    }
}