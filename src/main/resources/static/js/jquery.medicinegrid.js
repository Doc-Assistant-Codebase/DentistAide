var selectTableData = new Array();
var usernameJSON = new Array();

$(document).ready(function() {
	
	loadUserInfo();
	
	$('#PrescriptionHistory').click(function(event) {
		var un=$("#PrescriptionHistory").text();
		
		loadPrescriptionHistory();
	});
	
	$('#Tablet').click(function(event) {
		var un=$("#Tablet").text();
		loadMedicineGrid(un);
	});
	
	$('#Capsule').click(function(event) {
		var un=$("#Capsule").text();
		loadMedicineGrid(un);
	});
	
	$('#Syrup').click(function(event) {
		var un=$("#Syrup").text();
		loadMedicineGrid(un);
	});
	
	$('#Gel').click(function(event) {
		var un=$("#Gel").text();
		loadMedicineGrid(un);
	});
	
	$('#Mouthwash').click(function(event) {
		var un=$("#Mouthwash").text();
		loadMedicineGrid(un);
	});

	$(this).on('click', '.get-user', function() {
		var id = $(this).val();
		if (id && this.checked) {
			var ret = $("#usersGrid_t").jqGrid('getRowData', id);
			if(!ifRecordAlreadyExists(ret.id)){
				var rowData = new Object();
				rowData.id = ret.id;
				rowData.drugForm = ret.drugForm;
				rowData.tradeName = ret.tradeName;
				rowData.strength = ret.strength;
				rowData.frequency = ret.frequency;
				rowData.duration = ret.duration;
				rowData.specialInstructions = ret.specialInstructions;
				
				
				selectTableData.push(rowData);
				populateSelectTable();
			}
		}
	});

	$(this).on('click', '#button_postSelectedData', function() {
		postSelectedDataToServer();
	});

	$('#userData1').click();

	$(this).on('click', '#button_printSelectedData', function() {
		
		 window.print();
		
		//printPDF();
	});

});

function loadMedicineGrid(dataType) {
	var grid_id = 'usersGrid', grid_table_id = grid_id + '_t', pager_id = 'p_'
			+ grid_id;

	$('#' + grid_id).empty();
	$('#' + grid_id).html(
			'<table id="' + grid_table_id + '"></table><div id="' + pager_id
					+ '"></div>');

	var $masterUserGrid = $('#' + grid_table_id);
	$masterUserGrid.jqGrid({
		url: "/searchMedicineByDrugFormJson?drugForm="+dataType,
		datatype : 'json',
		mtype : 'POST',
		colNames : ['Id','Drug Form', 'Trade Name', 'Strength','Frequency','Duration', 'Special Notes', 'operations' ],
		colModel : [ {
			name:'id',index:'id',
			width : 100,
			editable : false,
			search : true
		}, {
			name:'drugForm',index:'drugForm',
			width : 150,
			editable : false,
			search : true
		}, {
			name:'tradeName',index:'tradeName',
			width : 150,
			editable : false,
			search : true
		}, {
			name:'strength',index:'strength',
			width : 100,
			editable : false,
			search : true
		}, {
			name:'frequency',index:'frequency',
			width : 100,
			editable : false,
			search : true
		}, {
			name:'duration',index:'duration',
			width : 100,
			editable : false,
			search : true
		}, {
			name:'specialInstructions',index:'specialInstructions',
			width : 100,
			editable : false,
			search : true
		}, {
			name : 'operations',
			index : 'id',
			formatter : operationsUrlFormatter,
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
		caption : 'Medicines',
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

function operationsUrlFormatter(cellvalue, options, rowObject) {
	var cellPrefix = '<input type="checkbox"  title="Accept" value="'
			+ rowObject.id + '"  class="get-user"/>';
	return cellPrefix;

}

function populateSelectTable() {
	$('#selectDataTable').empty();
	var $selectTable = $('#template_selectDataTable').clone();
	var html = '';
	$.each(selectTableData, function(k, obj) {
		html += '<tr>' + '<td>' + obj.id + '</td>' + '<td>' + obj.drugForm
				+ '</td>' + '<td>' + obj.tradeName + '</td>' + '<td>' + obj.strength
				+ '</td>' + '<td>' + obj.frequency + '</td>' + '<td>' + obj.duration
				+ '</td>' + '<td>' + obj.specialInstructions + '</td>' + '</tr>';
	});
	$('tbody', $selectTable).html(html);
	$('#selectDataTable').html($selectTable.html());
}

function postSelectedDataToServer() {

	//var finalJson = '{"patientId":2, "medDataList":';
	var patientJsonInfo = $("#userDetails").text();
	
	var finalJson = '{"patientInfo":'+patientJsonInfo+',"medDataList":';
	var jsonFormData = JSON.stringify(selectTableData);
	
	$.ajax({
		type : 'POST',
		url : '/postSelectedData',
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		data : finalJson+jsonFormData+'}',
		success : function(data, textStatus, jqXHR) {
			var jsonObj = encodeURIComponent(JSON.stringify(data));
			
			var myObj = decodeURIComponent(jsonObj);
			
			var jsObj = JSON.parse(myObj);

			$("#patientPrescriptionNumber").text(jsObj.prescriptionNumber);
	    	
			
			$('#selectBtnDiv').hide();
			//document.getElementById('selectBtnDiv').style.display = 'none';
			$('#usersGrid').hide();
			$('#button_postSelectedData').hide();
			$('#divPatientPrescription').hide();


			window.print();

			window.location='home';
		},

	});

}

function myFunction() {
	alert('Successfully provided prescription');
	window.location='home';
}

function printPDF() {
	
	window.print();

	alert('my 123');
	window.location='home';
	
	  /*var pdf = new jsPDF('letter');
      var canvas = pdf.canvas;
      canvas.height = 72 * 11;
      canvas.width=72 * 8.5;
      // var width = 400;
      html2pdf($('#table_selectedData').html(), pdf, function(pdf) {
             
          }
      );
	pdf.save('data.pdf');*/
}

function ifRecordAlreadyExists(id){
	var returnVal = false;
	$.each(selectTableData, function(k, obj) {
		if(id == obj.id){
			returnVal = true;
		}
	});
	return returnVal;
}

function loadUserInfo(){
	
	var modelAttr = $("#userDetails").text();
	
	modelAttr = $.parseJSON(modelAttr);
	$.each(modelAttr, function(i, item) {
    	$("#patientFirstName").text(item.firstName);
    	$("#patientLastName").text(item.lastName);
    	$("#patientId").text(item.id);
    	
    	
	});

	var today = new Date();
	var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
	var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
	var dateTime = date+' '+time;

	$("#patientConsultationDate").text(dateTime);

	$("#patientPrescriptionNUmber").text(dateTime);
	
	
}

function loadPrescriptionHistory(){
	  $("#patientHiddenId").val($("#patientId").text());
	  //var action = '/fetchPrescriptions?patientId='+$("#patientId").text()
	  //alert(action);
	 // $("#searchPatientPrescription").attr("action", action);
	 // alert($("#searchPatientPrescription").attr("action"));
	  $("#searchPatientPrescription").submit();
	
	
	
}

