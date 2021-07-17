$(function() {
	
	$("#register").on("click", function() {

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
        
        console.log(gender);

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
			//window.location = "/patient_dashboard";
		});
	})

})