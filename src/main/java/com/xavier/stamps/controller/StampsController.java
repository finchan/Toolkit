package com.xavier.stamps.controller;

import com.xavier.stamps.entity.Stamp;
import com.xavier.stamps.service.StampsService;
import com.xavier.stamps.utils.ParseHTML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class StampsController {
    @Autowired
    StampsService stampsService;

    @RequestMapping(path="/insert_stamp", method= RequestMethod.GET)
    public String insertAStamp(@RequestParam("id") String id, @RequestParam("link") String link){
//      https://colnect.com/en/stamps/stamp/121108-Edible_Frog_Rana_esculenta_Dwarf_Waterlily_Nymphaea_cand-Protection_of_Nature-Czechoslovakia
        if(id == null || link == null)
            return "failed";

        Stamp stamp = ParseHTML.generateEntireStamp(id, link);
        stampsService.insertStampInfo(stamp);
        return "success";
    }
}
