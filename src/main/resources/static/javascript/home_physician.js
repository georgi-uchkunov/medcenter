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
	
	$(document).on(
			'click',
			'.list-group-item',
			function() {
				$selectedTest = $(this).closest(
						'.list-group-template-test');
				$trueSelectedTest = $selectedTest.prevObject;
				console.log($trueSelectedTest);
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

	getUsername();
	getAllUserTests();

})