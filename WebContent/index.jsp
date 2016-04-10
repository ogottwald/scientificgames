<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>test</title>
<meta name = "viewport" content = "width = device-width, initial-scale = 1.0">
<!-- Bootstrap -->
<link href = "css/bootstrap.min.css" rel = "stylesheet">
</head>

<body>
<!-- Default bootstrap student modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Student</h4>
      </div>
      <div class="modal-body">
        <div id="studentName"></div>
        <div id="email"></div>
        <div id="avgGrade"></div>
        <table class="table table-striped table-bordered table-hover table-condensed" style="width: 500px">
   	  	<caption style="font-size: 11pt"><b>Classes</b></caption>
          <thead>
            <tr>
              <th>Class</th>
              <th>Grade</th>
            </tr>
          </thead>
          <tbody id="studentClassesList">
          </tbody>
        </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

  	<form  class="form-inline" id="searchForm">
  	  <h1 style="font-size: 15pt">Search Form</h1>
	  <div class="form-group">
        <label for="firstName">First Name</label>
        <input type="text" class="form-control" id="firstName" style="width: 100px">
      </div>
	  <div class="form-group">
        <label for="lastName">Last Name</label>
        <input type="text" class="form-control" id="lastName"  style="width: 200px">
      </div>
      <div class="form-group">
        <label for="lastName">Average Grade</label>
        <select class="form-control" id="avgGradeCon" style="width: 80px">
          <option value="==">=</option>
  		  <option value="<="><=</option>
  		  <option value="<"><</option>
  		  <option value=">=">>=</option>
  	      <option value=">">></option>
 		</select>
 		<input type="text" class="form-control" name="avgGrade" id="avgGrade" style="width: 100px"/>&nbsp;<span id="errmsg"></span>
      </div>
      <button type="submit" class="btn btn-primary" id="btnSearch">Search</button>
	</form>
	<br><br>
	<table class="table table-striped table-bordered table-hover table-condensed" style="width: 500px">
   	  <caption style="font-size: 11pt"><b>Students</b></caption>
        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Avg. Grade</th>
          </tr>
        </thead>
        <tbody id="objectList">
        </tbody>
    </table>
          
<script src = "https://code.jquery.com/jquery.js"></script>
<script src = "js/bootstrap.min.js"></script>
<script src="js/engine.js"></script>
</body>
</html>