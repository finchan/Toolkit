package com.xavier.stamps.vo;

import java.util.HashMap;
import java.util.Map;

public class ResultData {
    private Map data;
    private boolean result;
    private String errorMessage;

    public ResultData() {
        this.data = new HashMap();
        this.result = true;
        this.errorMessage = null;
    }

    public ResultData(Map data, boolean result, String errorMessage) {
        this.data = data;
        this.result = result;
        this.errorMessage = errorMessage;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
