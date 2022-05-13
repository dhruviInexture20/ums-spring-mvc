/**
 * 
 */
$(document).ready(function () {

	populateCountries("country_0", "state_0");

	var date = new Date().toISOString().slice(0, 10);
	console.log(date);

	$("#datepicker").attr("max", date);

	$(".img2").hide();

	$("#profilepic").change(function () {
		const file = this.files[0];
		if (file) {
			let reader = new FileReader();
			reader.onload = function (event) {
				$(".img2").show();
				$("#imgPreview2").attr("src", event.target.result);
				$(".img1").hide();
			};
			reader.readAsDataURL(file);

		}
	});

	i = 1;
	$('#add-more').cloneData({
		mainContainerId: 'main-container',
		cloneContainer: 'container-item',
		removeButtonClass: 'remove-item',
		maxLimit: 0,
		minLimit: 1,
		regexName: /(^.+?)([\[\d{1,}\]]{1,})(.+)$/i,
		removeConfirm: true,
		removeConfirmMessage: 'Are you sure want to delete?',
		afterRender: function () {
			console.log("country_" + i, "state_" + i);
		
			$("#addressID_"+i).val(0);
			console.log($("#addressID_"+i).val(0));
			populateCountries("country_" + i, "state_" + i);
			i++;
		},
		afterRemove: function () {
			i--;
			var numItems = $(".container-item").length;
			if (numItems <= 1) {
				$(".remove-item").hide();
			}
			
		},
		beforeRender: function () {
			var numItemsAfterAdd = $(".container-item").length;
			if (numItemsAfterAdd >= 1) {
				$(".remove-item").show();
			}
		},
	});

	$.validator.addMethod("passwordFormatCheck", function (value, element) {
		//return this.optional(element) || /^(?=.*\d)(?=.*[A-Z])(?=.*\W).*$/i.test(value);
		return this.optional(element) || /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/i.test(value);
	}, 'Password must contain one capital letter,one numerical and one special character');

	$.validator.addMethod("lettersonly", function (value, element) {
		return this.optional(element) || /^[a-z\s]+$/i.test(value);
	});

	// ^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$


	$.validator.addMethod("validateEmail", function (value, element) {
		//return this.optional(element) || /^(?=.*\d)(?=.*[A-Z])(?=.*\W).*$/i.test(value);
		return this.optional(element) || /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/i.test(value);
	}, 'Enter Valid Email');




	$("#registration_form").validate({
		rules: {
			firstname: {
				required: true,
				lettersonly: true
			},
			lastname: {
				required: true,
				lettersonly: true
			},
			password: {
				required: true,
				passwordFormatCheck: true,
			},
			confirm_password: {
				required: true,
				equalTo: "#password"
			},
			email: {
				required: true,
				validateEmail: true,
				"remote": {
					url: 'checkEmailAvailability',
					type: "post",
					data: {
						email: function () {
							return $("#email").val();
						}
					}
				}
			},
			phone: {
				required: true,
				number: true,
			},
			postal_code: {
				required: true,
				number: true,
			},
			security_question: {
				required: true,
			},
			security_answer: {
				required: true
			},
			gender: "required",

		},
		messages: {
			firstname: {
				required: "Please enter your firstname",
				lettersonly: "Please enter valid firstname",
			},
			lastname: {
				required: "Please enter your lastname",
				lettersonly: "Please enter valid lastname",
			},
			password: {
				required: "Please provide a password",
			},
			confirm_password: {
				required: "Please provide a password",
				equalTo: "Please enter the same password as above"
			},
			email: {
				required: "Please provide an email",
				email: "Please enter a valid email address",
				remote: "The corresponding email already exists"
			},
			security_question: {
				required: "Please select any security question"
			},
			security_answer: {
				required: "Please enter security answer",
			}
		},
		errorPlacement: function (error, element) {
			if (element.attr("name") == "gender") {
				error.appendTo("#gender-error");
			} else {
				error.insertAfter(element);
			}
		},

	});



});

