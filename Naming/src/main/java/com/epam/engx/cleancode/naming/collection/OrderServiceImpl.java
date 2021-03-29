package com.epam.engx.cleancode.naming.collection;


import com.epam.engx.cleancode.naming.IOrderService;
import com.epam.engx.cleancode.naming.thirdpartyjar.CollectionService;
import com.epam.engx.cleancode.naming.thirdpartyjar.Message;
import com.epam.engx.cleancode.naming.thirdpartyjar.NotificationManager;
import com.epam.engx.cleancode.naming.thirdpartyjar.Order;

public class OrderServiceImpl implements IOrderService {

    private CollectionService collectionService;
    private NotificationManager notificationManager;

    public void submitOrder(Order productsOrder) {
        if (collectionService.isEligibleForCollection(productsOrder))
            notificationManager.notifyCustomer(Message.READY_FOR_COLLECT, 4); // 4 - info notification level
        else
            notificationManager.notifyCustomer(Message.IMPOSSIBLE_TO_COLLECT, 1); // 1 - critical notification level
    }

    public void setCollectionService(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    public void setNotificationManager(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }
}
