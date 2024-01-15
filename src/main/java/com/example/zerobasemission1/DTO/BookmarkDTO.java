package com.example.zerobasemission1.DTO;

public class BookmarkDTO {
    private String bookmarkNumber;
    private String bookmarkName;
    private String WifiName;
    private String bookmarkRegistDate;

    public String getBookmarkNumber() {
        return bookmarkNumber;
    }

    public void setBookmarkNumber(String bookmarkNumber) {
        this.bookmarkNumber = bookmarkNumber;
    }

    public String getBookmarkName() {
        return bookmarkName;
    }

    public void setBookmarkName(String bookmarkName) {
        this.bookmarkName = bookmarkName;
    }

    public String getWifiName() {
        return WifiName;
    }

    public void setWifiName(String bookmarkWifiName) {
        this.WifiName = bookmarkWifiName;
    }

    public String getBookmarkRegistDate() {
        return bookmarkRegistDate;
    }

    public void setBookmarkRegistDate(String bookmarkRegistDate) {
        this.bookmarkRegistDate = bookmarkRegistDate;
    }
}
