<#import "/spring.ftl" as spring />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>User Login</title>

    <!-- Bootstrap -->
    
    <link rel="stylesheet" href=<@spring.url '/resources/library/bootstrap/css/bootstrap.min.css' /> >
    
    <link rel="stylesheet" href=<@spring.url '/resources/css/login.css' /> >
    
   
</head>

<body>
    
    <div class="wrapper">
        <h1 class="text-center">User-Login</h1>
        <div class="col-md-12">
        	<div class="error-msg">
        	<#if error_msg?? >${error_msg}</#if>
        	</div>
        	<div class="success">
        	<#if success_msg?? >${success_msg}</#if>
        	</div>
        </div>
        <form action="process_login" method="post" id="login_form">
            <div class="form-group">
                <label for="email">Email address</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Email">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Password">
            </div>
            <input type="submit" class="btn btn-primary" value="Login">
        </form>
        <div class="links">
        <a href="registration">Don't have an account?</a>
        <a href="forgetpassword">Forget Password?</a>
        </div>
    </div>
    
     
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src=<@spring.url '/resources/library/jquery/jquery-3.6.0.min.js' />></script>
    <script src=<@spring.url '/resources/library/bootstrap/js/bootstrap.min.js' /> ></script>
	<script src=<@spring.url '/resources/library/jquery-validation-1.19.3/dist/jquery.validate.js' /> ></script>
   	<script src=<@spring.url '/resources/js/login.js' /> ></script>
</body>

</html>