package com.epam.engx.cleancode.naming.delivery;

import com.epam.engx.cleancode.naming.IOrderService;

import com.epam.engx.cleancode.naming.thirdpartyjar.*;

import java.util.List;

public class DeliveryOrderService implements IOrderService {

    private DeliveryService deliveryService;

    private OrderFulfilmentService orderFulfilmentService;

    public void submitOrder(Order productsOrder) {
        if (deliveryService.isDeliverable(productsOrder)) {
            List<Product> products = productsOrder.getProducts();
            orderFulfilmentService.fulfilProducts(products);
        } else {
            throw new NotDeliverableOrderException();
        }
    }

    public void setDeliveryService(DeliveryService deliveryServiceImpl) {
        this.deliveryService = deliveryServiceImpl;
    }

    public void setOrderFulfilmentService(OrderFulfilmentService orderFulfilmentServiceImpl) {
        this.orderFulfilmentService = orderFulfilmentServiceImpl;
    }
}
