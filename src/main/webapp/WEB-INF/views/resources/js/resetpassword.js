/**
 * 
 */

$(document).ready(function(){
	
	$.validator.addMethod("passwordFormatCheck", function(value, element) {
		return this.optional(element) || /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/i.test(value);
	}, 'Password must contain one capital letter,one numerical and one special character');

	
	$("#resetpassword_form").validate({
		rules: {
			password1: {
				required: true,
				passwordFormatCheck: true
				
			},
			password2: {
				required: true,
				equalTo: "#password1"
			}
			
		},
		messages: {
			password1: {
				required: "Please provide a password",
				
			},
			password2: {
				required: "Please provide a password",
				equalTo: "Please enter the same password as above"
			}
		},
		errorPlacement: function (error, element) {
			error.insertAfter(element.parent());
		}
	});
	
	
	
	$(".error").css("color","red");
	
	
});