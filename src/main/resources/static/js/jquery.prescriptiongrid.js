$(document).ready(function() {
	
		loadUserInfo();
	
		loadPrescriptionGrid($("#patientId").text());
	
		$('#backToSearchPage').click(function(event) {
			window.history.back();
		});


});

function loadPrescriptionGrid(patientId) {
	var grid_id = 'prescriptionGrid', grid_table_id = grid_id + '_t', pager_id = 'p_'
			+ grid_id;

	$('#' + grid_id).empty();
	$('#' + grid_id).html(
			'<table id="' + grid_table_id + '"></table><div id="' + pager_id
					+ '"></div>');

	var $masterUserGrid = $('#' + grid_table_id);
	$masterUserGrid.jqGrid({
		url : "/searchPrescriptionsForPatientId?patientId=" + patientId,
		datatype : 'json',
		mtype : 'POST',
		colNames : ['Id', 'Bill Number', 'Consultation Date', 'Med Type', 'Med Name', 'Med Strength', 'Special Notes' ],
		colModel : [ {
			name:'id',index:'id',
			width : 100,
			editable : false,
			search : true
		}, {
			name:'prescriptionNumber',index:'prescriptionNumber',
			width : 150,
			editable : false,
			search : true
		}, {
			name:'prescriptionDate',index:'prescriptionDate',
			formatter:'date', formatoptions: {srcformat: 'U/1000', newformat:'Y/d/m'},
			width : 150,
			editable : false,
			search : true
		}, {
			name:'medType',index:'medType',
			width : 150,
			editable : false,
			search : true
		}, {
			name:'medName',index:'medName',
			width : 150,
			editable : false,
			search : true
		}, {
			name:'medStrength',index:'medStrength',
			width : 100,
			editable : false,
			search : true
		}, {
			name:'notes',index:'notes',
			width : 100,
			editable : false,
			search : true
		}],
		rowNum : 10,
		rowList : [ 5, 10, 20 ],
		height : 'auto',
		autowidth : true,
		rownumbers : true,
		pager : pager_id,
		viewrecords : true,
		loadonce : false,
		sortorder : 'desc',
		caption : 'Prescription History',
		emptyrecords : 'No Records Found',
		sortable : true,

	});
	$masterUserGrid.jqGrid('navGrid', '#' + pager_id, {
		edit : false,
		add : true,
		del : false,
		search : true
	});
}


function loadUserInfo(){
	
	var modelAttr = $("#pastPrescriptionDetails").text();
	
	modelAttr = $.parseJSON(modelAttr);
	$.each(modelAttr, function(i, item) {
    	$("#patientFirstName").text(item.firstName);
    	$("#patientLastName").text(item.lastName);
    	$("#patientId").text(item.id);
   	
	});
	
	
}
