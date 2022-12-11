package com.nphase.entity;

import java.math.BigDecimal;

public class CategoryDiscount {

    private final int minimumAmount;

    private final BigDecimal discountPercentage;

    public CategoryDiscount(int minimumAmount, BigDecimal discountPercentage) {
        this.minimumAmount = minimumAmount;
        this.discountPercentage = discountPercentage;
    }

    public CategoryDiscount() {
        this.minimumAmount = 0;
        this.discountPercentage = BigDecimal.ZERO;
    }

    public int getMinimumAmount() {
        return minimumAmount;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

}
