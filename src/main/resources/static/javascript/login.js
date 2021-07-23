$(function() {
	
	$("#login").on("click", function() {
		loginValidation();
		
	})

	var loginValidation = function(){
		var $username = $("#username");
		var $password = $("#password");

		var usernameStatus = $username[0].classList[2];
		var passwordStatus = $password[0].classList[2];

		if (usernameStatus == "is-valid" && passwordStatus == "is-valid") {
			proceedLogin();
		}
	}

	var proceedLogin = function(){
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
	}

	$("#username").on('change', function () {
		var $username = $("#username");
		var username = $username.val();
		if (username.length > 3 && username != 'name') {
			$username[0].classList.remove('is-invalid');
			$username[0].classList.add('is-valid');
		} else {
			$username[0].classList.remove('is-valid');
			$username[0].classList.add('is-invalid');
		}
	})

	$("#password").on('change', function () {
		var $password = $("#password");
		var password = $password.val();
		var validation = /^(?=.*?[0-9])(?=.*?[a-zA-Z]).{3,30}$/;
		if (validation.test(password)) {
		$password[0].classList.remove('is-invalid');
		$password[0].classList.add('is-valid');
		} else {
		$password[0].classList.remove('is-valid');
		$password[0].classList.add('is-invalid');
		}
	})

})