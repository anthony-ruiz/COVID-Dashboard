 var mymap = L.map('map',{ zoomControl: false }).setView([38.500000, -98.000000], 4);
 L.geoJSON(albers_composite).addTo(mymap);

 mymap.dragging.disable();
 mymap.touchZoom.disable();
 mymap.doubleClickZoom.disable();
 mymap.scrollWheelZoom.disable();

