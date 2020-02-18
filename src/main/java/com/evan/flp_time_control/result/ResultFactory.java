package com.evan.flp_time_control.result;

public class ResultFactory {

    /**
     * 注释
     */
    public static Result buildFailResult(String message){
        return buildResult(ResultCode.FAIL,message,null);
    }

    /**
     * 注释
     */
    public static Result buildSuccessResult(Object data){
        return buildResult(ResultCode.SUCCESS,"成功",data);
    }

    /**
     * 注释
     */
    private static Result buildResult(ResultCode resultCode, String message, Object data){
        return new Result(resultCode.code,message,data);
    }

    /**
     * 注释
     */
    private static Result buildResult(int resultCode, String message, Object data){
        return new Result(resultCode,message,data);
    }
}
