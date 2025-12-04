package org.example.service.pricing.impl;

import org.example.dto.ReadyMadeOrderCreate;
import org.example.service.pricing.PricingResult;
import org.example.service.pricing.PricingStrategy;
import org.springframework.stereotype.Component;

@Component("flatPercent")
public class FlatPercentStrategy implements PricingStrategy {
    @Override
    public PricingResult price(double subtotal, ReadyMadeOrderCreate dto) {
        double dp = dto.getDiscountPercent() == null ? 0d : dto.getDiscountPercent();
        double da = Math.round(subtotal * (dp / 100.0) * 100.0) / 100.0;
        double total = Math.round((subtotal - da) * 100.0) / 100.0;
        return new PricingResult(dp, da, total);
    }
}
