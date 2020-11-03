   
   
   
   function initMap() {
  const map = new google.maps.Map(document.getElementById("map"), {
    zoom: 8,
    center: { lat: 43.275653, lng: -5.134239 },
  });
  const ctaLayer = new google.maps.KmlLayer({
    url: "rutal.kml",
    map: map,
  });
}