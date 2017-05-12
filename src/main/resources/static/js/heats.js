console.log('Heats code is here...');

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

function handleHeat(heatId, action) {
	if (action == 'goto') {
		console.log('Going to race ' + heatId);
		console.log('URL: ' + document.URL);
		var heatPage = document.URL + '?heatId=' + heatId;
		window.location.replace(heatPage);		
	} else if (action == 'cancel') {
		console.log('Deleting race ' + heatId);
	} else if (action == 'restart') {
		console.log('Restarting race ' + heatId);
	} else if (action == 'stats') {
		console.log('Showing stats of race ' + heatId);
	}

}	

function ActionsFormatter(row, cell, value, columnDef, dataContext) {
    return '<button onclick="handleHeat(\'' + value + '\', \'goto\')" class="btn btn-xs btn-success"><i class="glyphicon glyphicon-play-circle"></i> Run</button> &nbsp;&nbsp;' + 
    '<button onclick="handleHeat(\'' + value + '\', \'restart\')" class="btn btn-xs btn-info"><i class="glyphicon glyphicon-remove-circle"></i> Restart</button> &nbsp;&nbsp;' + 
    '<button onclick="handleHeat(\'' + value + '\', \'cancel\')" class="btn btn-xs btn-danger"><i class="glyphicon glyphicon-remove-circle"></i> Cancel</button> &nbsp;&nbsp;' + 
    '<button onclick="handleHeat(\'' + value + '\', \'stats\')" class="btn btn-xs btn-primary"><i class="glyphicon glyphicon-stats"></i> Results</button> &nbsp;&nbsp;';
}

var columnsBasic = [
        {id: "id", name: "Sheet Id", field: "id", width: 120},
        {id: "numberOfRacers", name: "Racers", field: "numberOfRacers", width: 70},
        {id: "numberOfRunsPerRacer", name: "Runs", field: "numberOfRunsPerRacer", width: 70},
        {id: "totalNumberOfEvents", name: "Heats", field: "totalNumberOfEvents", width: 70},
        {id: "numberOfEventsNotTimed", name: "Pending", field: "numberOfEventsNotTimed", width: 70},
        {id: "action", name: "Actions", field: "id", width: 300, formatter: ActionsFormatter}
      ];

var options = {
	    enableCellNavigation: true,
	    enableColumnReorder: false,
	    forceFitColumns: false,
	    rowHeight: 35
	  };	
	
var data = [];
var grid = new Slick.Grid("#sheetSummaryList", data, columnsBasic, options);
//grid.setSortColumn("registered",true); //columnId, ascending

var form = $('#heat-sheet-form');
form.submit(function(event){
	event.preventDefault();
	var form_status = $('<div class="form-group"></div>');
	
	var runsPerRacer = $(this).find("select[name='runsPerRacer']").val();
	
	$.ajax({
		headers: { 
	        'Content-Type': 'application/json' 
	    },
		url: $(this).attr('action') + '/' + runsPerRacer,
		method: 'POST',
		beforeSend: function(){
			form.append( form_status.html('<p><i class="fa fa-spinner fa-spin"></i> Building Heat Sheet...</p>').fadeIn() );
		}
	}).done(function(data){
		form_status.html('<p class="text-success">Heat Sheet Built!</p>').delay(1000).fadeOut();
		form[0].reset();
		updateHeatSheetSummaryList()
	});
});

function updateHeatSheetSummaryList() {
	console.log('Updating heat sheet list!');
	$.ajax({
		headers: { 
	        'Content-Type': 'application/json' 
	    },
		url: '/api/heat/management/sheet',
		method: 'GET'
	}).done(function(data){
		grid.setData(data);
		grid.render();
	});
}

updateHeatSheetSummaryList();