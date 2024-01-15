package com.example.zerobasemission1.NeedFile;

import com.example.zerobasemission1.DTO.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Services {
    static WifiDAO wifiDAO = new WifiDAO();



    // 오픈 API 를 데이터베이스에 저장하는 메서드
    public int insertWifis() throws Exception {
        wifiDAO.CreateWifiTable();
        return wifiDAO.InsertWifiInfo();
    }

    public List<ShowWifiInfo> SelectAllWifiInfo(double lat, double lnt) {
        List<ShowWifiInfo> tmp = wifiDAO.SelectAllWifiInfo();
        for (ShowWifiInfo wifiInfo : tmp) {
            wifiInfo.setDistance(Math.round(CalculateDistance.calDistance(lat, lnt, wifiInfo.getLat(), wifiInfo.getLnt())) / 1000.0);
        }
        Collections.sort(tmp, Comparator.comparingDouble(ShowWifiInfo::getDistance));

        List<ShowWifiInfo> result = new ArrayList<>();  // 리턴할 객체 생성

        for (int i = 0; i < 20; i++) {
            result.add(tmp.get(i));
        }

        return result;
    }

    public Row SelectOneWifiInfo(String xSwifiMgrNo) {
        return wifiDAO.SelectOneWifiInfo(xSwifiMgrNo);
    }


    // history 관련 서비스 제공
    public void CreateHistory() {
        wifiDAO.CreateHistoryTable();
    }
    public void AddHistory(String lat, String lnt) {
        wifiDAO.InsertHistory(lat,lnt);
    }
    public List<HistoryDTO> SelectAllHistory() {
        return wifiDAO.SelectAllHistoryInfo();
    }
    public HistoryDTO SelectOneHistory(String historyID) {
        return wifiDAO.SelectOneHistoryInfo(historyID);
    }
    public void DeleteOneHistory(String historyID) {
        wifiDAO.DeleteOneHistoryInfo(historyID);
    }



    // 북마크 그룹 관련 서비스 제공

    public void AddBookmarkGroup(String bookmarkName, String bookmarkSeq) {
        wifiDAO.CreateBookmarkRelatedTable();
        wifiDAO.AddBookmarkGroup(bookmarkName, bookmarkSeq);
    }

    public List<BookmarkGroupDTO> SelectAllBookmarkGroup() {
        return wifiDAO.SelectAllBookmarkGroup();
    }

    public void EditBookmarkGroup(String bookmarkNumber, String bookmarkName, String bookmarkSeq) {
        wifiDAO.EditBookmarkGroup(bookmarkNumber, bookmarkName , bookmarkSeq);
    }

    public BookmarkGroupDTO SelectOneBookmarkGroup(String bookmarkNumber) {
        return wifiDAO.SelectOneBookmarkGroup(bookmarkNumber);
    }

    public void DeleteOneBookmarkGroup(String bookmarkNumber) {
        wifiDAO.DeleteOneBookmarkGroup(bookmarkNumber);
    }



    // 북마크 관련 서비스 제공

    public void AddBookmark(String WifiName, String bookmarkName) {
        wifiDAO.CreateBookmarkRelatedTable();
        wifiDAO.AddBookmark(WifiName, bookmarkName);
    }

    public List<BookmarkDTO> SelectAllBookmark() {
        return wifiDAO.SelectAllBookmark();
    }

    public BookmarkDTO SelectOneBookmark(String bookmarkNumber) {
        return wifiDAO.SelectOneBookmark(bookmarkNumber);
    }

    public void DeleteOneBookmark(String bookmarkNumber) {
        wifiDAO.DeleteOneBookmark(bookmarkNumber);
    }


}
