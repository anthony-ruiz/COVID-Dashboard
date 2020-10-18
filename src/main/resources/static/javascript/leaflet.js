

var mymap = L.map('map',{ zoomControl: false }).setView([40, -100], 5);
var geojsonFeature  = usa_map;

//background layer
L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
        '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
        'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    id: 'mapbox.light',
    maxZoom: 7,
    minZoom: 5
}).addTo(mymap);


//gets the number of cases per mil for all states
$.ajax({
    type: "POST",
    url: "/casesPerMil/",
    contentType: "application/json",
    data: "Test",
    dataType: 'text',
    cache: false,
    success: function (data) {
    console.log(data);
    var stateCasesMap = JSON.parse(data);
    createStateCasesHashMap(stateCasesMap);

    geojson = L.geoJSON(geojsonFeature, {
        style: styleState,
        onEachFeature: onEachFeature
    }).addTo(mymap);
    }
});

//create a hashmap of the Key:State | Value: Cases per Mil
var stateCasesHashMap;
function createStateCasesHashMap(stateCasesMap){
    stateCasesHashMap = new Map(Object.entries(stateCasesMap));
}

//Color the state states depending on cases
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

//color : number of cases key
function getColor(cases) {
    return cases > 3000  ? '#800026' :
           cases > 2500  ? '#BD0026' :
           cases > 2000  ? '#E31A1C' :
           cases > 1500  ? '#FC4E2A' :
           cases > 1000  ? '#FD8D3C' :
           cases > 500   ? '#FEB24C' :
           cases > 100   ? '#FED976' :
                           '#FFEDA0';
}

//bind States to create request (get specific  information of state)
function onEachFeature(feature, layer) {

    layer.on({
         mouseover: highlightState,
         mouseout: resetHighlight,
         click: clickState

    });
}

function clickState(e){
    var layer = e.target;
    var stateName = layer.feature.properties.NAME
    console.log(stateName)
    //when the state is clicked it creates a request
    $.ajax({
        type: "POST",
        url: "/stateRequested/",
        contentType: "application/json",
        data: stateName,
        dataType: 'text',
        cache: false,
        success: function (data) {
            var stateInfo = JSON.parse(data);
            console.log(data);
            info.update(layer.feature.properties , stateInfo);
        }

    });

}

function highlightState(e) {
    var layer = e.target;

    layer.setStyle({
        weight: 5,
        color: '#666',
        dashArray: '',
        fillOpacity: 0.7
    });
}
function resetHighlight(e) {
    geojson.resetStyle(e.target);
}


var info = L.control();

info.onAdd = function (map) {
    this._div = L.DomUtil.create('div', 'info'); // create a div with a class "info"
    this.update();
    return this._div;
};

// method that we will use to update the control based on feature properties passed
info.update = function (props , stateInfo) {
    this._div.innerHTML = '<h4>Covid Information</h4>' +  (props ?
        '<b>' + props.NAME + '</b>' +
        ' <div class="table">'+
            '<table>'+
                '<tr>'+
                    '<td>'+stateInfo.positive+'</td><br /><td>positive cases</td><br />'+
                '</tr>'+
                '<tr>'+
                    '<td>'+stateInfo.positiveIncrease+'</td><br /><td>New Cases since yesterday</td><br />'+
                '</tr>'+
                '<tr>'+
                    '<td>'+stateInfo.deathIncrease+'</td><br /><td>Deaths in last Day</td><br />'+
                '</tr>'+
                '<tr>'+
                    '<td>'+stateInfo.hospitalizedCurrently+'</td><br /><td>Currently Hospitalized</td><br />'+
                '</tr>'+
                '<tr>'+
                    '<td>'+stateInfo.inIcuCurrently+'</td><br /><td> In Intensive Care Unit</td><br />'+
                '</tr>'+
                '<tr>'+
                    '<td>'+stateInfo.recovered+'</td><br /><td>Recovered</td><br />'+
                '</tr>'+
                '<tr>'+
                    '<td>'+stateInfo.deathConfirmed+'</td><br /><td>deathConfirmed</td><br />'+
                '</tr>'+
            '</table>'+
        '</div>'
        : 'Click a state for more information');
};

info.addTo(mymap);