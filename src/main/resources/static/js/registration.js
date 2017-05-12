console.log('Registration code is here')

var dataView;

Date.prototype.customFormat = function(formatString){
	  var YYYY,YY,MMMM,MMM,MM,M,DDDD,DDD,DD,D,hhhh,hhh,hh,h,mm,m,ss,s,ampm,AMPM,dMod,th;
	  YY = ((YYYY=this.getFullYear())+"").slice(-2);
	  MM = (M=this.getMonth()+1)<10?('0'+M):M;
	  MMM = (MMMM=["January","February","March","April","May","June","July","August","September","October","November","December"][M-1]).substring(0,3);
	  DD = (D=this.getDate())<10?('0'+D):D;
	  DDD = (DDDD=["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"][this.getDay()]).substring(0,3);
	  th=(D>=10&&D<=20)?'th':((dMod=D%10)==1)?'st':(dMod==2)?'nd':(dMod==3)?'rd':'th';
	  formatString = formatString.replace("#YYYY#",YYYY).replace("#YY#",YY).replace("#MMMM#",MMMM).replace("#MMM#",MMM).replace("#MM#",MM).replace("#M#",M).replace("#DDDD#",DDDD).replace("#DDD#",DDD).replace("#DD#",DD).replace("#D#",D).replace("#th#",th);
	  h=(hhh=this.getHours());
	  if (h==0) h=24;
	  if (h>12) h-=12;
	  hh = h<10?('0'+h):h;
	  hhhh = hhh<10?('0'+hhh):hhh;
	  AMPM=(ampm=hhh<12?'am':'pm').toUpperCase();
	  mm=(m=this.getMinutes())<10?('0'+m):m;
	  ss=(s=this.getSeconds())<10?('0'+s):s;
	  return formatString.replace("#hhhh#",hhhh).replace("#hhh#",hhh).replace("#hh#",hh).replace("#h#",h).replace("#mm#",mm).replace("#m#",m).replace("#ss#",ss).replace("#s#",s).replace("#ampm#",ampm).replace("#AMPM#",AMPM);
	};

function ActionsFormatter(row, cell, value, columnDef, dataContext) {
    return '<button onclick="updateRacer(\'' + value + '\')" class="btn btn-xs btn-info"><i class="glyphicon glyphicon-edit"></i> Edit</button> &nbsp;&nbsp;' + 
           '<button onclick="deleteRacer(\'' + value + '\')" class="btn btn-xs btn-danger"><i class="glyphicon glyphicon-remove-circle"></i> Delete</button>';
}

function DateCellFormatter(row, cell, value, columnDef, dataContext) {
	var date = new Date(value);
	return date.customFormat( "#DD#/#MM#/#YYYY# #hh#:#mm#:#ss#" );
	//return date.toString();
}

function updateRacer(racerId) {
	console.log('Updating racer ' + racerId);
	
	$.ajax({
		headers: { 
	        'Content-Type': 'application/json' 
	    },
		url: '/api/racer/' + racerId,
		method: 'GET'
	}).done(function(data){
		$('input[name="id"]').val(data.id);
		$('input[name="racerName"]').val(data.racerName);
		$('input[name="carNumber"]').val(data.carNumber);
		$('select[name="groupName"]').val(data.groupName);
		// No need to update list as this is only going to populate the editor
	});
}

function deleteRacer(racerId) {
	console.log('Deleting racer ' + racerId);
	
	$.ajax({
		headers: { 
	        'Content-Type': 'application/json' 
	    },
		url: '/api/racer/' + racerId,
		method: 'DELETE'
	}).done(function(data){
		updateRacerList()
	});
}

function groupListItemsByGroupName() {
	  dataView.setGrouping({
	    getter: "groupName",
	    formatter: function (g) {
	      return "<span style='font-weight: bold;'>Group:</span>  " + g.value + "  <span style='color:green'>(" + g.count + " racers)</span>";
	    }
	  });
	}

var columnsBasic = [
    {id: "racerName", name: "Racer Name", field: "racerName", width: 80},
    {id: "carNumber", name: "Car #", field: "carNumber", width: 30},
    {id: "groupName", name: "Group", field: "groupName", width: 50},
    {id: "registered", name: "Registered", field: "created", width: 100, formatter: DateCellFormatter},
    {id: "action", name: "Actions", field: "id", formatter: ActionsFormatter}
  ];

var options = {
	    enableCellNavigation: true,
	    enableColumnReorder: false,
	    forceFitColumns: true,
	    rowHeight: 35
	  };


var data = [];

var groupItemMetadataProvider = new Slick.Data.GroupItemMetadataProvider();
dataView = new Slick.Data.DataView({
    groupItemMetadataProvider: groupItemMetadataProvider,
    inlineFilters: true
  });

//var grid = new Slick.Grid("#racerList", data, columnsBasic, options);
var grid = new Slick.Grid("#racerList", dataView, columnsBasic, options);
grid.registerPlugin(groupItemMetadataProvider);

//grid.setSortColumn("registered",true); //columnId, ascending


/**
 * Register the submit handler wen a new racer is registered
 */
var form = $('#racer-registration-form');
form.submit(function(event){
	event.preventDefault();
	var form_status = $('<div class="form-group"></div>');
	var racerData = {};
	racerData.id = $(this).find("input[name='id']").val();
	racerData.racerName = $(this).find("input[name='racerName']").val();
	racerData.carNumber = $(this).find("input[name='carNumber']").val();
	racerData.groupName = $(this).find("select[name='groupName']").val();
	
	$.ajax({
		headers: { 
	        'Content-Type': 'application/json' 
	    },
		url: $(this).attr('action'),
		method: 'POST',
		data: JSON.stringify(racerData),
		beforeSend: function(){
			form.append( form_status.html('<p><i class="fa fa-spinner fa-spin"></i> Saving...</p>').fadeIn() );
		}
	}).done(function(data){
		form_status.html('<p class="text-success">Saved!</p>').delay(1000).fadeOut();
		// reset the form
		form[0].reset();
		// force grid refresh
		updateRacerList()
	});
});

//wire up model events to drive the grid
dataView.onRowCountChanged.subscribe(function (e, args) {
  grid.updateRowCount();
  grid.render();
});
dataView.onRowsChanged.subscribe(function (e, args) {
  grid.invalidateRows(args.rows);
  grid.render();
});

/**
 * Forces the grid refresh (makes REST call with updated info)
 */
function updateRacerList() {
	console.log('Updating racer list!');
	$.ajax({
		headers: { 
	        'Content-Type': 'application/json' 
	    },
		url: '/api/racer',
		method: 'GET'
	}).done(function(data){
		dataView.setItems(data);
		groupListItemsByGroupName();
		grid.render();
	});
}

updateRacerList()
