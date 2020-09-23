
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
//                //alert(data);
//                if (data === "login failed") {
//                    document.getElementById('failed').innerHTML = '<h2><font color="red">Invalid login!</font></h2>';
//                } else {
//                    localStorage.setItem("person", data);
//                    window.location = "./signed_in.html";
//                }
                  console.log(data);
            }
        });
        return false;


    });

}



geojson = L.geoJSON(geojsonFeature, {
    onEachFeature: onEachFeature
}).addTo(mymap);
