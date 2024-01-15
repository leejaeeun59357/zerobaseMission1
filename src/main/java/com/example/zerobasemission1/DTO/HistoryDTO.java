package com.example.zerobasemission1.DTO;


public class HistoryDTO {


    private String historyID; 
    private String historyLNT;   // X좌표
    private String historyLAT;   // Y좌표
    private String historyDate;  // 조회일자

    public String getHistoryID() {
        return historyID;
    }

    public void setHistoryID(String historyID) {
        this.historyID = historyID;
    }

    public String getHistoryLNT() {
        return historyLNT;
    }

    public void setHistoryLNT(String historyLNT) {
        this.historyLNT = historyLNT;
    }

    public String getHistoryLAT() {
        return historyLAT;
    }

    public void setHistoryLAT(String historyLAT) {
        this.historyLAT = historyLAT;
    }

    public String getHistoryDate() {
        return historyDate;
    }

    public void setHistoryDate(String historyDate) {
        this.historyDate = historyDate;
    }
}
