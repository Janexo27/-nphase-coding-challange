package com.nphase.entity;

import java.math.BigDecimal;

public class BulkDiscount {

    private final int minimumAmount;

    private final BigDecimal discountPercentage;

    public BulkDiscount(int minimumAmount, BigDecimal discountPercentage) {
        this.minimumAmount = minimumAmount;
        this.discountPercentage = discountPercentage;
    }

    public BulkDiscount() {
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
