
var parameters = new URLSearchParams(window.location.search);
var requestedHeatId = parameters.get('heatId');

// Update Race title
//$("#raceTitle").html('Race: ' + requestedHeatId);

var columnsBasic = [
    {name: "#", width: 40, formatter: function(row){return row+1} },                     
    {id: "lane1", name: "Lane 1", field: "lane1", width: 140, formatter: RacerFormatter},
    {id: "lane2", name: "Lane 2", field: "lane2", width: 140, formatter: RacerFormatter},
    {id: "lane3", name: "Lane 3", field: "lane3", width: 140, formatter: RacerFormatter}
  ];

var options = {
    enableCellNavigation: true,
    enableColumnReorder: false,
    rowHeight: 35
  };	

function RacerFormatter(row, cell, value, columnDef, dataContext) {
	var racerInfo = '<div> ' +
		               '<div>' + value.racerName + '</div>' +
//		               '<div>' + value.groupName + '</div>' +
//		               '<div>' + value.bestTime + '</div>' +
		            '</div>';
	
	return racerInfo;
}

var heatSheet;
var grid;

// load Heatsheet events
function loadHeatSheet(heatId) {
	console.log('Retrieving HeatSheet');
	$.ajax({
		headers: { 
	        'Content-Type': 'application/json' 
	    },
		url: '/api/heat/management/sheet/' + heatId ,
		method: 'GET'
	}).done(function(data){
		heatSheet = data;
		grid = new Slick.Grid("#eventList", heatSheet.events, columnsBasic, options);
	});
}

loadHeatSheet(requestedHeatId);