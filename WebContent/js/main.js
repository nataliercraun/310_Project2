/**
 *  JavaScript functions for main.jsp
 */

// Get a reference to the storage service, which is used to create references in your storage bucket
var storage = firebase.storage();
// Create a storage reference from our storage service
var storageRef = storage.ref();
//Create a database reference from our database service
var databaseRef = firebase.database().ref();
// Array of saved urls for gallery display
var galleryImageUrls = [];

firebase.auth().onAuthStateChanged(function(user) {
	if (user) {
		
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
		//show animated busy symbol at this point
		//document.getElementById("#animSymb").style = "visibility:none";
		//document.getElementById("#collageImage").innerHTML = document.getElementById("#animSymb").innerHTML;
		/* This is where we need to send data to the servlet and generate the letter shaped collage */
		var topicString = document.querySelector("#topicBox").value;
		var shapeString = document.querySelector("#shapeBox").value;
		var bordersOption = document.querySelector("#borderBox").checked;
		var rotateOption = document.querySelector("#rotateBox").checked;
		var width = document.querySelector("#widthBox").value;
		var height = document.querySelector("#heightBox").value;
		var xhttp = new XMLHttpRequest();
		
		xhttp.open("GET", "http://localhost:8080/310_Project2"+"/CollageBuilderServlet?topic="+topicString+"&shape="+shapeString+"&borders="+bordersOption+"&rotate="+rotateOption+"&width="+width+"&height="+height, true);
		xhttp.send();
		
		//use this for animated busy symbol - state to indicate ready
		xhttp.onreadystatechange = function() {
			if (xhttp.responseText.length > 0){
				//hide animated busy symbol now
				//document.getElementById("#animSymb").hide();
				
				document.querySelector("#collageImage").src = xhttp.responseText;
			}
		};
	} else {
		alert("Please ensure both shape and topic are given")
	}
}

/* Function to save currently displayed collage to history (database) */
document.querySelector("#saveToHistoryBtn").onclick = function() {
	if (document.querySelector("#collageImage").getAttribute('src') == "") { 
		console.log("ERROR: save to history button pressed when no collage present");
		/* If the src attribute is empty, this section of code should not be reachable */
	} else {
		/* Create reference to uploaded image */
		var collageRef = storageRef.child(document.querySelector("#topicBox").value + '.png');
		/* Save the Base64 formatted string to the database */
		var image = document.querySelector("#collageImage").src;
		/* Create metadata */
		var metadata = {	 contentType: 'image/png' };
		/* Variable for upload task to track status of upload */
		collageRef.putString(image, 'data_url', metadata).then(function(snapshot){
			console.log("url success, attempting to push to DB");
			
			firebase.database().ref('savedCollages').push().set({
				email: firebase.auth().currentUser.email,
				collageURL: snapshot.downloadURL,
			});
			console.log("DB push success!");
			
			updateGalleryArray();
			
		}).catch(function(error) {
			console.log("No URL");
			console.log(error);
		});
	}
}

/* Function to update history gallery */
function updateGalleryArray() {
	/* Clear the gallery images so we don't double push */
	galleryImageUrls = [];
	firebase.database().ref('savedCollages').once("value").then(function(snapshot) {
		for (var key in snapshot.val()) {
			/* If the email of image matches current user, push to gallery array */
			if (snapshot.val()[key].email === firebase.auth().currentUser.email){
				var url = snapshot.val()[key].collageURL;
				galleryImageUrls.push(url);
			}
		}
		for (var urlIndex in galleryImageUrls) {
			console.log("url: " + galleryImageUrls[urlIndex]);
		}
		/* Update the gallery history here */
	});
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