$(function() {

	// check for date coherence : introduction date must be before discontinued
	// date
	jQuery.validator.addMethod("dateCompare",
			function() {

				if ($("#introduced").val().toString() == ""
						|| $("#discontinued").val().toString() == "") {
					return true;
				} else {
					return $("#introduced").val() < $("#discontinued").val()
							.toString();
				}

			}, "The ending date must be a later date than the start date");

	// check for date coherence : introduction date must be given if
	// discontinued date is given
	jQuery.validator.addMethod("needsIntroduced", function() {

		if ($("#discontinued").val().toString() == "") {
			return true;
		} else if ($("#introduced").val().toString() == "") {
			return false;
		} else {
			return true;
		}

	}, "An introduced date in mandatory with a discontinued date.");

	// date syntax validation
	jQuery.validator.addMethod("dateSyntax", function(value, element) {
		
		if(value =="") {
			return true;
		}	
		return value.match(dateRegex);
	}, "date not valid");

	
	// validate form
	$("#editcomputer-form").validate(
			{

				rules : {
					name : "required",

					introduced : {
						dateSyntax : true,				
					},

					discontinued : {
						dateSyntax : true,
						needsIntroduced : true,
						dateCompare : true			
					},

					companyId : {
						number : true
					}
				},

				messages : {
					name : "Please enter a computer name.",
				},

				highlight : function(element) {
					$(element).parent().closest("div").addClass('has-error')
							.removeClass('has-success')
				},

				unhighlight : function(element) {
					$(element).parent().closest("div").removeClass('has-error')
							.addClass('has-success')
				},

				submitHandler : function(form) {
					form.submit();
					alert('Computer has been successfully added !');
				}
			});
});