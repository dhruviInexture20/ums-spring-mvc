

<#import "/spring.ftl" as spring />

<html>

<head>
<title>User Registration</title>

<!-- Bootstrap -->
<link rel="stylesheet" href= <@spring.url '/resources/library/bootstrap/css/bootstrap.min.css' /> >


<link rel="stylesheet" href= <@spring.url '/resources/css/registration.css' /> >

</head>

<body>
 	
	<section>
	
		<div class="main container">
			
			<h1 class="text-center">User Registration</h1>
			<div class="col-md-12 msg">
				<div class="col-md-12 error-msg">
				</div>
				<div class="col-md-12 success-msg">
					
				</div>
			</div>
			<form id="registration_form" 
				  enctype = "multipart/form-data" 
				  method="POST"
				  action= "<#if role?? >process_edits<#else>process_form</#if>" 
				  >
				
				<div class="col-md-12">
					
					<!-- userid -->
					<input type="hidden" name="userid" id="userid" value="<#if user?? >${user.userid}<#else>0</#if>">
				
				
					<!-- first Name -->
					<div class="form-group col-md-6">
						<label for="firstname">First Name</label> 
						<input type="text"
							   class="form-control" 
							   id="firstname" 
							   name="firstname"
							   placeholder="First Name" 
							   required 
							   value= "<#if user?? >${user.firstname}</#if>" > 
					</div>

					<!-- Last Name -->
					<div class="form-group col-md-6">
						<label for="lastname">Last Name</label> 
						<input type="text" 
							   class="form-control" 
							   id="lastname" 
							   name="lastname"
							   placeholder="Last Name" 
							   required
							   value ="<#if user?? >${user.lastname}</#if>" >	
					</div>
				</div>

				<div class="col-md-12">
					<!-- Email -->
					<div class="form-group col-md-6">
						<label for="email">Email</label> 
						<input type="text"
							   class="form-control" 
							   id="email" 
							   name="email" 
							   placeholder="Email" 
							   required 
							   value = "<#if user?? >${user.email}</#if>" 
							   <#if role??>disabled</#if> >
					</div>

					<!-- phone -->
					<div class="form-group col-md-6">
						<label for="phone">Phone number</label> 
						<input type="tel" 
							   class="form-control" 
							   name="phone" 
							   id="phone" 
							   placeholder="phone number" 
							   pattern="[0-9]{10}" 
							   required
							   value = "<#if user?? >${user.phone}</#if>" >
					</div>
				</div>


				<div class="col-md-12">
					<!-- password -->
					<div class="form-group col-md-6">
						<label for="password">Password</label> 
						<input type="password" 
							   class="form-control" 
							   id="password" 
							   name="password"
							   placeholder="Password" 
							   required
							   value = "<#if user?? >${user.password}</#if>" >
							
					</div>

					<!-- confirm password -->
					<div class="form-group col-md-6">
						<label for="confirm_password">confirm Password</label> 
						<input type="password" 
							   class="form-control" 
							   id="confirm_password" 
							   name="confirm_password" 
							   placeholder="Confirm Password" 
							   required
							   value = "<#if user?? >${user.password}</#if>" >
							
					</div>
				</div>

				<div class="col-md-12">
					<!-- designation -->
					<div class="form-group col-md-6">
						<label for="role_title"> User Designation </label> 
						<select class="form-control" name="designation" required>
							<option value="">Select designation</option>
							<option value="intern" <#if user?? & user.designation == "intern" > selected </#if> >Intern</option>
							<option value="jrDeveloper" <#if user?? & user.designation == "jrDeveloper" > selected </#if> >Junior Developer</option>
							<option value="srDeveloper" <#if user?? & user.designation == "srDeveloper" > selected </#if> >Senior Developer</option>
							<option value="team-lead" <#if user?? & user.designation == "team-lead" > selected </#if> >Team Lead</option>
							<option value="project-manager" <#if user?? & user.designation == "project-manager" > selected </#if> >Project Manager</option>
						</select>
					</div>
					
					<div class="col-md-6">
						<label for="birthdate">Birthday:</label> 
						<input type="date"
							   id="datepicker" 
							   name="birthdate" 
							   class="form-control" 
							   required 
							   value = "<#if user?? >${user.birthdate}</#if>" >
					</div>
				</div>

				<div class="col-md-12">
					<!-- gender -->
					<div class="gender-input col-md-12">
						<label>Select your gender</label>
						<div id="gender-error"></div>
						<div class="radio">
							<label>
							<input type="radio" 
								   id="male" 
								   value="male"
								   name="gender"
								   <#if user?? & user.gender == "male">checked = "checked"</#if> >
								   Male
							</label>
						</div>
						<div class="radio">
							<label>
							<input type="radio" 
								   id="female" 
								   value="female"
								   name="gender"
								   <#if user?? & user.gender == "female">checked = "checked"</#if> >
								   Female
							</label>
						</div>
					</div>
				</div>

				<div class="col-md-12">
					<div class="form-group col-md-12">
						<label for="profilepic">Choose Your Profile Picture</label> 
						<input type="file" 
							   id="profilepic" 
							   name="profilepicture"
							   accept=".jpg, .jpeg, .png" >
					</div>	
					<div class="holder col-md-4 img1">
						<img id="imgPreview1" src="data:image/jpg;base64,<#if user?? & user.profilepic??>${user.profilepic}</#if>" alt="profile pic">
					</div>

					<div class="holder col-md-4 img2">
						<img id="imgPreview2" src="#" alt="profile pic">
					</div>
						
				</div>

				
				<div class="col-md-12">
					<div class="form-group col-md-6">
					<label for="role_title">Security Question : </label>
						<select class="form-control" name="security_question">
							<option value="">Select Security Question</option>
							<option value="book" <#if user?? & user.security_question == "book" > selected </#if> >What is your favourite book name ?</option>
							<option value="nick_name" <#if user?? & user.security_question == "nick_name" > selected </#if> >What is your nick name ?</option>
							<option value="game" <#if user?? & user.security_question == "game" > selected </#if> >What is the name of your favouroite game ?</option>
						</select>
					</div>
					
					<div class="form-group col-md-6">
					<label for="role_title">Enter Your Answer :</label>
						<input class="form-control" 
							   type="text" 
							   name="security_answer" 
							   value= "<#if user?? >${user.security_answer}</#if>" 
							   placeholder="Enter Answer" 
							   >
					</div>
				
				</div>
				
   				<div id="main-container">
					<div class="container-item col-md-12">
					
					
					<input type="hidden" id="addressID_0" name="addressList[0].addressid" value="0">
					
						<!-- street address -->
						<div class="col-md-12">
							<div class="form-group col-md-12">
								<label class="control-label" for="address_0">Street Address</label> 
								<input class="form-control" 
								       type="text"
								       id="streetAddress_0" 
								       name="addressList[0].street_address" 
								       required >
							</div>
						</div>
						<br>

						<!-- city -->
						<div class="col-md-12">
							<div class="form-group col-md-6">
								<label class="control-label" for="city_0">City</label> 
								<input class="form-control" 
								       type="text" 
								       id="city_0" 
								       name="addressList[0].city"
									   required >
							</div>
							<!-- country -->
							<div class="form-group col-md-6">
								<label class="control-label" for="country_0">Country</label> 
								<select id="country_0" name="addressList[0].country" class="form-control" required ></select>
							</div>
						</div>

						<br>

						<div class="col-md-12">
							<!-- state -->
							<div class="form-group col-md-6">
								<label class="control-label" for="state_0">State</label> 
								<select id="state_0" name="addressList[0].state" class="form-control" required></select>
							</div>

							<div class="form-group col-md-6">
								<label class="control-label" for="postal_code_0">PostalCode</label>
								<input type="text" name="addressList[0].postal_code" id="postal_code_0"
									class="form-control" required >
							</div>
						</div>
						<br>

						<!-- remove item  -->
						<div class="col-md-12">
							<a href="javascript:void(0)" 
							   id="remove_0"
							   class="remove-item btn btn-danger">Remove</a>
						</div>
						<br>
					</div>
					
				</div>
				
				<div class="col-md-3">
					<a id="add-more" class="btn btn-info my-btn" href="javascript:;"> Add Address </a>
				</div>
				<div class="col-md-3">
					<input type="submit" class="btn btn-success my-btn" 
					value= "<#if role??>${"Save Edits"}<#else>${"Register"}</#if>" >
				</div>
			</form>
			
			<#if role?? >
			<#else>
			<div class="col-md-3">
				<a href="login" class="btn btn-primary my-btn">Goto Login Page</a>
			</div>
			</#if>
			<div class="col-md-3">
				<a href="logOutController" class="btn btn-primary my-btn">LogOut</a>
			</div>
			 
			 
			<#if role?? & role == "admin"> 
			<div class="col-md-3">
				<a href="adminDashboard" class="btn btn-info my-btn">Back To Dashboard</a>
			</div>
			</#if>
						
			
			<#if role?? & role == "user">
			<div class="col-md-3">
				<a href="profile" class="btn btn-info my-btn">Back To Profile</a>
			</div> 	
			</#if>
			
		</div>
	</section>

	
	
	
	<script src="<@spring.url '/resources/library/jquery/jquery-3.6.0.min.js' />" ></script>
	<script src="<@spring.url '/resources/library/bootstrap/js/bootstrap.min.js' /> "></script>
	<script src="<@spring.url '/resources/library/clone-field-increment-id/cloneData.js' /> "></script>
	<script src="<@spring.url '/resources/library/jquery-validation-1.19.3/dist/jquery.validate.js' /> "></script>
	<script src="<@spring.url '/resources/library/List_Country_State-master/countries.js' /> "></script>
	<script src="<@spring.url '/resources/js/registration.js' /> "></script>
	
	<#if user?? >
	
	<script src="<@spring.url '/resources/js/editProfile.js' /> "></script>
	
	</#if>
	
	
	
</body>

</html>
