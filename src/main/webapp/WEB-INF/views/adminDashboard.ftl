
<#import "/spring.ftl" as spring />

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Dashboard</title>

<link rel="stylesheet" href=<@spring.url '/resources/library/bootstrap/css/bootstrap.min.css' /> >

<link rel="stylesheet" type="text/css" href=<@spring.url '/resources/library/DataTables/datatables.css' /> >
<link rel="stylesheet" href=<@spring.url '/resources/css/adminDashboard.css' /> >

</head>
<body>
	
<#--	<c:set var="role" value="${role }" />
	<c:if test="${role != 'admin'}">
		<c:redirect url="login.jsp"></c:redirect>
	</c:if>
	
	<jsp:include page="header.jsp"></jsp:include> -->

    <div class="body-wrapper">
	<div id="wrapper" class="container">
		<a href="LogOutController" class="btn btn-primary">Log Out</a>
        <h2 class="text-center">User Detail</h2>
        
        <table id="myTable" class="table table-striped table-bordered" style="width:100%">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>FirstName</th>
                    <th>LastName</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Gender</th>
                    <th>Designation</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
        </table>
		
        <form action="EditUserController" method="post" id="redirect_form">
            <input type="hidden" value="" id="hidden_userid" name="userid">
            <input type="submit" value="submit" id="submit">
        </form>
    </div>

    </div>


	<script src= <@spring.url '/resources/library/jquery/jquery-3.6.0.min.js ' /> ></script>
    <script src= <@spring.url '/resources/library/bootstrap/js/bootstrap.min.js' /> ></script>
	<script src= <@spring.url '/resources/library/DataTables/datatables.js' /> ></script>
	<script src= <@spring.url '/resources/library/DataTables/DataTables-1.11.4/js/dataTables.bootstrap.min.js' /> > </script>
	<script src= <@spring.url '/resources/js/adminDashboard.js' /> ></script>
	
</body>
</html>