<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		
		<title>Log In</title>
		<link rel="stylesheet" href="css/login.css">
		<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script src="https://www.gstatic.com/firebasejs/4.12.0/firebase.js"></script>
	</head>
	
	<body>
		<h1 class = "header">
			COLLAGE BUILDER
		</h1>
		<div id="outercontainer" onclick="blurFunc()">
			<h3 id="title">LOG IN</h3>
			<div class="form-row">
				<label>Email: </label> <input id="emailinput" type="text" name="email" />
			</div>
			<div class="form-row">
				<label>Password:</label> <input type="password" id="passinput" name="pw" />
			</div>
			<div class="form-row" style="text-align:center;">
				<button id="acct_button">Submit</button>
			</div>
			<h4 id="register">New user? Register <b id="newUserClick" onclick="newUserFunc()">here</b>.</h4>  
		</div>
		
		<div id="newusercontainer">
			<h3 id="title">REGISTER</h3>
			<div class="form-row">
				<label>Email: </label> <input type="text" id="newemail" />
			</div>
			<div class="form-row">
				<label>Password:</label> <input type="password" id="newpw" />
			</div>
			<div class="form-row">
				<label>Confirm Password:</label> <input type="password" id="confirmpw" />
			</div>
		  	<div class="form-row" style="text-align:center;">
				<button id="register_button">Register</button>
		    </div>
		</div>
		
		<div id="errorcontainer">
			<h3 id = "errorMessage">ERROR: INCORRECT EMAIL OR PASSWORD</h3>
		</div>
		
		<img src = "img/dog.jpg" class="backgroundImg" id="catTest"/> 
		
		<script type="text/javascript" src="js/login.js"></script>
	</body>

</html>
