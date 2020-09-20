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

//info that will be shown 
function onEachFeature(feature, layer) {
    if (feature.properties && feature.properties.NAME) {
        layer.bindPopup(feature.properties.NAME);
        console.log(feature.properties.NAME);
    }
}

L.geoJSON(geojsonFeature, {
    onEachFeature: onEachFeature
}).addTo(mymap);


// mymap.dragging.disable();
// mymap.touchZoom.disable();
// mymap.doubleClickZoom.disable();
// mymap.scrollWheelZoom.disable();

//var info = L.control();

//info.onAdd = function (mymap) {
//    this._div = L.DomUtil.create('div', 'info');
//    this.update();
//    return this._div;
//};
//
//info.update = function (props) {
//    this._div.innerHTML = '<h4>State Information</h4>' + (props ?
//        '<b> State : ' + props.STATENAME : 'Hover over a state');
//};
//info.addTo(mymap);

