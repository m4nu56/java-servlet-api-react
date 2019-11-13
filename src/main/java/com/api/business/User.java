package com.api.business;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Principal;

@Data
@NoArgsConstructor
public class User implements Principal {
	private String login;
	private String account;

	public User(String login, String account) {
		this.login = login;
		this.account = account;
	}

	public String getName() {
		return login;
	}
}
