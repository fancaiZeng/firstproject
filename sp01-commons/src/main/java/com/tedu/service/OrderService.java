package com.tedu.service;

import com.tedu.sp01.pojo.Order;

public interface OrderService {
	Order getOrder(String orderId);
	void addOrder(Order order);
}