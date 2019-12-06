$(document).ready(function() {
    var bucketlist = [];

    var updateUI = function() {
        $(".bucketlistitem").remove();
        var bucketlistLS = window.localStorage.getItem("bucketlist");
        if (bucketlistLS !== null) {
            const list = document.querySelector(".bucketlist");
            $.each(JSON.parse(bucketlistLS), function(index, item) {
                bucketlist.push(item);
                console.log(item);
                if (item.checkedOff) {
                    $(".bucketlist").append("<li class='bucketlistitem checkedOff list-group-item' id='" + item.locationId +
                        "' ><img src='../img/tick.png' style='width:16px;height:16px;'/>" + item.label + "</li>");
                } else {
                    $(".bucketlist").append("<li class='bucketlistitem list-group-item' id='" + item.locationId +
                        "'><span style='width:16px;height:16px;'></span>" + item.label + "</li>");
                }
            });

            $(".bucketlistitem").click(function() {
                var countrycode = $(this).attr("id");
                console.log(countrycode);
            });
        }
    };

    updateUI();

    $("#address-input").on("input", function() {
        var input = this.value;
        if (input.length > 3) {
            $(".suggestions").show();
            var uri = "http://autocomplete.geocoder.api.here.com/6.2/suggest.json" +
                "?app_id=lNS733JNJxreqczcmLri" +
                "&app_code=yeiZrbZ75btuCILPoGSq3A" +
                "&query=" + input;
            $(".suggested").remove();
            const suggestionsPanel = document.querySelector(".suggestions");
            $.get(uri, function(data) {
                var suggestions = data.suggestions;
                suggestions.forEach(suggested => {
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
                                    updateUI();
                                    $(".suggested").remove();
                                    $(".suggestions").hide();
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
        if (input.length === 0) {
            $(".suggestions").hide();
        }
    });
});