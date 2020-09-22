
 var mymap = L.map('map',{ zoomControl: false }).setView([40, -100], 5);

//background layer
L.tileLayer('https://server.arcgisonline.com/ArcGIS/rest/services/Canvas/World_Light_Gray_Base/MapServer/tile/{z}/{y}/{x}', {
    attribution: 'Tiles &copy; Esri &mdash; Esri, DeLorme, NAVTEQ',
    maxZoom: 7,
    minZoom: 5
}).addTo(mymap);

//color for the states
var myStyle = {
    "color": "#1a94ff",
    "weight": 2,
    "opacity": 0.65
};

var geojsonFeature  = usa_map;
var geoLayer = L.geoJSON(geojsonFeature, {style : myStyle}).addTo(mymap);

function onEachFeature(feature, layer) {
    //bind click
    layer.on('click', function (e) {
      // e = event
      console.log(feature.properties.NAME);
      e.NA
    });

}
geojson = L.geoJSON(geojsonFeature, {
    onEachFeature: onEachFeature
}).addTo(mymap);
