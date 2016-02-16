$(document).ready(function() {

	// Setup form validation on the form
	$('#add-computer-form').validate({

		// Specify the validation rules
		rules : {
			name : "required",
			introduced : {
				Date : true
			},
			discontinued : {
				Date : true
			}
		},

		// Specify the validation error messages
		messages : {
			name : "Please enter at least the name of the computer",
			introduced : "Please enter a valide date format",
			discontinued : "Please enter a valide date format"
		},

		submitHandler : function(form) {
			form.submit();
		}
	});
});