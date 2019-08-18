package com.oop;

/**
 * DiscountCondition
 */
public interface DiscountCondition {

    boolean isSatisfiedBy(Screening screening);
}