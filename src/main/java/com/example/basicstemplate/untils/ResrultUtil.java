package com.example.basicstemplate.untils;


//  返回的工具类
public class ResrultUtil {
    /**成功且带数据**/
    public static Resrult success(Object object){
        Resrult result = new Resrult();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }
    /**成功但不带数据**/
    public static Resrult success(){

        return success(null);
    }
    /**失败**/
    public static Resrult error(){
        Resrult result = new Resrult();
        result.setCode(ResultEnum.DATA_IS_NULL.getCode());
        result.setMsg(ResultEnum.DATA_IS_NULL.getMsg());
        result.setCode(ResultEnum.INVALID_PASSWORD.getCode());
        result.setMsg(ResultEnum.INVALID_PASSWORD.getMsg());
//        result.setMsg(ResultEnum.NOT_MATCH.getMsg());
        return result;
    }


}
