package com.dailycodework.dreamshops.Services.cart;

import com.dailycodework.dreamshops.model.CartItem;
import jakarta.persistence.Id;

public interface ICartItemService {
    void addItemToCart(Long cartId, Long productId, int quantity);
    void removeItemFromCart(Long cartId, Long productId);
    void updateItemQuantity(Long cartId, Long productId, int quantity);
}
