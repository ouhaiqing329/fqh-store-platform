package com.fqh.storeserver.service;



import com.fqh.storeserver.dto.CartDTO;
import com.fqh.storeserver.dto.CartItemDTO;

import java.util.List;

public interface CartService {
    CartDTO getCart(String userId);

    void addToCart(String userId, String productId, Integer quantity);

    void updateQuantity(String userId, String productId, Integer quantity);

    void selectItem(String userId, String productId, Boolean selected);

    void selectAll(String userId, Boolean selected);

    void deleteItem(String userId, String productId);

    void clearCart(String userId);

    List<CartItemDTO> getSelectedItems(String userId);
}