 $(document).ready(function() {
     var selectedItem = JSON.parse(window.localStorage.getItem("selectedItem"));
     var bucketlist = JSON.parse(window.localStorage.getItem("bucketlist"));

     //map
     if (navigator.onLine) {
         function moveToItem(map) {
             map.setCenter({ lat: selectedItem.location.latitude, lng: selectedItem.location.longitude });
             map.setZoom(14);
             var marker = new H.map.Marker({ lat: selectedItem.location.latitude, lng: selectedItem.location.longitude });
             map.addObject(marker);
         }

         var platform = new H.service.Platform({
             apikey: "QIaw2jXgV1oBr4nIgPTJJsfhq-qCeCGK-CH0Xoqe30k"
         });

         var defaultLayers = platform.createDefaultLayers();
         var map = new H.Map(document.getElementById('map'),
             defaultLayers.vector.normal.map, {
                 center: { lat: 50, lng: 5 },
                 zoom: 4,
                 pixelRatio: window.devicePixelRatio || 1
             });

         window.addEventListener('resize', () => map.getViewPort().resize());
         var behavior = new H.mapevents.Behavior(new H.mapevents.MapEvents(map));
         var ui = H.ui.UI.createDefault(map, defaultLayers);

         moveToItem(map);
     } else {
         $("#map").append($(
             "<img src = '../img/mapNA.png' style:'width:100%;'><img/>"
         ));
     }

     //photo
     $("#filepicker").on("change", function() {
         var file = this.files[0];

         var url = URL.createObjectURL(file);
         console.log(file);
         selectedItem.images.push(url);
         window.localStorage.setItem("selectedItem", JSON.stringify(selectedItem));

         $.each(bucketlist, function(index, item) {
             if (item.locationId == selectedItem.locationId) {
                 bucketlist.slice(index, 1);
             }
             bucketlist.push(selectedItem);
         });
         loadImages();
     });

     loadImages();

     function loadImages() {
         $.each(selectedItem.images, function(index, item) {
             var img = document.createElement("img");
             img.src = item;
             img.width = 50;
             img.height = 50;
             $("#photo").append(img);
         });
     }

 });