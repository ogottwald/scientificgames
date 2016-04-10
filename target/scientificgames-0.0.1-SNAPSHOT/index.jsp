<%-- 
    Document   : index
    Created on : May 4, 2015, 5:38:06 PM
    Author     : Oliver Gottwald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="js/jquery-1.11.2.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
      <body>
<script type="text/javascript">
 
$(document).ready(function(){
 $("#msgid").html("This is Hello World by JQuery");
 //$("#msgid").html("");

});

$(function() { 
    $('#c_b input[type="checkbox"]:checked').each(function() { 
        $('#t').append(', '+$(this).val());
    });
});

</script>
 
This is Hello World by HTML
 
<div id="msgid">
</div>


    <div id="c_b">
      <input type="checkbox" value="one_name" checked>
      <input type="checkbox" value="one_name1">
      <input type="checkbox" value="one_name2">
    </div>  

    <textarea id="t"></textarea>



  </body>
</html>
