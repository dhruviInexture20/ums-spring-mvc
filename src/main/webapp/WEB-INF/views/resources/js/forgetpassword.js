/**
 * 
 */

$(document).ready(function(){
	
	
	$("#forgetpassword_form").validate({
		rules: {
			email: {
				required: true,
			},
			security_question: {
				required: true, 
			},
			security_answer:{
				required: true,
			}
		},
		messages: {
			email: {
				required: "Please provide an email",
				email: "Please enter a valid email address",
			},
			security_question:{
				required: "Please select security question",
			},
			security_answer:{
				required: "Please enter security answer",
			}
		},
		errorPlacement: function (error, element) {
			error.insertAfter(element.parent());
		}
		
	});
	
	$(".error").css("color","red");
	
});