
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
               	 			<#if error_msg?? >${error_msg}</#if>          	
                     	</div>
                        <h2 class="text-center">Forgot Password?</h2>
                        <p>You can reset your password here.</p>
                        <div class="panel-body">
                           <form action="ForgetPasswordController" id="forgetpassword_form" role="form" class="form" method="post">
                              <div class="form-group">
                                 <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-envelope color-blue"></i></span>
                                    <input id="email" name="email" placeholder="email address" class="form-control"  type="email">
                                 </div>
                              </div>
                              <div class="form-group">
                                 <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock color-blue"></i></span>
                                    <select class="form-control" name="security_question">
                                       <option value="">Select Security Question</option>
                                       <option value="book">What is your favourite book name ?</option>
                                       <option value="nick_name" >What is your nick name ?</option>
                                       <option value="game" >What is the name of your favouroite game ?</option>
                                    </select>
                                 </div>
                              </div>
                              <div class="form-group">
                                 <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-hand-right color-blue"></i></span>
                                    <input id="security_answer" name="security_answer" placeholder="security answer" class="form-control" type="text">
                                 </div>
                              </div>
                              <div class="form-group">
                                 <input name="recover-submit" class="btn btn-lg btn-primary btn-block" value="Send OTP" type="submit">
                              </div>
                              <input type="hidden" class="hide" name="token" id="token" value=""> 
                           </form>
                        </div>
                        <div class="links">
                  <a href="login">Go to Login page</a>
               </div>	
                     </div>
                  </div>
               </div>
               
            </div>
         </div>
      </div>
      
      <script src="resources/library/jquery-validation-1.19.3/dist/jquery.validate.js"></script>
      <script src="resources/js/forgetpassword.js"></script>
   </body>
</html>