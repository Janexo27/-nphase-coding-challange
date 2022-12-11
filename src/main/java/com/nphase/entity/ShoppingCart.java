package com.nphase.entity;

import java.util.List;

public class ShoppingCart {
    private final List<Product> products;
    private final BulkDiscount bulkDiscount;
    private final CategoryDiscount categoryDiscount;

    public ShoppingCart(List<Product> products, BulkDiscount bulkDiscount, CategoryDiscount categoryDiscount) {
        this.products = products;
        this.bulkDiscount = bulkDiscount;
        this.categoryDiscount = categoryDiscount;
    }

    public ShoppingCart(List<Product> products) {
        this.products = products;
        bulkDiscount = new BulkDiscount();
        categoryDiscount = new CategoryDiscount();
    }

    public List<Product> getProducts() {
        return products;
    }

    public BulkDiscount getBulkDiscount() {
        return bulkDiscount;
    }

    public CategoryDiscount getCategoryDiscount() {
        return categoryDiscount;
    }
}
