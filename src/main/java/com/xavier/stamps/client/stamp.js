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
    this.deleteStampByID = function() {
        if($.trim($("#deletedStampID").val()) == "") {
            alert("ID is empty!");
            return;
        }
        let genUrl = "http://localhost:9090/delete_stamp/?id=";
        $.ajax({
            url: genUrl+$.trim($("#deletedStampID").val()),
            dataType: "json",
            method: "GET",
            success: function(resultData) {
                if(resultData.result) {
                    console.info(resultData);
                    alert("Delete successfully!");
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
    },
    this.setInsertStampID = function() {
        let countryCode = $("#countryCodeText").val();
        if(countryCode == undefined || countryCode == null || $.trim(countryCode) == "") {
            alert("Country Code is empty!")
            return;
        }

        let genUrl = "http://localhost:9090/get_max_id_num";
        $.ajax({
            url: genUrl,
            dataType: "json",
            method: "GET",
            success: function(resultData) {
                if(resultData.result) {
                    let generatedID = countryCode + resultData.data.nextID;
                    $("#id").val(generatedID);
                    $("#latestIDText").val(generatedID);
                    $("#existedStampIDText").val(generatedID);
                } else {
                    $("#id").val("Error!");
                    $("#latestIDText").val("");
                    $("#existedStampIDText").val("");
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

function init() {
    //page events register
    pageEventsRegistered();
    //country code auto complete
    countryCodeAutoCompleted();
    //Initialize Pagination
    reconstructPaginationBar();
}

function pageEventsRegistered() {
    $("#countryCode").on("click", function() {
        dbOperation.setInsertStampID();
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
        dbOperation.clearText(["latestIDText","deletedStampID"]);
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

    $('#searchStamps').on('click', function() {
        let series = $('#series').val();
        let country = $('#country').val();
        let theme = $('#theme').val();
        if(!(series || country || theme)) {
            alert("All these searching fields are empty!!!")
            return;
        }
        paginationImageWallLoaded(1, false);
    });

    $("#deleteStampBtn").on('click', function(){
        dbOperation.deleteStampByID();
    });

    $('#clearStampsWallBtn').on('click', function() {
        dbOperation.clearText(["series", "country", "theme"]);
        reconstructPaginationBar();
        $(".square-inner").css("display", "none");
        $('.milky').css("display", "none");
    });
}

function countryCodeAutoCompleted() {
    let options = {
        data: countries,
        dataType: "json",
        getValue: "name",
        template: {
            type: "custom",
            method: function(elementValue, element) {
                return "<img class='eac-icon' style='display:block;float:left;margin-right:7px;' " +
                    "src='flags/16/" + element["name"].split(' ').join('-') + ".png'/>" +
                    "<span style='line-height:16px;'>" +  elementValue +
                    "<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;✿&nbsp;<strong style='color:#D21856;'>" +
                    element["alpha-3"] + "</strong> / " + element["alpha-2"] + " / " + element["country-code"] + " (Alpha-3/2/C-C)" +
                    "<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;❀&nbsp;" +
                    element["sub-region"] + ", " +element["region"]  +
                    "</span>";
            }
        },
        list: {
            match: {
                enabled: true
            },
            onSelectItemEvent: function() {
                let value = $("#countryCodeText").getSelectedItemData()["alpha-3"];
                $("#countryCodeText").val(value).trigger("change");
            },
            onKeyEnterEvent: function() {
                let value = $("#countryCodeText").getSelectedItemData()["alpha-3"];
                $("#countryCodeText").val(value).trigger("change");
            }
        }

    };
    $("#countryCodeText").easyAutocomplete(options);
}

function reconstructPaginationBar() {
    $('.pagination').jqPagination({
        // trigger:false,
        current_page: 1,
        max_page: 1,
        link_string:"",
        paged: function() {

        }
    });
}

function constructPaginationBar(maxPages) {
    $('.pagination').jqPagination({
        // trigger:false,
        max_page: maxPages,
        paged: function () {
            paginationImageWallLoaded(this.current_page, true);
        }
    });
}

function generateImagesWall(resultData) {
    let $ul = $(".square-inner");
    $ul.empty();
    if(resultData.data.total && resultData.data.total > 0) {
        let stamps = resultData.data.searchingResult;
        for(let i=0; i< stamps.length; i++) {
            $ul.append("<li class='grid_item'><div>"+ stamps[i].id +"</div><img src='data:image/jpeg;base64,"+ stamps[i].img +"'/></li>");
        }
        let imges = $(".square-inner li img");
        imges.each(function(){
            $(this).blowup();
        });
        $('.milky').css("display", "none");
        $(".square-inner").css("display", "grid" );
    } else {
        $(".square-inner").css("display", "none");
        $('.milky').css("display", "block");
    }
    //DOESN'T SUPPORT FOR THE IMAGE HEIGHT/WIDTH FOR data:image/jpeg;base64,
    // $(".square-inner li img").each(function(){
    //     let imgHeight = $(this).height();
    //     console.info(imgHeight);
    //    $(this).css("margin-top", ( (imgHeight< 117) ? (117-imgHeight)/2 : 0) );
    // });
}

function paginationImageWallLoaded(currentPage, skippedPagination){
    let tempURL = "http://localhost:9090/get_stamps";
    let data = {
        "page": currentPage,
        "searchingCriteria": {
            "series": $('#series').val(),
            "country": $('#country').val(),
            "themes": $('#theme').val()
        }
    };
    $.ajax({
        url: tempURL,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(data),
        method: "POST",
        success: function(resultData) {
            if(!skippedPagination){
                constructPaginationBar(resultData.data.pages);
            }
            generateImagesWall(resultData);
        },
        error: function(err) {
            console.info(err);
            alert("Failed!");
        }
    });
}

window.onload = function() {
    init();
};