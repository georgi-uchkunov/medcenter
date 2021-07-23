$(function() {
	
	$("#register").on("click", function() {
		registerValidation();
	})
	
	var registerValidation = function(){
		var $username = $("#username");
		var $password = $("#password");
		var $email = $("#email");
		var $firstName = $("#first-name");
		var $lastName = $("#last-name");
		var $address = $("#address");

		var usernameStatus = $username[0].classList[2];
		var passwordStatus = $password[0].classList[2];
		var emailStatus = $email[0].classList[2];
		var firstNameStatus = $firstName[0].classList[2];
		var lastNameStatus = $lastName[0].classList[2];
		var addressStatus = $address[0].classList[2];

		if (usernameStatus == "is-valid" && passwordStatus == "is-valid" && emailStatus == "is-valid" && 
				firstNameStatus == "is-valid" && lastNameStatus == "is-valid" && addressStatus == "is-valid") {
			proceedRegistration();
		}
	}
	
	var proceedRegistration = function(){
		var username = $("#username").val();
		var password = $("#password").val();
		var email = $("#email").val();
        var firstName = $("#first-name").val();
        var lastName = $("#last-name").val();
        var day = $("#day").val();
        var month = $("#month").val();
        var year = $("#year").val();
        var dateOfBirthString = year + "-" + month + "-" + day;
        var address = $("#address").val();
        
        var radios = document.getElementsByName("gender-radio");
        
        for(var i = 0; i < radios.length; i++) {
        	if(radios[i].checked){
        		if(i == 0){
        			var gender = $("#male-radio").val();
        		} else if(i == 1){
        			var gender = $("#female-radio").val();
        		} else if(i == 2){
        			var gender = $("#other-radio").val();
        		}
        	}
        }

		$.ajax({
			method : "POST",
			url : "registerPatient",
			data : {

                username : username,
                password : password,
                email : email,
                firstName : firstName,
                lastName : lastName,
                dateOfBirthString : dateOfBirthString,
                gender : gender,
                address : address
				
			}
		}).done(function(response) {
            
			console.log(response);
			proceedLogin();
		});
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
	
	$("#email").on('change', function () {
		var $email = $("#email");
		var email = $email.val();
		var validation = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/g;
		if (validation.test(email)) {
			$email[0].classList.remove('is-invalid');
			$email[0].classList.add('is-valid');

		} else {
			$email[0].classList.remove('is-valid');
			$email[0].classList.add('is-invalid');
		}
	})
	
	$("#first-name").on('change', function () {
		var $firstName = $("#first-name");
		var firstName = $firstName.val();
		if (firstName.length > 0 && firstName != 'name') {
			$firstName[0].classList.remove('is-invalid');
			$firstName[0].classList.add('is-valid');
		} else {
			$firstName[0].classList.remove('is-valid');
			$firstName[0].classList.add('is-invalid');
		}
	})
	
	$("#last-name").on('change', function () {
		var $lastName = $("#last-name");
		var lastName = $lastName.val();
		if (lastName.length > 0 && lastName != 'name') {
			$lastName[0].classList.remove('is-invalid');
			$lastName[0].classList.add('is-valid');
		} else {
			$lastName[0].classList.remove('is-valid');
			$lastName[0].classList.add('is-invalid');
		}
	})
	
	$("#address").on('change', function () {
		var $address = $("#address");
		var address = $address.val();
		if (address.length > 3 && address != 'address') {
			$address[0].classList.remove('is-invalid');
			$address[0].classList.add('is-valid');
		} else {
			$address[0].classList.remove('is-valid');
			$address[0].classList.add('is-invalid');	
		}
	})

})