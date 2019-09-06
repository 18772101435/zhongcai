package com.silv.api.util;

import com.silv.api.model.Result;

public class ResultUtil {

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success("");
    }

    public static Result error(Object object) {
        Result result = new Result();
        result.setCode(500);
        result.setMsg("失败");
        result.setData("object");
        return result;
    }
}
