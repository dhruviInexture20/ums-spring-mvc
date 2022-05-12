
 
<!DOCTYPE html>
<html>
<head>
	  <title>OTP Verification</title>
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
                     <#if error_msg?? >${ error_msg }</#if>
                     </div>
                        <h2 class="text-center">OTP Verification</h2>
                        <p>For OTP check your mail</p>
                        <div class="panel-body">
            
                           <form action="VerifyOTPController" id="otp_form" role="form" autocomplete="off" class="form" method="post">
                              <div class="form-group">
                                 <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-hand-right color-blue"></i></span>
                                    <input id="otp" name="otp" placeholder="Enter OTP here" class="form-control"  type="number">
                                    <input type="hidden" value="<#if email??>${email}</#if>"  name="email" >
                                 </div>
                              </div>
                              <div class="form-group">
                                 <input name="submit" class="btn btn-lg btn-primary btn-block" value="Submit OTP" type="submit">
                              </div>
                           </form>
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