package com.nphase.service;


import com.nphase.entity.BulkDiscount;
import com.nphase.entity.CategoryDiscount;
import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

public class ShoppingCartServiceTest {
    private final ShoppingCartService service = new ShoppingCartService();

    @Test
    public void calculatesPrice()  {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(5.0), 1),
                new Product("Coffee", BigDecimal.valueOf(3.5), 2)
        ));

        BigDecimal result = service.calculateTotalPrice(cart);

        Assertions.assertEquals(0, result.compareTo(BigDecimal.valueOf(12)));
    }

    @Test
    public void calculateBulkDiscountedPrice() {
        BulkDiscount bulkDiscount = new BulkDiscount(4, BigDecimal.valueOf(10));
        CategoryDiscount categoryDiscount = new CategoryDiscount(4, BigDecimal.valueOf(10));
        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(5.0), 5),
                new Product("Coffee", BigDecimal.valueOf(3.5), 3)
        ), bulkDiscount, categoryDiscount);

        BigDecimal result = service.calculateTotalPrice(cart);

        Assertions.assertEquals(0, result.compareTo(BigDecimal.valueOf(33)));
    }

    @Test
    public void calculateCategoryDiscountedPrice() {
        BulkDiscount bulkDiscount = new BulkDiscount(4, BigDecimal.valueOf(10));
        CategoryDiscount categoryDiscount = new CategoryDiscount(4, BigDecimal.valueOf(10));
        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(5.3), 2, "drinks"),
                new Product("Coffee", BigDecimal.valueOf(3.5), 2 , "drinks"),
                new Product("Cheese", BigDecimal.valueOf(8), 2, "food")
        ), bulkDiscount, categoryDiscount);

        BigDecimal result = service.calculateTotalPrice(cart);

        Assertions.assertEquals(0, result.compareTo(BigDecimal.valueOf(31.84)));
    }

    @Test
    public void calculateBothDiscounts() {
        BulkDiscount bulkDiscount = new BulkDiscount(4, BigDecimal.valueOf(10));
        CategoryDiscount categoryDiscount = new CategoryDiscount(5, BigDecimal.valueOf(10));
        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(5.3), 5, "drinks"),
                new Product("Coffee", BigDecimal.valueOf(3.5), 2 , "drinks"),
                new Product("Cheese", BigDecimal.valueOf(8), 4, "food")
        ), bulkDiscount, categoryDiscount);

        BigDecimal result = service.calculateTotalPrice(cart);

        Assertions.assertEquals(0, result.compareTo(BigDecimal.valueOf(56.565)));
    }

}