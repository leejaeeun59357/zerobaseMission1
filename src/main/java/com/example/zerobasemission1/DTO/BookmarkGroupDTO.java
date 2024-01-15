package com.example.zerobasemission1.DTO;

public class BookmarkGroupDTO {
    private int bookmarkNumber;
    private String bookmarkName;
    private String bookmarkRegistDate;
    private String bookmarkEditDate;
    private String bookmarkSeq;

    public String getBookmarkSeq() {
        return bookmarkSeq;
    }

    public void setBookmarkSeq(String bookmarkSeq) {
        this.bookmarkSeq = bookmarkSeq;
    }

    public int getBookmarkNumber() {
        return bookmarkNumber;
    }

    public void setBookmarkNumber(int bookmarkNumber) {
        this.bookmarkNumber = bookmarkNumber;
    }

    public String getBookmarkName() {
        return bookmarkName;
    }

    public void setBookmarkName(String bookmarkName) {
        this.bookmarkName = bookmarkName;
    }

    public String getBookmarkRegistDate() {
        return bookmarkRegistDate;
    }

    public void setBookmarkRegistDate(String bookmarkRegistDate) {
        this.bookmarkRegistDate = bookmarkRegistDate;
    }

    public String getBookmarkEditDate() {
        return bookmarkEditDate;
    }

    public void setBookmarkEditDate(String bookmarkEditDate) {
        this.bookmarkEditDate = bookmarkEditDate;
    }
}
