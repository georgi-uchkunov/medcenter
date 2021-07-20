$(function() {

	var getAllUserTests = function() {
		$.ajax({
			method : "GET",
			url : "/getAllMedTests"
		}).done(
				function(response) {
					for (var i = 0; i < response.content.length; i++) {
						var currentMedTest = response.content[i];
						renderMedTest(currentMedTest.id, currentMedTest.testDate, currentMedTest.patient.patientName, currentMedTest.testResult, currentMedTest.patient.phoneNumber);
					}

				}).fail(function(response) {
		})
	}

	var renderMedTest = function(id, testDate, patientName, testResult, phoneNumber) {

		var $template = $("#template-test").html();
		$template = $($template);

		$template.find(".patient-name").text(patientName);
		$template.find(".test-date").text(testDate);
		$template.find(".phone-number").text(phoneNumber);
		$template.find(".test-result").text(testResult);

		var $testsList = $("#test-list");
		$testsList.append($template);
	}
	
	var getUsername = function() {
		$.ajax({
			method : "GET",
			url : "getCurrentUsername"
		}).done(
				function(response) {
					$("#username-nav").text("Hello, " + response);

				}).fail(function(response) {
		})
	}
	
	$('.modal.draggable>.modal-dialog').draggable({
	    cursor: 'move',
	    handle: '.modal-header'
	});
	
	$('.modal.draggable>.modal-dialog>.modal-content>.modal-header').css('cursor', 'move');

	getUsername();
	getAllUserTests();

})