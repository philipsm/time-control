package com.evan.flp_time_control.result;

import com.evan.flp_time_control.pojo.User;

public class Result {
    //响应码
    private int code;
    private String message;
    private Object data;


    public Result(int code) {
        this.code = code;
    }

    public Result(int code,String message,Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
