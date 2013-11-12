define([],
/**
 * @lends StatusPage
 */ 
function(){
//	console.log("statusPage.js is called");
	Status = {};
	rowId = "";
	Status.init = function(){
		
		$.get("/api/v1/status", function(data){
			showStatus(data);
		});
		$(document).on("click",".statusRow",function(){
			rowId = this.id;
			$("#popup").modal("show");
		});
		$("#popup").on("show", function(){
			$.get("/api/v1/status/"+rowId,function (data) {
				$(".modal-body").html(showDetailedStatus(data));
				 
			});
			
		});
		
	}
	
	showStatus = function(data){
		//sort the data list
//		data = data.sort(sortStatusLines);
		
//		data.sort(function(a,b){
//			if (a.id < b.id)
//				  return -1;
//				if (a.id > b.id )
//				  return 1;
//				return 0;
////				return a.id.localeCompare(b.id);
//				});
		
		sortStatusLines(data);
		
		for(var k in data){
//			console.log(data[k].id);
			$('#statusTable > tbody').append(buildRow(data[k]));
		}
	}
	
	showDetailedStatus = function(data) {
		//sort the data list
		sortStatusLines(data.components);
		var table =  "<table><caption><h3>"+data.id+"</h3></caption><thead><tr><th>Monitoring Status</th><th>Component Name</th><th>Last checked</th></tr></thead><tbody>";
		var tableBody = "";
		for(var k in data.components){
			tableBody = tableBody + buildDetailRow(data.components[k]);
		}
		 
		 table = table + tableBody + "</tbody></table>";
		 
		 return table;
		 
			
		
	}
	
	sortStatusLines = function(data){
		
		return data.sort(function(a,b){
			
			if(a.status == 'up' && b.status != 'up') return -1;
			if(a.status == 'upAndLastCheckedOld' && b.status != 'up' && b.status != 'upAndLastCheckedOld') return -1;
			if(a.status == 'partially' && b.status != 'up' && b.status != 'upAndLastCheckedOld' && b.status != 'partially') return -1;
			if(a.status == 'down' && b.status != 'up' && b.status != 'upAndLastCheckedOld' && b.status != 'partially' && b.status != 'down') return -1;
			
			if(a.status == b.status){
				if (a.id < b.id)
					return -1;
				if (a.id > b.id )
					return 1;
				return 0;
			}
			
			return 1;
//				return a.id.localeCompare(b.id);
				});
		}
	
	
	
	
	buildRow = function(testbedstatus){
//		var row = '<tr><td>'+getStatusIcon(testbedstatus.status)+'</td><td>'+testbedstatus.id+'</td><td>'+new Date(testbedstatus.lastCheck).toString()+'</td><td class="statusRow" '+'id="'+testbedstatus.id+'">Details</td></tr>';
		var lastCheckStr = 'unknown'; 
		if(testbedstatus.lastCheck!=null)
			lastCheckStr = new Date(testbedstatus.lastCheck).toString();
		var row = '<tr><td>'+getStatusIcon(testbedstatus.status)+'</td><td class="rowAlignLeft">'+testbedstatus.id+'</td><td>'+lastCheckStr+'</td><td class="statusRow" '+'id="'+testbedstatus.id+'">Details</td></tr>';
		return row;
	}	
	
	buildDetailRow = function(testbedstatus) {
//		var row = '<tr><td>'+getStatusIcon(testbedstatus.status)+'</td><td>'+testbedstatus.id+'</td><td>'+new Date(testbedstatus.lastCheck).toString()+'</td></tr>';
		var lastCheckStr = 'unknown'; 
		if(testbedstatus.lastCheck!=null)
			lastCheckStr = new Date(testbedstatus.lastCheck).toString();
		var row = '<tr><td>'+getStatusIcon(testbedstatus.status)+'</td><td class="rowAlignLeft">'+testbedstatus.id+'</td><td>'+lastCheckStr+'</td></tr>';
		return row;
	}
	// TODO don't rely on img names and path
	getStatusIcon = function(status) {
		if(status === 'up'){
			return '<img src="images/green.png" class="status"></img>';
		}else if (status === 'down') {
			return '<img src="images/red.png" class="status"></img>';
		}else if (status === 'partially') {
			return '<img src="images/yellow.png" class="status"></img>';
		}else if (status === 'upAndLastCheckedOld') {
			return '<img src="images/gray.png" class="status"></img>';
		}else if (status === 'undefined') {
			return '<img src="images/unknown.png" class="status"></img>';
		}else if (status === null) {
			return '<img src="images/unknown.png" class="status"></img>';
		}
	}
	return Status;
	
});



