package com.api.resources;

import com.api.ApiApplication;
import com.api.business.User;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import utils.mocker.MockAuthorizationJWT;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UserApiTest extends JerseyTest {

	@Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);

		return new ApiApplication();
	}

	@Test
	public void whoAmI() {
		final Response response = target("users/who-am-i")
			.request(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, MockAuthorizationJWT.mockAuthorizationJWT("m4nu56", "dev10"))
			.get();

		// Buffer entity.
		response.bufferEntity();

		if (response.getStatus() != Response.Status.OK.getStatusCode()) {
			fail(response.getStatus() + ": " + response.readEntity(String.class));

		} else {
			assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
			User u = response.readEntity(User.class);
			assertEquals("m4nu56", u.getLogin());
			assertEquals("dev10", u.getAccount());
		}
	}

	@Test
	public void getList() {
		final Response response = target("users")
			.request(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, MockAuthorizationJWT.mockAuthorizationJWT("m4nu56", "dev10"))
			.get();

		// Buffer entity.
		response.bufferEntity();

		if (response.getStatus() != Response.Status.OK.getStatusCode()) {
			fail(response.getStatus() + ": " + response.readEntity(String.class));

		} else {
			assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
			List<User> users = response.readEntity(new GenericType<List<User>>() {});
			assertEquals(2, users.size());
		}
	}

}
