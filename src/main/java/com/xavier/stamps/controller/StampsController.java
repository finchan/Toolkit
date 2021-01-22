package com.xavier.stamps.controller;

import com.xavier.stamps.entity.Stamp;
import com.xavier.stamps.service.StampsService;
import com.xavier.stamps.utils.ParseHTML;
import com.xavier.stamps.utils.StringUtil;
import com.xavier.stamps.vo.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class StampsController {
    @Autowired
    private StampsService stampsService;

    @RequestMapping(path="/insert_stamp", method= RequestMethod.PUT)
    public ResultData insertAStamp(@RequestParam("id") String id, @RequestParam("link") String link){
        ResultData resultData = new ResultData();
        if(StringUtil.emptyString(id) || StringUtil.emptyString(link)){
            resultData.setResult(false);
            resultData.setErrorMessage("ID/LINK is null");
            return resultData;
        }

        Stamp stamp = ParseHTML.generateEntireStamp(id, link);
        if(stamp.getImg() == null) {
            resultData.setResult(false);
            resultData.setErrorMessage("Stamp image can not be retrieved");
            return resultData;
        }

        stampsService.insertStampInfo(stamp);

        return resultData;
    }
}
