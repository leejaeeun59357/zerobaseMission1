package com.example.zerobasemission1.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Result {

    @SerializedName("CODE")  // 어노테이션으로 json 파일과 변수명 일치시키기
    @Expose
    private String code;

    @SerializedName("MESSAGE")  // 어노테이션으로 json 파일과 변수명 일치시키기
    @Expose
    private String message;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}