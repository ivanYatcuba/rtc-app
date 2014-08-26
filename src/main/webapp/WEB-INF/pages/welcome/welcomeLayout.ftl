<html>
<#import "/spring.ftl" as spring/>
<head>
    <#include "layout/link.ftl">
</head>

<body>
    <#include "layout/header.ftl" />
    <div id="container" class="container" style="margin-bottom: 60px; margin-top: 20px">
        <#include "${content}.ftl" />
    </div>

    <#include "layout/footer.ftl" />
</body>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="<@spring.url'/resources/css/Bootstrap/js/bootstrap.min.js'/>"></script>
<script src="<@spring.url'/resources/css/Bootstrap/js/bootstrap.js'/>"></script>
<script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js'></script>
<script type="text/javascript">
    $(document).ready(function(){
        $("#content").fadeIn();
       if($("#content").width() < 980){

           var widthMargin = 980 - $("#content").width();
           $("#content").width($("#content").width()+widthMargin);
           var margin = $("#content").css("marginLeft").replace('px', '');


           var n = margin - widthMargin;
           $("#content").css("marginLeft", n/4 +'px' );
       }

    });
</script>


</html>




