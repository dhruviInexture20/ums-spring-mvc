/**
 * 
 */

$(document).ready(function() {
	
	console.log("loaded");
	
	$("#login_form").validate({
		rules: {
			password: {
				required: true,
			},
			email: {
				required: true,
				email: true, 
				
			},
		},
		messages: {
			password: {
				required: "Please provide a password",
			},
			email: {
				required: "Please provide an email",
				email: "Please enter a valid email address",
			},
		}
	});
	
});