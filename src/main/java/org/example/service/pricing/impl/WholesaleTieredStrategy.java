package org.example.service.pricing.impl;

import org.example.dto.ReadyMadeOrderCreate;
import org.example.service.pricing.PricingResult;
import org.example.service.pricing.PricingStrategy;
import org.springframework.stereotype.Component;

@Component("wholesaleTiered")
public class WholesaleTieredStrategy implements PricingStrategy {
    @Override
    public PricingResult price(double subtotal, ReadyMadeOrderCreate dto) {
        double dp = subtotal >= 200_000 ? 12.0 :
                subtotal >= 100_000 ? 8.0  :
                        subtotal >=  50_000 ? 5.0  : 0.0;
        double da = Math.round(subtotal * (dp / 100.0) * 100.0) / 100.0;
        double total = Math.round((subtotal - da) * 100.0) / 100.0;
        return new PricingResult(dp, da, total);
    }
}
