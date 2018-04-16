/**
 * 
 */

var config = {
	apiKey: "AIzaSyC6ClUVqUFLcfXz8LZYZ0Fg1WpyRn1p-2Q",
	authDomain: "cs310-p2groupn.firebaseapp.com",
	databaseURL: "https://cs310-p2groupn.firebaseio.com",
	projectId: "cs310-p2groupn",
	storageBucket: "cs310-p2groupn.appspot.com"
};

firebase.initializeApp(config);
	  
var rootRef = firebase.database().ref();
	  
document.getElementById("acct_button").onclick = function() {  
	console.log("log in button clicked"); 
		  
	var email = document.querySelector("#emailinput").value; 
	var password = document.querySelector("#passinput").value;
		  
//	firebase.database().ref('users').set({
//		username: name,
//		password: pass,
//	});
	
	firebase.auth().signInWithEmailAndPassword(email, password).catch(function(error) {
		// Handle Errors here.
		var errorCode = error.code;
		var errorMessage = error.message;
		// ...
		console.log(error.code + errorMessage);
	});

}

document.getElementById("register_button").onclick = function() {
	console.log("register button clicked"); 
		  
	var email = document.querySelector("#newemail").value; 
	var password = document.querySelector("#newpw").value;
	var confirmPassword = document.querySelector("#confirmpw").value;
	
	if (password != confirmPassword) {
		// OUTPUT ERROR HERE
		console.log("passwords do not match");
		alert("Passwords do not match!");
	}
	else {
		console.log("creating user");
		firebase.auth().createUserWithEmailAndPassword(email, password).catch(function(error) {
			// Handle Errors here.
			var errorCode = error.code;
			var errorMessage = error.message;
			// ...
			console.log(error.code + errorMessage);
			alert(error.code + " " + errorMessage);
		});
	}
}

firebase.auth().onAuthStateChanged(function(user) {
	if (user) {
		// User is signed in.
		window.location.href = "main.jsp";
	} else {
		// No user is signed in.
		console.log("could not sign in");
	}
});

function blurFunc() {
	var imgToBlur = document.getElementById("catTest");
	imgToBlur.setAttribute('class', 'blur');
}

function newUserFunc() {
	document.getElementById("outercontainer").style.display = "none";
	document.getElementById("newusercontainer").style.display = "block";
}

function toggleShowError() {
	var errorContainer = document.getElementById("errorcontainer");
	if (errorContainer.style.display == "none") {
		errorContainer.style.display = "block";
	}
	else {
		errorContainer.style.display = "none";
	}
}