package com.example.zerobasemission1.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class WifiDto {

    @SerializedName("TbPublicWifiInfo")  // 어노테이션으로 json 파일과 변수명 일치시키기
    @Expose
    private TbPublicWifiInfo tbPublicWifiInfo;

    public TbPublicWifiInfo getTbPublicWifiInfo() {
        return tbPublicWifiInfo;
    }

    public void setTbPublicWifiInfo(TbPublicWifiInfo tbPublicWifiInfo) {
        this.tbPublicWifiInfo = tbPublicWifiInfo;
    }
}
