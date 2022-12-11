package com.nphase.service;

import com.nphase.entity.BulkDiscount;
import com.nphase.entity.CategoryDiscount;
import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCartService {

    public BigDecimal calculateTotalPrice(ShoppingCart shoppingCart) {
        Map<String, Integer> productsInCategory = calculateNumberOfProductsInCategory(shoppingCart);

        return shoppingCart.getProducts()
                .stream()
                .map(product -> product.getPricePerUnit()
                        .multiply(BigDecimal.valueOf(product.getQuantity()))
                        .multiply(getBulkDiscountMultipler(shoppingCart.getBulkDiscount(), product))
                        .multiply(getCategoryDiscountMultiplier(
                                shoppingCart.getCategoryDiscount(),
                                productsInCategory.getOrDefault(product.getCategory(), 0))))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal getBulkDiscountMultipler(BulkDiscount bulkDiscount, Product product) {
        return BigDecimal.valueOf(100)
                .subtract(
                        product.getQuantity() >= bulkDiscount.getMinimumAmount() ?
                                bulkDiscount.getDiscountPercentage() : BigDecimal.ZERO)
                .divide(BigDecimal.valueOf(100));
    }

    private BigDecimal getCategoryDiscountMultiplier(CategoryDiscount categoryDiscount, int categoryQuantity) {
        return BigDecimal.valueOf(100)
                .subtract(
                        categoryQuantity >=
                                categoryDiscount.getMinimumAmount() ?
                                categoryDiscount.getDiscountPercentage() : BigDecimal.ZERO)
                .divide(BigDecimal.valueOf(100));
    }

    private Map<String, Integer> calculateNumberOfProductsInCategory(ShoppingCart shoppingCart) {
        Map<String, Integer> productsInCategory = new HashMap<>();
        shoppingCart.getProducts().forEach(product -> {
            if (product.getCategory() != null) {
                productsInCategory.put(
                        product.getCategory(),
                        productsInCategory.getOrDefault(product.getCategory(), 0) + product.getQuantity());
            }
        });
        return productsInCategory;
    }


}
