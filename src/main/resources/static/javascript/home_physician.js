$(function() {

	var getAllUserTests = function() {
		$.ajax({
			method : "GET",
			url : "/getAllMedTests"
		}).done(
				function(response) {
					for (var i = 0; i < response.content.length; i++) {
						var currentMedTest = response.content[i];
						renderMedTest(currentMedTest.id,
								currentMedTest.testDate,
								currentMedTest.patient.patientName,
								currentMedTest.testResult,
								currentMedTest.patient.phoneNumber,
								currentMedTest.patient.dna,
								currentMedTest.patient.email,
								currentMedTest.patient.dateOfBirth,
								currentMedTest.patient.gender,
								currentMedTest.patient.address,
								currentMedTest.symptom);
					}

				}).fail(function(response) {
		})
	}

	var renderMedTest = function(id, testDate, patientName, testResult,
			phoneNumber, dna, email, dateOfBirth, gender, address, symptom) {

		var $template = $("#template-test").html();
		$template = $($template);

		$template.find(".redo-test").attr("id", id);
		$template.find(".patient-name").text(patientName);
		$template.find(".test-date").text(testDate);
		$template.find(".phone-number").text(phoneNumber);
		$template.find(".test-result").text(testResult);
		$template.find(".symptom").text(symptom);
		$template.find(".patient-email").text(email);
		$template.find(".dna").text(dna);
		$template.find(".date-of-birth").text(dateOfBirth);
		$template.find(".gender").text(gender);
		$template.find(".address").text(address);

		var $testsList = $("#test-list");
		$testsList.append($template);
	}
	
	$(document).on('click', '.redo-test', function() {
		$selectedTest = $(this).closest('.list-group-item');
		var testId = $selectedTest.find('.redo-test').attr('id');
		$('#testIdPassModal').find('#testId').text(testId);
	})
	
	$("#agree-redo").on("click", function() {

		var testId = $("#testId").text();
		$.ajax({
			method : "POST",
			url : "redoDNATest",
			data : {
				testId: testId
			}
		}).done(function(response) {
			$("#redo-test-modal").modal("hide");
			var $testList = $("#test-list");
			$testList.empty();
			getAllUserTests();
		});
	})
	
	$(document).on(
			'click',
			'.list-group-item:not([type = button])',
			function() {
				$selectedTest = $(this).closest(
						'.list-group-template-test');
				$trueSelectedTest = $selectedTest.prevObject;
				var display = $trueSelectedTest.find('.more-info-div').css(
						'display');
				if (display == 'block') {
					$trueSelectedTest.find('.more-info-div').css({
						'display' : 'none',
						'opacity' : '0',
						'transition' : 'opacity 1s ease-out',
						'transition-delay' : '250ms',
						'transform' : 'translate(17%, 6%)'
					});
					$trueSelectedTest.find('.test-image-div').css({
						'display' : 'block',
						'transition' : 'opacity 1s ease-out',
						'transition-delay' : '250ms'
					});
					setTimeout(function() {
						$trueSelectedTest.find('.test-image-div').css({
							'opacity' : '1'
						});
					}, 110);

				} else {
					$trueSelectedTest.find('.more-info-div').css({
						'display' : 'block',
						'transition' : 'opacity 1s ease-out',
						'transition-delay' : '250ms'
					});
					$trueSelectedTest.find('.test-image-div').css({
						'display' : 'none',
						'opacity' : '0',
						'transition' : 'opacity 1s ease-out',
						'transition-delay' : '250ms'
					});

					setTimeout(function() {
						$trueSelectedTest.find('.more-info-div').css({
							'opacity' : '1'
						});
					}, 110);
					setTimeout(function() {
						$trueSelectedTest.find('.symptom').css({
							'opacity' : '1'
						});
					}, 110);
					setTimeout(function() {
						$trueSelectedTest.find('.symptom-label').css({
							'opacity' : '1'
						});
					}, 110);
				}
			});
	
	$("#search-button").on("click", function() {

		var searchTerm = $("#search-bar").val();

		$.ajax({
			method : "GET",
			url : "getSpecificTests",
			data : {
				
				searchTerm : searchTerm
			}
		}).done(function(response) {
			var $testList = $("#test-list");
			$testList.empty();
			for (var i = 0; i < response.length; i++) {
				var currentMedTest = response[i];
				renderMedTest(currentMedTest.id,
						currentMedTest.testDate,
						currentMedTest.patient.patientName,
						currentMedTest.testResult,
						currentMedTest.patient.phoneNumber,
						currentMedTest.patient.dna,
						currentMedTest.patient.email,
						currentMedTest.patient.dateOfBirth,
						currentMedTest.patient.gender,
						currentMedTest.patient.address,
						currentMedTest.symptom);
			}
		});
	})

	$("#submit-button").on("click", function() {
		medTestValidation();
	})
	
	var medTestValidation = function(){
		var $email = $("#email");
		var $firstName = $("#first-name");
		var $lastName = $("#last-name");
		var $phoneNumber = $("#phone-number");
		var $address = $("#address");
		var $dna = $("#dna");
		var $symptom = $("#address");

		var emailStatus = $email[0].classList[2];
		var phoneNumberStatus = $phoneNumber[0].classList[2];
		var firstNameStatus = $firstName[0].classList[2];
		var lastNameStatus = $lastName[0].classList[2];
		var addressStatus = $address[0].classList[2];
		var dnaStatus = $dna[0].classList[2];
		var symptomStatus = $symptom[0].classList[2];

		if (emailStatus == "is-valid" && firstNameStatus == "is-valid" && lastNameStatus == "is-valid" && addressStatus == "is-valid" && phoneNumberStatus == "is-valid"
			&& dnaStatus == "is-valid" && symptomStatus == "is-valid") {
			proceedMedTest();
		}
	}
	
	var proceedMedTest = function(){
		var firstName = $("#first-name").val();
		var lastName = $("#last-name").val();
		var patientName = firstName + " " + lastName;
		var email = $("#email").val();
		var countryCode = $("#country-code").val();
		var phoneNumber = $("#phone-number").val();
		var patientPhoneNumber = countryCode + " " + phoneNumber;
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

		var dna = $("#dna").val();
		var symptom = $("#symptom").val();

		$.ajax({
			method : "POST",
			url : "performDNATest",
			data : {
				patientName : patientName,
				email : email,
				phoneNumber : patientPhoneNumber,
				dateOfBirthString : dateOfBirthString,
				address : address,
				gender : gender,
				dna : dna,
				symptom : symptom
			}
		}).done(function(response) {
			$("#do-test-modal").modal("hide");
			var $testList = $("#test-list");
			$testList.empty();
			getAllUserTests();
		});
	}

	var getUsername = function() {
		$.ajax({
			method : "GET",
			url : "getCurrentUsername"
		}).done(function(response) {
			$("#username-nav").text("Hello, " + response);

		}).fail(function(response) {
		})
	}

	const slidePage = document.querySelector(".slide-page");
    const nextBtnFirst = document.querySelector(".firstNext");
	const prevBtnSec = document.querySelector(".prev-1");
	const nextBtnSec = document.querySelector(".next-1");
	const prevBtnThird = document.querySelector(".prev-2");
	const nextBtnThird = document.querySelector(".next-2");
	const prevBtnFourth = document.querySelector(".prev-3");
	const submitBtn = document.querySelector(".submit");
	const progressText = document.querySelectorAll(".step p");
	const progressCheck = document.querySelectorAll(".step .check");
	const bullet = document.querySelectorAll(".step .bullet");
	let current = 1;

	nextBtnFirst.addEventListener("click", function(event){
  	event.preventDefault();
  	slidePage.style.marginLeft = "-25%";
  	bullet[current - 1].classList.add("active");
  	progressCheck[current - 1].classList.add("active");
  	progressText[current - 1].classList.add("active");
  	current += 1;
	});

	nextBtnSec.addEventListener("click", function(event){
  	event.preventDefault();
  	slidePage.style.marginLeft = "-50%";
  	bullet[current - 1].classList.add("active");
  	progressCheck[current - 1].classList.add("active");
  	progressText[current - 1].classList.add("active");
  	current += 1;
	});

	nextBtnThird.addEventListener("click", function(event){
  	event.preventDefault();
  	slidePage.style.marginLeft = "-75%";
  	bullet[current - 1].classList.add("active");
  	progressCheck[current - 1].classList.add("active");
  	progressText[current - 1].classList.add("active");
  	current += 1;
	});

	submitBtn.addEventListener("click", function(){
  	bullet[current - 1].classList.add("active");
  	progressCheck[current - 1].classList.add("active");
 	progressText[current - 1].classList.add("active");
  	current += 1;
	});

	prevBtnSec.addEventListener("click", function(event){
  	event.preventDefault();
  	slidePage.style.marginLeft = "0%";
  	bullet[current - 2].classList.remove("active");
  	progressCheck[current - 2].classList.remove("active");
  	progressText[current - 2].classList.remove("active");
  	current -= 1;
	});

	prevBtnThird.addEventListener("click", function(event){
  	event.preventDefault();
  	slidePage.style.marginLeft = "-25%";
  	bullet[current - 2].classList.remove("active");
  	progressCheck[current - 2].classList.remove("active");
  	progressText[current - 2].classList.remove("active");
  	current -= 1;
	});
	
	prevBtnFourth.addEventListener("click", function(event){
  	event.preventDefault();
  	slidePage.style.marginLeft = "-50%";
  	bullet[current - 2].classList.remove("active");
  	progressCheck[current - 2].classList.remove("active");
  	progressText[current - 2].classList.remove("active");
  	current -= 1;
	});

	$('.modal.draggable>.modal-dialog').draggable({
		cursor : 'move',
		handle : '.modal-header'
	});

	$('.modal.draggable>.modal-dialog>.modal-content>.modal-header').css(
			'cursor', 'move');
	
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
		if (firstName.length > 2 && firstName != 'name') {
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
		if (lastName.length > 2 && lastName != 'name') {
			$lastName[0].classList.remove('is-invalid');
			$lastName[0].classList.add('is-valid');
		} else {
			$lastName[0].classList.remove('is-valid');
			$lastName[0].classList.add('is-invalid');
		}
	})
	
	$("#phone-number").on('change', function () {
		var $countryCode = $("#country-code");
		var $phoneNumber = $("#phone-number");
		var fullNumber = $countryCode.val() + $phoneNumber.val();
		var validation = /^\d{12}$/;
		if (validation.test(fullNumber)) {
			$phoneNumber[0].classList.remove('is-invalid');
			$phoneNumber[0].classList.add('is-valid');
		} else {
			$phoneNumber[0].classList.remove('is-valid');
			$phoneNumber[0].classList.add('is-invalid');
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
	
	$("#dna").on('change', function () {
		var $dna = $("#dna");
		var dna = $dna.val();
		var validation = /^[ATGC]+$/i
		if (validation.test(dna)) {
			$dna[0].classList.remove('is-invalid');
			$dna[0].classList.add('is-valid');
		} else {
			$dna[0].classList.remove('is-valid');
			$dna[0].classList.add('is-invalid');	
		}
	})
	
	$("#symptom").on('change', function () {
		var $symptom = $("#symptom");
		var symptom = $symptom.val();
		if (symptom.length > 3 && symptom != 'symptom') {
			$symptom[0].classList.remove('is-invalid');
			$symptom[0].classList.add('is-valid');
		} else {
			$symptom[0].classList.remove('is-valid');
			$symptom[0].classList.add('is-invalid');	
		}
	})

	getUsername();
	getAllUserTests();

})