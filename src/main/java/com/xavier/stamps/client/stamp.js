function DBOperation() {
    this.insertSingleStamp = function(link, id) {
        let genUrl = "http://localhost:9090/insert_stamp?" + "link=" + link+"&id="+id;
        $.ajax({
            url: genUrl,
            dataType: "json",
            method: "PUT",
            success: function(resultData) {
                if(resultData.result) {
                    console.info(resultData);
                    alert("Done!")
                } else {
                    alert("Failed!");
                }
            },
            error: function(err) {
                console.info(err);
                alert("Failed!");
            }
        });
    },
        this.queryMaxID = function() {
            let genUrl = "http://localhost:9090/query_max_id";
            $.ajax({
                url: genUrl,
                dataType: "json",
                method: "GET",
                success: function(resultData) {
                    if(resultData.result) {
                        console.info(resultData);
                        $("#latestIDText").val(resultData.data.maxID);
                    } else {
                        alert("Failed!");
                    }
                },
                error: function(err) {
                    console.info(err);
                    alert("Failed!");
                }
            });
        },
        this.clearText = function(ids) {
            if(ids == null) {
                alert("Arguments are empty!");
                return;
            }
            for(var i = 0; i < ids.length; i++) {
                let id = "#"+ ids[i];
                $(id).val("");
            }
        },
        this.queryStampByID = function() {
            if($.trim($("#existedStampIDText").val()) == "") {
                alert("ID is empty!");
                return;
            }
            let genUrl = "http://localhost:9090/get_stamp_by_id/?";
            genUrl += ("id="+$.trim($("#existedStampIDText").val()));
            $.ajax({
                url: genUrl,
                dataType: "json",
                method: "GET",
                success: function(resultData) {
                    if(resultData.result) {
                        imageDataPopulation(resultData);
                        $("#stamp_info").css("display", "block");
                        $("#stamp_info_no_records").css("display", "none");
                    } else {
                        $("#stamp_info").css("display", "none");
                        $("#stamp_info_no_records").css("display", "block");
                        $("#siimg").attr("src","");
                    }
                },
                error: function(err) {
                    console.info(err);
                    alert("Failed!");
                }
            });
        }
}

function imageDataPopulation(rd) {
    let base64IMG = 'data:image/jpeg;base64,' + rd.data.stampInfo.img;
    $("#siimg").attr("src", base64IMG);
    $("#siid").html(rd.data.stampInfo.id);
    $("#sicountry").html(rd.data.stampInfo.country);
    $("#siname").html(rd.data.stampInfo.name);
    $("#siseries").html(rd.data.stampInfo.series);
    $("#sithemes").html(rd.data.stampInfo.themes);
}

let dbOperation = new DBOperation();
window.onload = function() {
    $("#countryCode").on("click", function() {
        console.info("Set Stamp Country Code");
    });
    $("#addStamp").on("click", function() {
        let link = $.trim($("#link").val());
        let id = $.trim($("#id").val());
        dbOperation.insertSingleStamp(link,id);
    });

    $("#latestID").on("click", function() {
        let link = $.trim($("#link").val());
        let id = $.trim($("#id").val());
        dbOperation.queryMaxID();
    });

    $("#clearStampInfo").on("click", function() {
        dbOperation.clearText(["id", "link"]);
    });
    $("#clearLatestID").on("click", function() {
        dbOperation.clearText(["latestIDText"]);
    });
    $("#clearCountryCode").on("click", function() {
       dbOperation.clearText(["countryCodeText"]);
    });
    $("#clearExistedStampID").on("click", function() {
        dbOperation.clearText(["existedStampIDText"]);
        $("#siimg").attr("src","");
        $("#stamp_info_no_records").css("display","none");
        $(".stamp_info table tr td:last-child div").html("");
    });

    $("#existedStampID").on("click", function() {
        let id = $.trim($("#existedStampIDText").val());
        dbOperation.queryStampByID(id);
    });
    $("#siimg").blowup();

    $("#countryCodeText").autocomplete({
        source: countries,
        minLength:2
    });

};