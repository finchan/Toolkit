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

import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping(path="/query_max_id", method= RequestMethod.GET)
    public ResultData queryMaxID(){
        ResultData resultData = new ResultData();

        String maxID = stampsService.queryMaxID();
        Map<String, String> data = new HashMap<String, String>();
        data.put("maxID", maxID);
        resultData.setData(data);
        return resultData;
    }

    @RequestMapping(path="/get_stamp_by_id", method= RequestMethod.GET)
    public ResultData getStampViaID(@RequestParam("id") String id){
        ResultData resultData = new ResultData();
        if(StringUtil.emptyString(id)){
            resultData.setResult(false);
            resultData.setErrorMessage("ID is null");
            return resultData;
        }

        Stamp stamp = stampsService.getStampViaID(id);

        if(stamp == null) {
            resultData.setResult(false);
            resultData.setErrorMessage("There's no existed stamp!");
            return resultData;
        } else{
            resultData.setResult(true);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("stampInfo", stamp);
            resultData.setData(data);
        }
        //Base64 - "data:image/png;base64," - byte2Base64StringFun
        return resultData;
    }
}
