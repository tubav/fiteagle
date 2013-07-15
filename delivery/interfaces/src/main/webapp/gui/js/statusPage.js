define([],
/**
 * @lends StatusPage
 */ 
function(){
	
	Status = {};
	
	Status.init = function(){
		
		$.get("https://localhost:8443/api/v1/status", function(data){
			showStatus(data);
		});
	}
	
	showStatus = function(data){
		
		for(var k in data){
			console.log(data[k].id);
			$('#statusTable > tbody').append(buildRow(data[k]));
		}
	}
	
	buildRow = function(testbedstatus){
		var row = '<tr><td>'+testbedstatus.id+'</td><td>'+testbedstatus.status+'</td><td>'+new Date(testbedstatus.lastCheck).toLocaleDateString()+'</td></tr>';
		return row;
	}
	return Status;
	
});



