$(document).ready(function(){
    
    // this forces the click event on the image to trigger the click of the <input type="file" /> element
    $("#racerPictureImage").click(function () {
        $("#racerPictureFile").trigger('click');
    });
    
    // this is triggered when the <input type="file" /> element is changed
    $('#racerPictureFile').on({
        'change': function(e){
            
            if (this.files && this.files[0]) {
                var reader = new FileReader();
                
                reader.onload = function (e) {
                    $('#racerPictureImage').attr('src', e.target.result);
                }
                
                // force file read from client
                reader.readAsDataURL(this.files[0]);
           }
        }
    });
    
    // Updates the modal and show it
    $('#newRacerModal').on('show.bs.modal', function (event) {
        var modal = $(this);
        var element = $(event.relatedTarget); // Element that triggered the modal
        var racerId = element.data('racer-id');
        
        // setup for existing racer
        if (racerId) {
            // 'element' is a 'div' since it has an 'id'
            var currentRacerDiv = element;
            console.log("Displaying Existing Racer " + racerId);

            // Populate racer id
            modal.find('#racerId').val(racerId);
            
            currentRacerDiv.find('div.racer-name').text()
            
            // Populate racer name
            var racerName = currentRacerDiv.find('div.racer-name').text(); 
            modal.find('#racerName').val(racerName);
            
            // Populate racer picture
            var racerPicture = currentRacerDiv.find('img').clone(); 
            modal.find('#racerPictureImage').attr('src', racerPicture.attr('src'));
            
            // Populate racer enable checkbox
            var racerStatus = currentRacerDiv.find('div.racer-status').text();
            if (racerStatus == 'Enabled') {
                modal.find('#racerEnableCheck').attr('checked',true);
            } else {
                modal.find('#racerEnableCheck').removeAttr('checked');
            }
            
        } else {
            // clearing modal for new input
            console.log("Display New Racer...");
            
            // clear modal with reset values
            modal.find('#racerName').val("");
            modal.find('#racerId').val("");
            modal.find('#racerPictureImage').attr('src', 'default-racer.png');
            modal.find('#racerEnableCheck').removeAttr('checked');
        }
    })
    
    var dummyRacerId = 1;
    
    $('#saveButton').click(function(event){
        var element = $(event.relatedTarget); // Element that triggered the save
        var racerId = $('#racerId').val();
        var racerName = $('#racerName').val();
        var pictureBlob = $('#racerPictureImage').clone();
        var racerStatus = 'Disabled';
        var racerStatusBadge = 'danger';
        var racerEnableCheck = $('#racerEnableCheck').prop('checked') == true;
        
        if (racerEnableCheck) {
            console.log('racer status is checked!');
            racerStatus = 'Enabled';
            racerStatusBadge = 'success';
        } 
        
        if (racerId) {
           console.log("Saving Racer " + racerId); 
           
           var currentRacerDiv = $("div").find("[data-racer-id=" + racerId+"]");
           
           currentRacerDiv.find('div.racer-name').text(racerName);
           currentRacerDiv.find('img').attr('src', pictureBlob.attr('src'));
           currentRacerDiv.find('div.racer-status').text(racerStatus);
           
           // Update CSS based on the check box 'checked' attribute
           if (racerEnableCheck) {    
               currentRacerDiv.find('div.badge').removeClass('badge-danger');
               currentRacerDiv.find('div.badge').addClass('badge-success');
           } else {
               currentRacerDiv.find('div.badge').removeClass('badge-success');
               currentRacerDiv.find('div.badge').addClass('badge-danger');
           }
            
        } else {
            console.log("Saving New Racer"); 
            // TODO: Need to get racerId from Remote call
            racerId = dummyRacerId;
            
            var newRacerDiv = '<div class="col-2 text-center racer-info" data-toggle="modal" data-target="#newRacerModal" ' + 
                              '                  data-racer-id="'+racerId+'">' +
                              '   <div class="picture"><img src="'+  pictureBlob.attr('src') +'" class="img-thumbnail"/></div>' +
                              '   <div class="racer-name">' + racerName +'</div>' +
                              '   <div class="badge badge-'+ racerStatusBadge+' racer-status">'+racerStatus+'</div>' +
                              '</div>';
                          
            $('#racer-list').append(newRacerDiv);
            dummyRacerId = dummyRacerId + 1;
        }
        
        
        $("#modalCloseButton").trigger("click");
    });
    
})