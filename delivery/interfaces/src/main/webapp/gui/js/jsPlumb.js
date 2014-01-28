define(['require','jquery-ui.min','jquery.jsPlumb-min'],

function(require){
	
//	jsPlumb.ready(function() {
//	});
	
	initResourceButtons = function(){
//		console.log("jsplumb init.");
		var instance = jsPlumb.getInstance({
			DragOptions : { cursor: 'pointer', zIndex:2000 },
			PaintStyle : { strokeStyle:'#666' },
			EndpointStyle : {strokeStyle:'#666' },
			Anchors : ["TopCenter", "TopCenter"],
			Container:"resources"
		});		
		
		var dropOptions = {
				tolerance:"touch",
				hoverClass:"dropHover",
				activeClass:"dragActive"
			};
		
		var startIMEI = {
			endpoint:["Dot", { radius:7 }],
			paintStyle:{ fillStyle:"#373737"},
			isSource:true,
			scope:"IMEI",
			connector: ["Bezier", { curviness:10 } ],
			maxConnections:1,
			isTarget:false,
		};
		
		var endIMEI = {
			endpoint:["Dot", { radius:7 }],
			isSource:false,
			scope:"IMEI",
			maxConnections:3,
			isTarget:true,
			dropOptions:dropOptions
		};
		
		var start2G = {
			endpoint:["Dot", { radius:7 }],
			paintStyle:{ fillStyle:"#373737"},
			isSource:true,
			scope:"2G",
			connector: ["Bezier", { curviness:10 } ],
			maxConnections:1,
			isTarget:false,
		};
		
		var end2G = {
			endpoint:["Dot", { radius:7 }],
			isSource:false,
			scope:"2G",
			maxConnections:3,
			isTarget:true,
			dropOptions:dropOptions
		};
	
		var start3G = {
			endpoint:["Dot", { radius:7 }],
			paintStyle:{ fillStyle:"#373737"},
			isSource:true,
			scope:"3G",
			connector: ["Bezier", { curviness:10 } ],
			maxConnections:1,
			isTarget:false,
		};
		
		var end3G = {
			endpoint:["Dot", { radius:7 }],
			isSource:false,
			scope:"3G",
			maxConnections:3,
			isTarget:true,
			dropOptions:dropOptions
		};
		
		var start4G = {
			endpoint:["Dot", { radius:7 }],
			paintStyle:{ fillStyle:"#373737"},
			isSource:true,
			scope:"4G",
			connector: ["Bezier", { curviness:10 } ],
			maxConnections:1,
			isTarget:false,
		};
		
		var end4G = {
			endpoint:["Dot", { radius:7 }],
			isSource:false,
			scope:"4G",
			maxConnections:3,
			isTarget:true,
			dropOptions:dropOptions
		};
		
		var startWifi = {
			endpoint:["Dot", { radius:7 }],
			paintStyle:{ fillStyle:"#373737"},
			isSource:true,
			scope:"Wifi",
			connector: ["Bezier", { curviness:10 } ],
			maxConnections:1,
			isTarget:false,
		};
		
		var endWifi = {
			endpoint:["Dot", { radius:7 }],
			isSource:false,
			scope:"Wifi",
			maxConnections:3,
			isTarget:true,
			dropOptions:dropOptions
		};
		
		
		
		var idCount = 0;
		var lis = $("#fusecoResources li, #userFusecoResources li");
		lis.on('click',function(event){
			event.preventDefault();
			var type = event.target.innerText;
			var newWindow = jQuery('<div/>', {
			    id: type+idCount,
			});
			
			newWindow.addClass("newWindow window _jsPlumb_endpoint_anchor_");
			newWindow.html("<span class=resourceType>"+type+"</div>");
			
			var editLink = jQuery('<a/>', {
			    href: '#',
			    onclick: 'return false;',
			    'data-toggle': 'collapse',
			    'data-target': '#edit'+idCount,
			});
			editLink.html("<i class='fa fa-cogs'></i>");
			
			var edit = jQuery('<div/>', {
			    id: 'edit'+idCount
			});
			edit.html("<label class='nameInputLabel' for='nameInput"+idCount+"'>name: </label> <input id=nameInput"+idCount+" class='nameInput' type='text' name='name'> <br/>"+
					"monitorable: <i class='fa fa-check'></i> <br/>");
			edit.addClass("collapse out");
			
			var removeLink = jQuery('<a/>', {
			    href: '#',
			});
			removeLink.html("<i class='fa fa-trash-o'></i>");
			removeLink.addClass("removeLink");
			
			
			newWindow.append(removeLink);
			newWindow.append(editLink);
			newWindow.append(edit);
			newWindow.appendTo('#resources');
			
			switch(type){
			case "Attenuator":
				var s1 = instance.addEndpoint(type+idCount, { anchor:[1 , 0.5 , 0, 1] }, start4G);
				s1.addOverlay(["Label", {id:"label", label:"4G", location:[2, 0.5] }]);
				
				var e1 = instance.addEndpoint(type+idCount, { anchor:[0 , 0.5, 0, 1] }, end4G);
				e1.addOverlay(["Label", {id:"label", label:"4G", location:[-1, 0.5] }]);
				
				break;
			case "EPC_Virtual":
				var e1 = instance.addEndpoint(type+idCount, { anchor:[0 , 0.1, 0, 1] }, endIMEI);
				e1.addOverlay(["Label", {id:"label", label:"IMEI", location:[-1, 0.5] }]);
				
				var e2 = instance.addEndpoint(type+idCount, { anchor:[0 , 0.3, 0, 1] }, end2G);
				e2.addOverlay(["Label", {id:"label", label:"2G", location:[-1, 0.5] }]);
				
				var e3 = instance.addEndpoint(type+idCount, { anchor:[0 , 0.5, 0, 1] }, end3G);
				e3.addOverlay(["Label", {id:"label", label:"3G", location:[-1, 0.5] }]);
				
				var e4 = instance.addEndpoint(type+idCount, { anchor:[0 , 0.7, 0, 1] }, end4G);
				e4.addOverlay(["Label", {id:"label", label:"4G", location:[-1, 0.5] }]);
				
				var e5 = instance.addEndpoint(type+idCount, { anchor:[0 , 0.9, 0, 1] }, endWifi);
				e5.addOverlay(["Label", {id:"label", label:"Wifi", location:[-1, 0.5] }]);
				
				break;
			case "EPC_Mobile_Client":
				var s1 = instance.addEndpoint(type+idCount, { anchor:[1 , 0.1 , 0, 1] }, startIMEI);
				s1.addOverlay(["Label", {id:"label", label:"IMEI", location:[2, 0.5] }]);
				
				var s2 = instance.addEndpoint(type+idCount, { anchor:[1 , 0.3 , 0, 1] }, start2G);
				s2.addOverlay(["Label", {id:"label", label:"2G", location:[2, 0.5] }]);
				
				var s3 = instance.addEndpoint(type+idCount, { anchor:[1 , 0.5 , 0, 1] }, start3G);
				s3.addOverlay(["Label", {id:"label", label:"3G", location:[2, 0.5] }]);
				
				var s4 = instance.addEndpoint(type+idCount, { anchor:[1 , 0.7 , 0, 1] }, start4G);
				s4.addOverlay(["Label", {id:"label", label:"4G", location:[2, 0.5] }]);
				
				var s5 = instance.addEndpoint(type+idCount, { anchor:[1 , 0.9 , 0, 1] }, startWifi);
				s5.addOverlay(["Label", {id:"label", label:"Wifi", location:[2, 0.5] }]);
				
				break;			
			}
			
			removeLink.on('click',function(event){
				event.preventDefault();
				instance.detachAllConnections(newWindow);
				instance.removeAllEndpoints(newWindow);
				newWindow.remove();
			});
			
			idCount++;
			instance.draggable($(".window"));
		});
	};
	
});

	
	




