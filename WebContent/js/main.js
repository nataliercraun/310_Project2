/**
 * 
 */
firebase.auth().onAuthStateChanged(function(user) {
	if (user) {
		// User is signed in.
		// Do nothing
	} else {
		// No user is signed in.
		window.location.href = "login.jsp";
		alert("You were logged out.");
	}
});

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

/* Function to log out user */
document.querySelector("#logOutBtn").onclick = function() {
	firebase.auth().signOut().then(function() {
		// Sign-out successful.
		console.log("log out successful");
		window.location.href = "login.jsp";
	}).catch(function(error) {
		// An error happened.
		alert("Something went wrong with log out")
	});
}

/* Function to populate collage display */
document.querySelector("#buildCollageBtn").onclick = function() {
	if ((document.querySelector("#shapeBox").value != '') && (document.querySelector("#topicBox").value != '')) {
		console.log("build collage button pressed");
		
		/* This is where we need to send data to the servlet and 
		   generate the letter shaped collage  */
		var topicString = document.querySelector("#topicBox").value;
		var shapeString = document.querySelector("#shapeBox").value;
		var bordersOption = document.querySelector("#borderBox").checked;
		var rotateOption = document.querySelector("#rotateBox").checked;
		var width = document.querySelector("#widthBox").value;
		var height = document.querySelector("#heightBox").value;
		
		var xhttp = new XMLHttpRequest();
		
		
		xhttp.open("GET", "http://localhost:8080/310_Project2"+"/CollageBuilderServlet?topic="+topicString+"&shape="+shapeString+"&borders="+bordersOption+"&rotate="+rotateOption+"&width="+width+"&height="+height, true);
		xhttp.send();
		
		xhttp.onreadystatechange = function() {
			if (xhttp.responseText.length > 0){
				document.querySelector("#collageImage").src = xhttp.responseText;
				console.log(xhttp.responseText);
			}
		};

		/* document.querySelector("#collageImage").src = "INSERT IMG URL HERE"; */

	} else {
		alert("Please ensure both shape and topic are given")
	}
}

/* Function to save currently displayed collage to history (database) */
document.querySelector("#saveToHistoryBtn").onclick = function() {
	if (document.querySelector("#collageImage").getAttr('src') == "") { 
		console.log("ERROR: save to history button pressed when no collage present");
		/* If the src attribute is empty, this section of code should not be reachable */
		/* The save to history button should be disabled */
	} else {
		/* This is where we should save to the database */
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