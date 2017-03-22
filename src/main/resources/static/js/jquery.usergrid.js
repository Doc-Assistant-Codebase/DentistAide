$(document).ready(function() {

	
		loadUserGrid('firstName', 'Vivek');
	


});

function loadUserGrid(searchBy, searchValue) {
	var grid_id = 'usersGrid', grid_table_id = grid_id + '_t', pager_id = 'p_'
			+ grid_id;

	$('#' + grid_id).empty();
	$('#' + grid_id).html(
			'<table id="' + grid_table_id + '"></table><div id="' + pager_id
					+ '"></div>');

	var $masterUserGrid = $('#' + grid_table_id);
	$masterUserGrid.jqGrid({
		url : "/searchPatientJSON?searchBy=" + searchBy+"&searchValue="+searchValue,
		datatype : 'json',
		mtype : 'POST',
		colNames : [ '#', 'First Name', 'Last Name', 'Mobile', 'Email',
				'Operations' ],
		colModel : [ {
			name : 'id',
			index : 'id',
			width : 100,
			editable : false,
			search : true
		}, {
			name : 'firstName',
			index : 'firstName',
			width : 150,
			editable : false,
			search : true
		}, {
			name : 'lastName',
			index : 'lastName',
			width : 150,
			editable : false,
			search : true
		}, {
			name : 'mobile',
			index : 'mobile',
			width : 100,
			editable : false,
			search : true
		}, {
			name : 'email',
			index : 'email',
			width : 100,
			editable : false,
			search : true
		}, {
			name : 'operations',
			index : 'id',
			width : 100,
			editable : false,
			search : false
		} ],
		rowNum : 10,
		rowList : [ 10, 20, 30, 40, 50, 60 ],
		height : 'auto',
		autowidth : true,
		rownumbers : true,
		pager : pager_id,
		viewrecords : true,
		loadonce : false,
		sortorder : 'desc',
		caption : 'Users',
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



