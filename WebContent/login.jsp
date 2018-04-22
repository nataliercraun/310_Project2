<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<link rel="stylesheet" href="css/login.css">
		<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script src="https://www.gstatic.com/firebasejs/4.12.0/firebase.js"></script>
	</head>
	
	<body>
		<h1 class = "header">
			COLLAGE BUILDER
		</h1>
		<div id="outercontainer" onclick="blurFunc()">
			<div id="emailRow" class="form-row1">
				<label>Email: </label> <input id="emailinput" type="text" name="email" />
			</div>
			<div id="passRow" class="form-row1">
				<label>Password:</label> <input type="password" id="passinput" name="pw" />
			</div>
			<div class="form-row" style="text-align:center;">
				<button id="acct_button" class="btn btn-warning">Login</button>
			</div>
			<h4 id="register">New user? Register <b id="newUserClick" onclick="newUserFunc()">here</b>.</h4>  
		</div>
		
		<div id="newusercontainer">
			<div id="topRow" class="form-row">
				<label>Email: </label> <input type="text" id="newemail" />
			</div>
			<div class="form-row">
				<label>Password:</label> <input type="password" id="newpw" />
			</div>
			<div class="form-row">
				<label>Confirm Password:</label> <input type="password" id="confirmpw" />
			</div>
		  	<div class="form-row-button" style="text-align:center;">
				<button class="btn btn-warning" id="register_button">Register</button>
				<button class="btn btn-warning" id="back_button">Go Back</button>
		    </div>
		</div>
		
		<div id="errorcontainer">
			<h3 id = "errorMessage">ERROR: INCORRECT EMAIL OR PASSWORD</h3>
		</div>
		
		<img src = "img/dog.jpg" class="backgroundImg" id="catTest"/> 
		
		<script type="text/javascript" src="js/login.js"></script>
	</body>

</html>
