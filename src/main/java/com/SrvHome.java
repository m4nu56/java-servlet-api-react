package com;

import com.api.business.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SrvHome extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");

		// initialize user if not exists in session
		if (user == null) {
			user = new User("m4nu56", "dev10");
		}

		// handle form submit to update user login
		if (request.getParameter("login") != null) {
			user.setLogin(request.getParameter("login"));
		}

		// set user in session
		request.getSession().setAttribute("user", user);

		// forward to the jsp
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
