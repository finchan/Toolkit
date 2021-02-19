package com.xavier.stamps.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.xavier.stamps.entity.Pager;
import com.xavier.stamps.entity.Stamp;
import com.xavier.stamps.service.StampsService;
import com.xavier.stamps.utils.ParseHTML;
import com.xavier.stamps.utils.StringUtil;
import com.xavier.stamps.vo.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
        //Base64 - "data:image/jpg;base64,"
        return resultData;
    }

    @RequestMapping(path="/delete_stamp", method= RequestMethod.GET)
    public ResultData deteleStamp(@RequestParam("id") String id){
        ResultData resultData = new ResultData();
        if(StringUtil.emptyString(id)){
            resultData.setResult(false);
            resultData.setErrorMessage("Deleted ID is null");
            return resultData;
        }

        stampsService.deteleStamp(id);

        resultData.setResult(true);

        return resultData;
    }

    @RequestMapping(path="/get_max_id_num", method=RequestMethod.GET)
    public ResultData setInsertStampIDNum() {
        ResultData resultData = new ResultData();

        String maxID = stampsService.getMaxIDNum();
        if (maxID != null) {
            Map<String, String> data = new HashMap<String, String>();
            maxID = String.format("%06d", Integer.parseInt(maxID)+1);
            data.put("nextID", maxID);
            resultData.setData(data);
        } else {
            resultData.setResult(false);
            resultData.setErrorMessage("Cannot get Next ID");
        }
        return resultData;
    }

    @RequestMapping(path="/get_stamps", method=RequestMethod.POST)
    public ResultData getStamps (@RequestBody JSONObject data){
        //TODO: Add paging logic if ... return null

        Pager pager = JSON.parseObject(data.toJSONString(), new TypeReference<Pager<List<Stamp>, Stamp>>(){});
        Integer current_page = pager.getPage();

        Pager<List<Stamp>, Stamp> pagerResult = stampsService.getStampsByPager(pager);

        ResultData resultData = new ResultData();
        if(pagerResult.getTotal() != null) {
            Map<String, Object> data1 = new HashMap<>();
            data1.put("total", pagerResult.getTotal());
            data1.put("size", pagerResult.getSize());
            if (pagerResult.getTotal() == 0) {
                data1.put("pages", "1");
            }else {
                data1.put("pages", new Double(Math.ceil(new Double(pagerResult.getTotal())/new Double(pagerResult.getSize()))).intValue());
            }
            data1.put("searchingResult", pagerResult.getEntities());
            resultData.setData(data1);
        } else {
            resultData.setResult(false);
            resultData.setErrorMessage("Cannot get Next ID");
        }
        return resultData;
    }
}
