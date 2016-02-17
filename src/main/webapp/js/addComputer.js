$(function() {

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

	jQuery.validator.addMethod("needsIntroduced", function() {

		if ($("#discontinued").val().toString() == "") {
			return true;
		} else if ($("#introduced").val().toString() == "") {
			return false;
		} else {
			return true;
		}

	}, "An introduced date in mandatory with a discontinued date.");

	$("#addcomputer-form").validate(
			{

				rules : {
					name : "required",

					introduced : {
						dateCompare : true,
						date : true
					},

					discontinued : {
						dateCompare : true,
						needsIntroduced : true,
						date : true
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
					alert('Computer has been successfully created');
				}
			});
});