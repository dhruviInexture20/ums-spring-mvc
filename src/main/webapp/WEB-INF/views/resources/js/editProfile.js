/**
 * 
 */



$(document).ready(function() {
	
	// $("input[name=gender]").attr("disabled", true);
	

	// get user address
	$.ajax({
		url: "getUserAddress",
		type: "post",
		data:{
			userid : function(){
				console.log($("#userid").val());
				return $("#userid").val();
			},	
		},
		success : function(response){
			// console.log(response.type);
			var count = 0;
			var addressList = JSON.parse(JSON.stringify(response));
			console.log(addressList);
			console.log("address = "+addressList.length);
			
			$.each(addressList, function(key, address){
				$("#addressID_"+count).val(address.addressid);
				console.log($("#addressID_"+count).val());
				$("#streetAddress_"+count).val(address.street_address);
				
				$("#city_"+count).val(address.city);
				$("#postal_code_"+count).val(address.postal_code);
				$("#country_"+count).val(address.country);
				populateStates( "country_"+count , "state_"+count );
				$("#state_"+count).val(address.state);
				count++;
				if(addressList.length > count){
					$("#add-more").click();
					
				}
			});
		},
		error: function(){
			alert("alert while retriving user data");
		}
	});
});

