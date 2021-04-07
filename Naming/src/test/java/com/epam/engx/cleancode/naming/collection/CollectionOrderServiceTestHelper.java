package com.epam.engx.cleancode.naming.collection;

import com.epam.engx.cleancode.naming.thirdpartyjar.CollectionService;
import com.epam.engx.cleancode.naming.thirdpartyjar.Submitable;

public class CollectionOrderServiceTestHelper {

    public OrderServiceImpl getService(){
        return new OrderServiceImpl();
    }

    public void submit(Submitable collectOrderService) {
        ((OrderServiceImpl) collectOrderService).submitOrder(new OrderDummy());
    }

    public void setNotificationManager(NotificationManagerMock notificationManagerMock, Submitable collectOrderService) {
        ((OrderServiceImpl) collectOrderService).setNotificationManager(notificationManagerMock);
    }

    public void setCollectionService(Submitable collectOrderService, CollectionService collectionServiceStub) {
        ((OrderServiceImpl) collectOrderService).setCollectionService(collectionServiceStub);
    }
}
