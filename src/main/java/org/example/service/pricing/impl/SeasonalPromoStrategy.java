package org.example.service.pricing.impl;

import org.example.dto.ReadyMadeOrderCreate;
import org.example.service.pricing.PricingResult;
import org.example.service.pricing.PricingStrategy;
import org.springframework.stereotype.Component;

@Component("seasonalPromo")
public class SeasonalPromoStrategy implements PricingStrategy {
    @Override
    public PricingResult price(double subtotal, ReadyMadeOrderCreate dto) {
        //   LKR 1,500 off if subtotal >= 15,000, but cap discount at 10%
        double flatOff = subtotal >= 15_000 ? 1_500 : 0;
        double cap     = Math.round(subtotal * 0.10 * 100.0) / 100.0; // 10% cap
        double discountAmount = Math.min(flatOff, cap);
        double total = Math.round((subtotal - discountAmount) * 100.0) / 100.0;
        double discountPercent = subtotal == 0 ? 0 :
                Math.round((discountAmount / subtotal) * 10000.0) / 100.0; // to 2dp

        return new PricingResult(discountPercent, discountAmount, total);
    }

}
