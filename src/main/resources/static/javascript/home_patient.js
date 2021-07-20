$(function() {

	var getUserMedTests = function() {
		$.ajax({
			method : "GET",
			url : "getPatientMedTests",
		}).done(
				function(response) {
					console.log(response);
					for (var i = 0; i < response.length; i++) {
						var currentMedTest = response[i];
						renderMedTest(currentMedTest.id, currentMedTest.patient.patientName, currentMedTest.testResult, currentMedTest.patient.phoneNumber);
					}

				}).fail(function(response) {
					console.log("1");
			console.log(response);
		})
	}

	var renderMedTest = function(id, patientName, testResult, phoneNumber) {

		var $template = $("#template-test").html();
		$template = $($template);

		$template.find(".patient-name").text(patientName);
		$template.find(".phone-number").text(phoneNumber);
		$template.find(".test-result").text(testResult);

		var $testsList = $("#test-list");
		$testsList.append($template);
	}

	getUserMedTests();

})