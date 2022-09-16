package org.shwaeki.dao;

import org.shwaeki.models.Coupon;

import java.util.ArrayList;

public interface CouponsDAO {

    void addCoupon(Coupon coupon);

    void updateCoupon(Coupon coupon);

    void deleteCoupon(int couponID);

    ArrayList<Coupon> gatAllCoupon();

    Coupon gatOneCoupon(int couponID);

    void addCouponPurchase(int customerID, int couponID);

    void deleteCouponPurchase(int customerID, int couponID);
}
