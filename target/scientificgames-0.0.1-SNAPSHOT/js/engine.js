// The root URL for the RESTful services
String.prototype.replaceAll = function(s,r){return this.split(s).join(r)}

var rootURL = "/scientificgames/webapi/student";
//alert(rootURL);
var currentObject;

$(document).ready(function() {
	 findAll();

	 $('#avgGrade').keypress(function(event) {
		  if ((event.which != 46 || $(this).val().indexOf('.') != -1) &&
		    ((event.which < 48 || event.which > 57) &&
		      (event.which != 0 && event.which != 8))) {
		    event.preventDefault();
		  }

		  var text = $(this).val();

		  if ((text.indexOf('.') != -1) &&
		    (text.substring(text.indexOf('.')).length > 3) &&
		    (event.which != 0 && event.which != 8) &&
		    ($(this)[0].selectionStart >= text.length - 3)) {
		    event.preventDefault();
		  }
	});

});


// Nothing to delete in initial application state
$('#btnDelete').hide();

// Register listeners
$('#btnSearch').click(function() {
	search($('#firstName').val(),$('#lastName').val(),$('#avgGrade').val(),$("#avgGradeCon").val());
	return false;
});

// Trigger search when pressing 'Return' on search key input field
$('#searchKey').keypress(function(e){
	if(e.which == 13) {
		search($('#searchKey').val());
		e.preventDefault();
		return false;
    }
});

$('#btnAdd').click(function() {
	newObject();
	return false;
});

$('#btnSave').click(function() {
    if ($('#id').val() == '')
		addObject();
	else
		updateObject();
	return false;
});

$('#btnDelete').click(function() {
	deleteObject();
	return false;
});

$('#objectList a').live('click', function() {
	findById($(this).data('identity'));
});


function search(firstName,lastName,avgGrade,avgGradeCon) {
	if (firstName == '' && lastName == '' && avgGrade == '' && avgGradeCon== '') 
		findAll();
	else {
		findByName(firstName,lastName,avgGrade,avgGradeCon);
	}
}

function newObject() {
	$('#btnDelete').hide();
	currentObject = {};
	renderDetails(currentObject); // Display empty form
}

function findAll() {
	$.ajax({
		type: 'GET',
		url: rootURL,
		dataType: "json", // data type of response
		success: renderList
	});
}

function findByName(firstName,lastName,avgGrade,avgGradeCon) {
	$.ajax({
		type: 'GET',
		url: rootURL + '/search?firstName=' + firstName + '&lastName=' + lastName  + "&avgGradeCon="+avgGradeCon + '&avgGrade=' + avgGrade,
		dataType: "json",
		success: renderList
	});
}

function findById(id) {
	$('#myModal').on('show.bs.modal', function(e) {

        var $modal = $(this),
            esseyId = e.relatedTarget.id;

        $.ajax({
        	type: 'GET',
    		url: rootURL + '/' + id,
    		dataType: "json",
    		success: function(data){
                $modal.find('#studentName').html(data.firstName+' '+data.lastName);
                $modal.find('#email').html(data.email);
                $modal.find('#avgGrade').html(data.avgGrade.toFixed(3));
                var studentClasses='';
                //alert(data.studentClassesList.length);
        		for(i =0; i<data.studentClassesList.length; i++){
        			studentClasses = studentClasses + '<tr><td>'+data.studentClassesList[i].className+
        			'</td><td>'+data.studentClassesList[i].grade+'</td></tr>';
        		}
        		//alert(studentClasses);
        		$modal.find('#studentClassesList').html(studentClasses);
            }
        });
    })
}

function addObject() {
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('Object created successfully');
			$('#btnDelete').show();
			$('#id').val(data.id);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addObject error: ' + textStatus);
		}
	});
}

function updateObject() {
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL + '/' + $('#id').val(),
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('Object updated successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updateObject error: ' + textStatus);
		}
	});
}

function deleteObject() {
	console.log('deleteObject');
	$.ajax({
		type: 'DELETE',
		url: rootURL + '/' + $('#id').val(),
		success: function(data, textStatus, jqXHR){
			alert('Object deleted successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deleteObject error');
		}
	});
}

function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#objectList tr').remove();
	$.each(list, function(index, object) {
		var test='';
		for(i =0; i<object.studentClassesList.length; i++){
			test = test + ' '+object.studentClassesList[i].grade+' ';
		}
		
		$('#objectList').append('<tr><td><a onclick="populateDialog(' + object.id + ')"  data-remote="false" data-toggle="modal" data-target="#myModal" class="btn btn-default">'+object.firstName+' '+object.lastName+'</a></td><td>'+object.email+'</td><td>'+object.avgGrade.toFixed(3)+'</td></tr>');
	});
}

function populateDialog(id) {
    findById(id)
}

function renderDetails(object) {
   clearAllInputs("#objectForm");
    $.each(object, function (name, val) {
        $("#objectForm").find('input,textarea,checkbox,radio,select').each(function() {
            switch (this.type) {
                case 'checkbox':
                    if(this.name == name && this.value == val){
                        $('#'+this.id).prop("checked", !$('#'+this.id).is(":checked"));
                    }
                    break;
                case 'radio':
                    if(this.name == name && this.value == val){
                        $('#'+this.id).prop("checked", !$('#'+this.id).is(":checked"));
                    }
                    break;
                default:
                    if(this.name == name){
                        this.value=val;
                    }
            }
        });
    });
}

function clearAllInputs(selector) {
  $(selector).find(':input').each(function() {
    if(this.type == 'submit'){
          //do nothing
      }
      else if(this.type == 'checkbox' || this.type == 'radio') {
        this.checked = false;
      }
      else if(this.type == 'file'){
        var control = $(this);
        control.replaceWith( control = control.clone( true ) );
      }else{
        $(this).val('');
      }
   });
}

// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
    //var id = $('#id').val();
    //alert("id: "+id);
        var i = 0;
        var comma="";
        var oo= "";
        $("#objectForm").find('input,textarea,checkbox,radio,select').each(function() {
            switch (this.type) {
                case 'checkbox':
                    if($('#'+this.id).is(":checked")){
                        if(i>0){
                            comma=", ";
                        }
                        oo = oo+comma+'"'+this.name+'" : "'+this.value+'"';
                    }
                    break;
                case 'radio':
                    if($('#'+this.id).is(":checked")){
                        if(i>0){
                            comma=", ";
                        }
                        oo = oo+comma+'"'+this.name+'" : "'+this.value+'"';                        
                    }
                    break;
                default:
                    if(i>0){
                        comma=", ";
                    }
                    oo = oo+comma+'"'+this.name+'" : "'+this.value+'"';
            }
            i++;
        });

    oo = $.parseJSON('{' + oo + '}');
    var formJSON = JSON.stringify(oo);
    return formJSON;
}



