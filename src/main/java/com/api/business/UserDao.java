package com.api.business;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class UserDao {

	public UserDao() {
	}

	List<User> getList() {
		return ImmutableList.of(new User("login1", "account"), new User("login2", "account"));
	}
}
