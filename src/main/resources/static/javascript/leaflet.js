
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

        var stateName = feature.properties.NAME
        console.log(stateName);

        $.ajax({
            type: "POST",
            url: "/stateRequested/",
            contentType: "application/json",
            data: stateName,
            dataType: 'text',
            cache: false,
            success: function (data) {
                  console.log(data);
            }
        });
        return false;

    });

}
geojson = L.geoJSON(geojsonFeature, {
    onEachFeature: onEachFeature
}).addTo(mymap);



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
    var stateCasesMap = JSON.parse(data);
    createStateCasesHashMap(stateCasesMap);
    L.geoJson(geojsonFeature, {style: styleState}).addTo(mymap);
    }
});


var stateCasesHashMap;
function createStateCasesHashMap(stateCasesMap){
    stateCasesHashMap = new Map(Object.entries(stateCasesMap));
    console.log(stateCasesHashMap);

}
function styleState(feature) {
    let stateName = feature.properties.NAME
    let cases = stateCasesHashMap.get(stateName.toUpperCase());
    return {
        fillColor: getColor(cases),
        weight: 2,
        opacity: 1,
        color: 'white',
        dashArray: '3',
        fillOpacity: 0.5
    };

}


function getColor(d) {
    return d > 3000 ? '#800026' :
           d > 2500  ? '#BD0026' :
           d > 2000  ? '#E31A1C' :
           d > 1500  ? '#FC4E2A' :
           d > 1000   ? '#FD8D3C' :
           d > 500   ? '#FEB24C' :
           d > 100   ? '#FED976' :
                      '#FFEDA0';
}

