
 var mymap = L.map('map',{ zoomControl: false }).setView([40, -100], 5);

//background layer
L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
        '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
        'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    id: 'mapbox.light',
    maxZoom: 7,
    minZoom: 5
}).addTo(mymap);

//color for the states
var myStyle = {
    "color": "#1a94ff",
    "weight": 1,
    "opacity": 0.25
};

var geojsonFeature  = usa_map;
var geoLayer = L.geoJSON(geojsonFeature, {style : myStyle}).addTo(mymap);

function onEachFeature(feature, layer) {
    //bind click
    layer.on('click', function (e) {
        console.log(feature.properties.NAME);
        var stateObj = feature.properties.NAME
        var loginJSON = JSON.stringify(stateObj);

        $.ajax({
            type: "POST",
            url: "/stateRequested/",
            contentType: "application/json",
            data: stateObj,
            dataType: 'text',
            cache: false,
            success: function (data) {
                  console.log(data);
            }
        });
        return false;


    });

}

var stateObjt = "Test"
$.ajax({
    type: "POST",
    url: "/casesPerMil/",
    contentType: "application/json",
    data: stateObjt,
    dataType: 'text',
    cache: false,
    success: function (data) {
    console.log(data);
    }
});



geojson = L.geoJSON(geojsonFeature, {
    onEachFeature: onEachFeature
}).addTo(mymap);
