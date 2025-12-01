package org.example.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.ReadyMadeOrder;
import org.example.dto.ReadyMadeOrderCreate;
import org.example.dto.ReadyMadeOrderCreateItem;
import org.example.dto.ReadyMadeOrderItem;
import org.example.entity.ReadyMadeOrderEntity;
import org.example.entity.ReadyMadeOrderItemEntity;
import org.example.repository.ReadyMadeOrderItemRepository;
import org.example.repository.ReadyMadeOrderRepository;
import org.example.service.ReadyMadeOrderService;
import org.example.service.pricing.PricingResult;
import org.example.service.pricing.PricingStrategy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReadyMadeOrderSeviceImpl implements ReadyMadeOrderService {

    private final ReadyMadeOrderRepository orderRepo;
    private final ReadyMadeOrderItemRepository itemRepo;
    private final Map<String, PricingStrategy> pricingStrategies;

    @Override
    @Transactional
    public List<ReadyMadeOrder> getAll() {
        List<ReadyMadeOrder> out = new ArrayList<>();
        orderRepo.findAll(Sort.by(Sort.Direction.DESC, "readyMadeOrderId"))
                .forEach(e -> out.add(toDTO(e)));
        return out;
    }


    @Override
    @Transactional
    public ReadyMadeOrder createDetailedOrder(ReadyMadeOrderCreate dto) {
        ReadyMadeOrderEntity order = new ReadyMadeOrderEntity();
        order.setCustomerId(dto.getCustomerId());
        order.setCustomerName(dto.getCustomerName());
        order.setDate(dto.getDate());

        List<ReadyMadeOrderItemEntity> items = new ArrayList<>();
        double subtotal = 0d;

        if (dto.getItems() != null) {
            for (ReadyMadeOrderCreateItem it : dto.getItems()) {
                ReadyMadeOrderItemEntity ie = new ReadyMadeOrderItemEntity();
                ie.setProductId(it.getProductId());
                ie.setItemName(it.getName());
                ie.setItemPrice(safe(it.getUnitPrice()));
                ie.setQuantity(it.getQty());
                double line = round2(safe(it.getUnitPrice()) * (it.getQty() == null ? 0 : it.getQty()));
                ie.setLineTotal(line);
                ie.setOrder(order);
                items.add(ie);
                subtotal += line;
            }
        }
        subtotal = round2(subtotal);

        // ---- Strategy selection & pricing ----
        String key = (dto.getPricingStrategy() == null || dto.getPricingStrategy().isBlank())
                ? "flatPercent" : dto.getPricingStrategy();

        PricingStrategy strategy =
                pricingStrategies.getOrDefault(key, pricingStrategies.get("flatPercent"));

        PricingResult priced = strategy.price(subtotal, dto);

        order.setSubtotal(subtotal);
        order.setDiscountPercent(priced.discountPercent());
        order.setDiscountAmount(priced.discountAmount());
        order.setTotalAmount(priced.total());

        // ---- Persist ----
        order = orderRepo.save(order);

        for (ReadyMadeOrderItemEntity ie : items) {
            ie.setOrder(order);
        }
        itemRepo.saveAll(items);
        order.setItems(items);

        return toDTO(order);
    }


    @Override
    @Transactional
    public List<ReadyMadeOrder> getDetailedOrders() {
        List<ReadyMadeOrderEntity> orders =
                orderRepo.findAll(Sort.by(Sort.Direction.DESC, "readyMadeOrderId"));
        List<ReadyMadeOrder> out = new ArrayList<>();
        for (ReadyMadeOrderEntity e : orders) {
            out.add(toDTO(e));
        }
        return out;
    }


    @Override
    @Transactional
    public void deleteReadyMadeOrderById(Integer id) {
        orderRepo.deleteById(id);
    }

    @Override
    @Transactional
    public ReadyMadeOrder searchReadyMadeOrderById(Integer id) {
        return orderRepo.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    @Override
    @Transactional
    public void updateReadyMadeOrderById(ReadyMadeOrder readyMadeOrder) {
        throw new UnsupportedOperationException("Implement update mapping as needed");
    }

    private ReadyMadeOrder toDTO(ReadyMadeOrderEntity e) {
        double subtotal = e.getItems() == null ? 0d
                : e.getItems().stream().mapToDouble(it -> safe(it.getLineTotal())).sum();

        // If entity field is "discount" (percent):
        double discountPercent = safe(e.getDiscountPercent());
        // If entity field is "discountPercent", use:
        double discountAmount  = round2(subtotal * (discountPercent / 100.0));
        double total           = e.getTotalAmount() != null
                ? safe(e.getTotalAmount())
                : round2(subtotal - discountAmount);

        List<ReadyMadeOrderItem> items = new ArrayList<>();
        if (e.getItems() != null) {
            for (ReadyMadeOrderItemEntity it : e.getItems()) {
                items.add(new ReadyMadeOrderItem(
                        it.getProductId(),
                        it.getItemName(),
                        safe(it.getItemPrice()),
                        it.getQuantity(),
                        safe(it.getLineTotal())
                ));
            }
        }

        ReadyMadeOrder dto = new ReadyMadeOrder();
        dto.setReadyMadeOrderId(e.getReadyMadeOrderId());
        dto.setCustomerId(e.getCustomerId());
        dto.setCustomerName(e.getCustomerName());
        dto.setDate(e.getDate());
        dto.setSubtotal(round2(subtotal));
        dto.setDiscountPercent(discountPercent);
        dto.setDiscountAmount(discountAmount);
        dto.setTotalAmount(total);
        dto.setItems(items);
        return dto;
    }

    private static double safe(Double d) { return d == null ? 0d : d; }
    private static double round2(double v) { return Math.round(v * 100.0) / 100.0; }
}
