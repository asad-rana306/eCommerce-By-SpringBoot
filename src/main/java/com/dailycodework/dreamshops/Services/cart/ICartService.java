package com.dailycodework.dreamshops.Services.cart;

import com.dailycodework.dreamshops.model.Cart;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);

}
