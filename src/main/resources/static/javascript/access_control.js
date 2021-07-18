$(function() {

	var preventPatientAccess = function() {
		$.ajax({
			method : "GET",
			url : "access_control_patient",
		}).done(function(response) {
			if (response != "ACCEPTED") {
				window.location = response;
			}
		});
	}

	preventPatientAccess();

})