<%@ page language="java" contentType="text/html; charset=utf-8" %>

<%
	pageContext.setAttribute("sessionId", request.getSession().getId());
	pageContext.setAttribute("user", request.getSession().getAttribute("user"));
%>

<!doctype html>
<html lang="en">
<head>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

	<title>Migrate your existing Java Servlet jsp app to a modern API + React stack</title>
</head>
<body>

<div class="body-content">

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">Servlet</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link" href="http://localhost:8080/webapp">Module on Java Servlet <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="http://localhost:3000">Module on React SPA</a>
				</li>
			</ul>
		</div>
	</nav>

	<div class="container">
		<h1>Migrate your existing Java Servlet jsp app to a modern API + React stack</h1>

		<br/>

		<div class='alert alert-dark'>
			This is the Java Servlet JSP !
		</div>

		<h2>Session</h2>
		<ul class="list-group">
			<li class="list-group-item"><span style="font-weight: bold;">${sessionId}</span></li>
			<li class="list-group-item"><span style="font-weight: bold;">${user}</span></li>
		</ul>

		<br/>

		<h2>Update the user login in session</h2>
		<div class="alert alert-primary">You can update the user login in HttpSession using the below form</div>
		<form action="">
			<div class="form-group">
				<label for="login">Login</label>
				<input type="text" class="form-control" id="login" name="login">
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>

	</div>

</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>
</html>
