package com.tedu.service;

import java.util.List;

import com.tedu.sp01.pojo.Item;
import com.tedu.sp01.pojo.User;

public interface UserService {
	User getUser(Integer id);
	void addScore(Integer id, Integer score);
}