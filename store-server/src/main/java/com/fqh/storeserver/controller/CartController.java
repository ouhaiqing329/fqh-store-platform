package com.fqh.storeserver.controller;

import com.fqh.storeserver.dto.CartDTO;
import com.fqh.storeserver.dto.CartItemDTO;
import com.fqh.storeserver.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public CartDTO getCart(@RequestAttribute String userId) {
        return cartService.getCart(userId);
    }

    @PostMapping("/add")
    public void addToCart(@RequestAttribute String userId,
                          @RequestParam String productId,
                          @RequestParam(defaultValue = "1") Integer quantity) {
        cartService.addToCart(userId, productId, quantity);
    }

    @PostMapping("/update")
    public void updateQuantity(@RequestAttribute String userId,
                               @RequestParam String productId,
                               @RequestParam Integer quantity) {
        cartService.updateQuantity(userId, productId, quantity);
    }

    @PostMapping("/select")
    public void selectItem(@RequestAttribute String userId,
                           @RequestParam String productId,
                           @RequestParam Boolean selected) {
        cartService.selectItem(userId, productId, selected);
    }

    @PostMapping("/selectAll")
    public void selectAll(@RequestAttribute String userId,
                          @RequestParam Boolean selected) {
        cartService.selectAll(userId, selected);
    }

    @DeleteMapping
    public void deleteItem(@RequestAttribute String userId,
                           @RequestParam String productId) {
        cartService.deleteItem(userId, productId);
    }

    @DeleteMapping("/clear")
    public void clearCart(@RequestAttribute String userId) {
        cartService.clearCart(userId);
    }

    @GetMapping("/selected")
    public List<CartItemDTO> getSelectedItems(@RequestAttribute String userId) {
        return cartService.getSelectedItems(userId);
    }
}