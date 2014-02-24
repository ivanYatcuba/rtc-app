<html>

<head>
<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
 <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
    <link href="runnable.css" rel="stylesheet" />
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="/META-INF/resources/images/back.jpeg" rel="stylesheet" type="text/css">

    <!-- Load jQuery and the validate plugin
    <script src="//code.jquery.com/jquery-1.9.1.js"></script>
    <script src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script> -->


</head>
<title> Profile View </title>

<style>
    html,body{
   <!--<img src="${pageContext.request.contextPath}/WEB-INF/resources/images/back.jpeg"/>-->
        background-image: url(back.jpeg);
        background-repeat: no-repeat;
        -o-background-size: 100%;
        background-size: 100%;
    }

</style>

<body>


<form action="" method="post" id="view-form" novalidate=" novalidate">
<div class="Profile view">

    <center><h1> Welcome to user profile view </h1> </center>
    <h2><label> User name: </label> <br></h2>
    <h2><label> Last name:</label> <br></h2>
    <h2><label> E-mail: </label> <br></h2>

</div>
    <!--<button class="btn"> somewhere </button> -->


</form>
<form action="edit" method="post" id="view-form" >

    <button class="btn btn-primary"> Edit Page </button><br>


</form>

</body>
</html>
