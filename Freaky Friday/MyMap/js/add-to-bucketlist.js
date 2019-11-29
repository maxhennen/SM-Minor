$(document).ready(function() {
    var bucketlistLS = window.localStorage.getItem("bucketlist");
    var bucketlist = [];

    if (bucketlistLS !== null) {
        const list = document.querySelector(".bucketlist");
        $.each(JSON.parse(bucketlistLS), function(index, item) {
            bucketlist.push(item);
            $(".bucketlist").append("<li id='" + item.locationId + "'>'" + item.label + "'</li>")
        });
    }

    $("#address-input").on("input", function() {
        var input = this.value;
        if (input.length > 3) {
            var uri = "http://autocomplete.geocoder.api.here.com/6.2/suggest.json" +
                "?app_id=lNS733JNJxreqczcmLri" +
                "&app_code=yeiZrbZ75btuCILPoGSq3A" +
                "&query=" + input;
            $(".suggested").remove();
            const suggestionsPanel = document.querySelector(".suggestions");
            $.get(uri, function(data) {
                var suggestions = data.suggestions;
                suggestions.forEach(suggested => {
                    console.log(suggested);
                    const div = $("<div/>", {
                        "class": "suggested",
                        "text": suggested.label,
                        click: function(e) {
                            var uriLocationid = "http://geocoder.api.here.com/6.2/geocode.json" +
                                "?locationid=" + suggested.locationId +
                                "&jsonattributes=1" +
                                "&gen=9" +
                                "&app_id=lNS733JNJxreqczcmLri" +
                                "&app_code=yeiZrbZ75btuCILPoGSq3A";

                            $.get(uriLocationid, function(data) {
                                var location = data.response.view[0].result[0].location.displayPosition;
                                var countryCode = suggested.countryCode.slice(0, -1);
                                var bucketlistitem = {
                                    label: suggested.label,
                                    countrycode: countryCode,
                                    location: location,
                                    checkedOff: false,
                                    locationId: suggested.locationId
                                }

                                var itemAlreadyInList = false;
                                $.each(bucketlist, function(index, item) {
                                    if (item.locationId === bucketlistitem.locationId) {
                                        itemAlreadyInList = true;
                                    }
                                })
                                if (!itemAlreadyInList) {
                                    bucketlist.push(bucketlistitem);
                                    window.localStorage.setItem("bucketlist", JSON.stringify(bucketlist));
                                    $(".suggested").remove();
                                }
                            })
                        }
                    }).appendTo(suggestionsPanel);
                });
                if (input === '') {
                    suggestionsPanel.innerHTML = '';
                }
            });
        }
    });
});