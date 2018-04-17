var datetime = null,
        date = null;

// updates the 
var update = function () {
    date = moment(new Date())
    datetime.html(date.format('dddd, MMMM Do YYYY, h:mm:ss a'));
};

$(document).ready(function(){
    datetime = $('#datetime');
    
    // update the current date/time using moment.js
    update();
    
    // update current date/time every 1 second
    setInterval(update, 1000);
});