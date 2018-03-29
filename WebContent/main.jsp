<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%--
  User: Natalie
  Date: 3/28/18
--%>
<html>
	<head>
		<!-- Google font --> 
		<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
		<link rel="stylesheet" href="css/main.css" type="text/css">
		<script src="main.js"></script>
		<title> Main Web Page </title>
	</head>
	<body>
		<h1 id="header">
			Collage Display 
		</h1>
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
					  <option value=""> Normal </option>
					  <option value="grayscale(100%)">Black & White </option>
					  <option value="sepia(100%)">Sephia</option>
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
						 <img id="collageImage" src="cozy.jpg">
					</div>
				</div>
			</div>
			<div id="saveBtns">
				<button class="save" id="saveToHistoryBtn"> Save to History  </button>
				<button class="save" id="exportBtn"> Export </button>
			</div>
		</div>
		<div id="galleryContainer"> 
			<img class="galleryImages" src="images/fleece-pant.jpg">
			<img class="galleryImages" src="images/tommy.jpg">
			<img class="galleryImages" src="images/usc.jpg">
			<img class="galleryImages" src="images/shorts.jpg">
		</div>

		<script> 

			document.addEventListener('click', function(e) {
			    e = e || window.event;
			    var target = e.target; 
			    console.log(e.target.className)
			    if (e.target.className == 'galleryImages') {
			    	var source = e.target.src; 
			    	console.log(source); 
			    	document.querySelector("#collageImage").src = source;
			    }
			}, false);

			/* Function to change collage width */
			document.querySelector("#widthSubmit").onclick = function() {
				if (document.querySelector("#widthBox").value > 500) {
					alert("Value greater than 500px")
				} else {
					document.querySelector("#collageDisplay").style.width = document.querySelector("#widthBox").value + 'px'; 

				}
			}

			/* Function to change collage height */
			document.querySelector("#heightSubmit").onclick = function() {
				if (document.querySelector("#heightBox").value > 500) {
					alert("Value greater than 500px"); 
				} else {
					document.querySelector("#collageDisplay").style.height = document.querySelector("#heightBox").value + 'px'; 
				}
			}

			/* Function to populate collage display */
			document.querySelector("#buildCollageBtn").onclick = function() {
				if ((document.querySelector("#shapeBox").value != '') && (document.querySelector("#topicBox").value != '')) {
					console.log("build collage button pressed"); 

					/* This is where we need to call the Google Search API and 
					   generate the letter shaped collage  */

					/* document.querySelector("#collageImage").src = "INSERT IMG URL HERE"; */

				} else {
					alert("Please ensure both shape and topic are given")
				}
			}

			/* Allows submit buttons to be triggered when the user presses 'enter' */
			document.getElementById("widthBox")
			    .addEventListener("keyup", function(event) {
			    event.preventDefault();
			    if (event.keyCode === 13) {
			        document.getElementById("widthSubmit").click();
			    }
			});

			document.getElementById("heightBox")
			    .addEventListener("keyup", function(event) {
			    event.preventDefault();
			    if (event.keyCode === 13) {
			        document.getElementById("heightSubmit").click();
			    }
			});

			document.getElementById("topicBox")
			    .addEventListener("keyup", function(event) {
			    event.preventDefault();
			    if (event.keyCode === 13) {
			        document.getElementById("buildCollageBtn").click();
			    }
			});

			document.getElementById("shapeBox")
			    .addEventListener("keyup", function(event) {
			    event.preventDefault();
			    if (event.keyCode === 13) {
			        document.getElementById("buildCollageBtn").click();
			    }
			});

			/* Collage Options Filter Effects */

			function filter() {
				document.querySelector("#collageImage").style.filter = document.getElementById("filterValue").value; 
			}
			
		</script>

	</body>
</html>