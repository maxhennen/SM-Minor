$(document).ready(function() {

    var visitedCountries = $.get("../data/visited-countries.txt");

    $("#address-input").on("input", function() {
        var input = this.value;
        if (input.length > 3) {
            var uri = "http://autocomplete.geocoder.api.here.com/6.2/suggest.json" +
                "?app_id=lNS733JNJxreqczcmLri" +
                "&app_code=yeiZrbZ75btuCILPoGSq3A" +
                "&query=" + input;


            // if ($())

            const suggestionsPanel = document.querySelector('.suggestions');
            $(".suggested").remove();

            $.get(uri, function(data) {
                var suggestions = data.suggestions;
                suggestions.forEach(suggested => {

                    const div = $("<div/>", {
                        "class": "suggested",
                        "text": suggested.label,
                        click: function(e) {
                            $.ajax({
                                type: "POST",
                                url: "../js/test.php",
                                data: { country: suggested.address.country + "\n" }
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