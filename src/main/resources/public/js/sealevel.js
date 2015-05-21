
(function() {
    /* global main */
    initialize = function() {
    	$("#title:first").textContent = "Title goes here";
        var mapOptions = {
          center: new google.maps.LatLng(51.0, -2.0),
          zoom: 8,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        }
        var map = new google.maps.Map($("#map-canvas")[0], mapOptions);
    };
    
    $(document).ready(function() {
        initialize();
    });
})();