package com.api.business;

import javax.inject.Inject;
import java.util.List;

public class UserSvc {

	@Inject
	private UserDao userDao;

	public List<User> getList() {
		return userDao.getList();
	}
}
