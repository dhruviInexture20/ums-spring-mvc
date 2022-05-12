
<!DOCTYPE html>
<html>
   <head>
      <meta charset="ISO-8859-1">
      <title>Forget Password</title>
      <link href="resources/library/bootstrap/css/bootstrap.min.css" rel="stylesheet">
      <link href="resources/css/forgetpassword.css" rel="stylesheet" >
      <script src="resources/library/jquery/jquery-3.6.0.min.js"></script>
      <script src="resources/library/bootstrap/js/bootstrap.min.js"></script>
   </head>
   <body>

      <div class="form-gap"></div>
      <div class="container">
         <div class="row">
            <div class="col-md-4 col-md-offset-4">
               <div class="panel panel-default">
                  <div class="panel-body">
                     <div class="text-center">
                     	<div class="error-msg">
               	 			                     	
                     	</div>
                        <h2 class="text-center">Reset Password</h2>
                        <div class="panel-body">
                           <form action="UpdatePasswordController" id="resetpassword_form" role="form" class="form" method="post">
                              <div class="form-group">
                                 <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock color-blue"></i></span>
                                    <input type="password" id="password1" class="form-control" placeholder="Enter new password" name="password">
                                 </div>
                              </div>
                              <div class="form-group">
                                 <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock color-blue"></i></span>
                                    <input type="password" class="form-control" placeholder="Enter password again" name="password2">
                                    <input type="hidden" value="<#if email??>${email}</#if>" name="email">
                                 </div>
                              </div>
                               
                              <div class="form-group">
                                 <input name="recover-submit" class="btn btn-lg btn-primary btn-block" value="Reset Password" type="submit">
                              </div>
                            </form>
                        </div>
                        <div class="links">
                  			<a href="login">Go To Login page</a>
              			</div>	
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <script src="resources/library/jquery-validation-1.19.3/dist/jquery.validate.js"></script>
      <script src="resources/js/resetpassword.js"></script>
   </body>
</html>