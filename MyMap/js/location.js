//function that gets the location and returns it
function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.watchPosition(succes, error, options);
    } else {
        console.log("Geo Location not supported by browser");
    }
}
//function that retrieves the position
function succes(position) {
    var location = {
        longitude: position.coords.longitude,
        latitude: position.coords.latitude
    }
    var bucketlist = JSON.parse(window.localStorage.getItem("bucketlist"));

    $.each(bucketlist, function(index, item) {
        if (!item.checkedOff) {
            var R = 6371; //Radius earth in km
            var dLat = deg2rad(item.location.latitude - location.latitude);
            var dLon = deg2rad(item.location.longitude - location.longitude);

            var a =
                Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(deg2rad(location.latitude)) * Math.cos(deg2rad(item.location.latitude)) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2);

            var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            var d = R * c; // distance in km
            console.log(d);

            if (d < 0.5) {
                bucketlist[index].checkedOff = true;
                window.localStorage.setItem("bucketlist", JSON.stringify(bucketlist));
                sendNotification(item);
            }
        }
    });
}

function error(err) {
    console.warn('ERROR(' + err.code + '): ' + err.message);
}

options = {
    enableHighAccuracy: false,
    timeout: 5000,
    maximumAge: 0
};

function deg2rad(deg) {
    return deg * (Math.PI / 180)
}

function sendNotification(item) {
    if (Notification.permission == "granted") {
        navigator.serviceWorker.getRegistration().then(function(reg) {
            reg.showNotification("Congratiolations you now have " + item.label + "check of your bucketlist!");
        });
    }
}
getLocation();