var bucketlist = window.localStorage.getItem("bucketlist");
var areas = [];
$.each(JSON.parse(bucketlist), function(index, item) {
    var area = {
        "id": item.countrycode,
        "showAsSelected": true
    };
    areas.push(area);
});

var map = AmCharts.makeChart("mapdiv", {
    type: "map",
    theme: "dark",
    projection: "mercator",
    panEventsEnabled: true,
    backgroundColor: "#535364",
    backgroundAlpha: 1,
    zoomControl: {
        zoomControlEnabled: true
    },
    dataProvider: {
        map: "worldHigh",
        getAreasFromMap: true,
        areas: areas
    },
    areasSettings: {
        autoZoom: true,
        color: "#B4B4B7",
        colorSolid: "#84ADE9",
        selectedColor: "#84ADE9",
        outlineColor: "#666666",
        rollOverColor: "#9EC2F7",
        rollOverOutlineColor: "#000000"
    }
});