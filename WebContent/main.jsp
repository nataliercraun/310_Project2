<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<!-- Google font --> 
		<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
		<link rel="stylesheet" href="css/main.css" type="text/css">
		<title> Main Web Page </title>
	</head>
	<body>
		<h1 id="header">
			Collage Display 
		</h1>
		<button id="logOutBtn"> Log Out </button>
		<div id="container">
			<div id="optionsContainer">
				<h2> Build Collage </h2>
				<div class="horizRow">
					<label for="topicBox"> Topic </label>
					<input id="topicBox" type="text"> 
				</div>
				<div class="horizRow">
					<label for="topicBox"> Shape </label>
					<input id="shapeBox" type="text"> 
				</div>
				<button id="buildCollageBtn" type="submit"> Build Collage </button>
				<h2> Collage Options  </h2>
				<div class="horizRow">
					<label for="dropdownContainer"> Filter Type </label>
					<div id="dropdownContainer">
					<select id="filterValue" onchange="filter()">
					  <option value="none"> Normal </option>
					  <option value="grayscale(100%)">Black & White </option>
					  <option value="sepia(100%)">Sepia</option>
					</select>
					</div>
				</div>
				<div class="horizRow">
					<label for="borderBox"> Borders </label>
					<input id="borderBox" type="checkbox"> 
				</div>
				<div class="horizRow">
					<label for="rotateBox"> Rotation </label>
					<input id="rotateBox" type="checkbox">
				</div>
				<div class="horizRow">
					<label for="widthBox"> Width </label>
					<input id="widthBox" type="text" placeholder="Max 500px">
					<button id="widthSubmit" type="submit"> Update </button>
				</div>
				<div class="horizRow">
					<label for="heightBox"> Height </label>
					<input id="heightBox" type="text" placeholder="Max 500px">
					<button id="heightSubmit" type="submit"> Update </button>
				</div>
			</div>
			<div id="maxSizeCollageContainer">
				<div id="collageContainer" class="row">
					<div id="collageDisplay" class="item">
						 <img id="collageImage" src="">
					</div>
				</div>
			</div>
			<div id="saveBtns">
				<button class="save" id="saveToHistoryBtn"> Save to History  </button>
				<button class="save" id="exportBtn"> Export </button>
			</div>
		</div>
		<!-- Include Firebase -->
		<script src="https://www.gstatic.com/firebasejs/4.12.0/firebase.js"></script>
		<!-- Initialize Firebase --> 
		<script>
			// Set up the configuration for firebase app
			var config = {
				apiKey: "AIzaSyC6ClUVqUFLcfXz8LZYZ0Fg1WpyRn1p-2Q",
				authDomain: "cs310-p2groupn.firebaseapp.com",
				databaseURL: "https://cs310-p2groupn.firebaseio.com",
				projectId: "cs310-p2groupn",
				storageBucket: "cs310-p2groupn.appspot.com",
			};
			firebase.initializeApp(config);
			
			// Get a reference to the database service
			var database = firebase.database();
			// Get a reference to the storage service, which is used to create references in your storage bucket
		  	var storage = firebase.storage();
			// Create a storage reference from our storage service
		  	var storageRef = storage.ref();
		</script>
		
		<script type="text/javascript" src="js/main.js"></script>
	</body>
</html>