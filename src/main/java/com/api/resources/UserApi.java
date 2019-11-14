package com.api.resources;

import com.api.business.User;
import com.api.business.UserSvc;
import com.api.injection.CurrentUser;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("users")
public class UserApi {

    @Inject
    private UserSvc userSvc;

	@CurrentUser
    private User user;

    @GET
    @Path("/who-am-i")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserLogged() {
        return user;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getList() {
        return userSvc.getList();
    }


}
