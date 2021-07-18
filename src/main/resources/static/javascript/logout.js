$(function() {
	
	$("#logout").on("click", function() {

		$.ajax({
			method : "POST",
			url : "logout",

		}).done(function(response) {
			window.location = response;
		});
	})

})