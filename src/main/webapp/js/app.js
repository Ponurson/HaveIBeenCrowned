// document.addEventListener("DOMContentLoaded", function () {
    var INFECTED_URL = "http://localhost:8080/view1";
    var CONTACT_URL = "http://localhost:8080/view2";

    function dropHandler(ev) {
        console.log('File(s) dropped');
        var p = document.getElementById("waitin");
        p.style.visibility = "visible";
        const fr = new FileReader();
        var flag = ev.currentTarget.id;
        fr.addEventListener("load", e => {
            var locationsToServer = [];
            var timeline = JSON.parse(fr.result);
            timeline.timelineObjects.forEach(function (element) {
                if (element.placeVisit != null) {
                    var locationData = {
                        startTimestamp: element.placeVisit.duration.startTimestampMs,
                        stopTimestamp: element.placeVisit.duration.endTimestampMs,
                        location: element.placeVisit.location.latitudeE7 + ',' + element.placeVisit.location.longitudeE7,
                    };
                    locationsToServer.push(locationData);
                }
            });
            var locationsToServerObject = {
                data: locationsToServer
            };

            if (flag === "drop_zone_contact") {
                urlAddr = CONTACT_URL;
            } else {
                urlAddr = INFECTED_URL;
            }

            $.ajax({
                url: urlAddr,
                data: JSON.stringify(locationsToServerObject),
                contentType: 'application/json',
                method: "POST"
            }).done(function (data) {
                document.getElementById("waitin").style.visibility = "hidden";
                alert(data)
            }).fail(function () {
                document.getElementById("waitin").style.visibility = "hidden";
                alert("Sorry. Server unavailable.");

            })
        });
        ev.preventDefault();
        console.log();
        if (ev.dataTransfer.items) {
            for (var i = 0; i < ev.dataTransfer.items.length; i++) {
                if (ev.dataTransfer.items[i].kind === 'file') {
                    var file = ev.dataTransfer.items[i].getAsFile();
                    fr.readAsText(file);
                }
            }
        } else {
            for (var i = 0; i < ev.dataTransfer.files.length; i++) {
                fr.readAsText(ev.dataTransfer.files[i]);
            }
        }
    }

    function dragOverHandler(ev) {
        console.log('File(s) in drop zone');
        ev.preventDefault();
    }


// });