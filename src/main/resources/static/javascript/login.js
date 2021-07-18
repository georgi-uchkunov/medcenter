$(function() {
	
	$("#login").on("click", function() {

		var username = $("#username").val();
		var password = $("#password").val();

		$.ajax({
			method : "POST",
			url : "login",
			data : {
				username : username,
				password : password
			}
		}).done(function(response) {
			console.log(response);
			window.location = response;
		});
	})

})