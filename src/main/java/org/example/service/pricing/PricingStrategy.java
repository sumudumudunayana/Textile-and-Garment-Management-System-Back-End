package org.example.service.pricing;

import org.example.dto.ReadyMadeOrderCreate;

public interface PricingStrategy {
    PricingResult price(double subtotal, ReadyMadeOrderCreate dto);
}
