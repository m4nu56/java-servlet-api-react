package com.api.resources;

import com.api.business.User;
import com.api.business.UserSvc;
import com.api.injection.CurrentUser;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("users")
public class UserApi {

	@Inject
	private UserSvc userSvc;

	@CurrentUser
	private User user;

	@Context
	private HttpServletRequest request;

	// Used to display the user read from the JWT token
	@GET
	@Path("/who-am-i")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserLogged() {
		return user;
	}

	// Used to display the user in HttpSession
	@GET
	@Path("/session/who-am-i")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserLoggedInSession() {
		System.out.println(request.toString());
		System.out.println("SessionID:" + request.getSession().getId());
		System.out.println("User:" + request.getSession().getAttribute("user"));
		if (request.getSession().getAttribute("user") != null) {
			return (User) request.getSession().getAttribute("user");
		}
		User notFoundUser = new User();
		notFoundUser.setLogin("NotFoundUser");
		return notFoundUser;
	}

	@PUT
	@Path("/session/update")
	@Produces(MediaType.APPLICATION_JSON)
	public User updateUserInSession(@QueryParam("login") String login) {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			user.setLogin(login);
			return user;
		}
		User notFoundUser = new User();
		notFoundUser.setLogin("NotFoundUser");
		return notFoundUser;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getList() {
		return userSvc.getList();
	}


}
