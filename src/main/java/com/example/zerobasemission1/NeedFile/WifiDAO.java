package com.example.zerobasemission1.NeedFile;

import com.example.zerobasemission1.DTO.*;

import java.sql.*;
import java.util.*;

public class WifiDAO {

    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet rs = null;
    private static Statement statement = null;


    // 오픈 API 에서 가져온 데이터 관련 메서드들
    public static void CreateWifiTable() {
        String url = "jdbc:mariadb://172.30.1.10:3306/mission1";
        String dbUserId = "mission1user";
        String dbPassword = "zero";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
            statement = connection.createStatement();


            String sql = " create table if not exists wifi_info ( " +
                    " X_SWIFI_MGR_NO varchar(100) PRIMARY KEY, " +
                    " X_SWIFI_WRDOFC varchar(100),  " +
                    " X_SWIFI_MAIN_NM varchar(100), " +
                    " X_SWIFI_ADRES1 varchar(100), " +
                    " X_SWIFI_ADRES2 varchar(100), " +
                    " X_SWIFI_INSTL_FLOOR varchar(100), " +
                    " X_SWIFI_INSTL_TY varchar(100), " +
                    " X_SWIFI_INSTL_MBY varchar(100), " +
                    " X_SWIFI_SVC_SE varchar(100), " +
                    " X_SWIFI_CMCWR varchar(100), " +
                    " X_SWIFI_CNSTC_YEAR varchar(100), " +
                    " X_SWIFI_INOUT_DOOR varchar(100), " +
                    " X_SWIFI_REMARS3 varchar(100), " +
                    " LAT varchar(100), " +
                    " LNT varchar(100), " +
                    " WORK_DTTM varchar(100) " +
                    ")";

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static int InsertWifiInfo() throws Exception {
        String url = "jdbc:mariadb://172.30.1.10:3306/mission1";
        String dbUserId = "mission1user";
        String dbPassword = "zero";

        // 데이터의 총 갯수
        ApiConnect apiConnect = new ApiConnect();
        int listTotalCount = apiConnect.takeWifi(0, 1).getTbPublicWifiInfo().getListTotalCount().intValue();

        int row = 0;
        WifiDto wifiDto = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {

            for (int i = 0; i <= listTotalCount / 1000; i++) {

                connection = DriverManager.getConnection(url, dbUserId, dbPassword);

                String sql = " insert into wifi_info " +
                        " values " +
                        " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

                // 데이터 요청은 한번에 최대 1000건을 넘길 수 없으므로
                // 1000건씩 읽어오기 위한 row 변수
                if (i == listTotalCount / 1000) {
                    row = listTotalCount % (i * 1000);
                } else {
                    row = 1000;
                }

                // wifiDto 객체 생성하기
                wifiDto = apiConnect.takeWifi((i * 1000) + 1, (i * 1000) + row);

                for (int j = 0; j < row; j++) {
                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setString(1, wifiDto.getTbPublicWifiInfo().getRow().get(j).getXSwifiMgrNo());
                    preparedStatement.setString(2, wifiDto.getTbPublicWifiInfo().getRow().get(j).getXSwifiWrdofc());
                    preparedStatement.setString(3, wifiDto.getTbPublicWifiInfo().getRow().get(j).getXSwifiMainNm());
                    preparedStatement.setString(4, wifiDto.getTbPublicWifiInfo().getRow().get(j).getXSwifiAdres1());
                    preparedStatement.setString(5, wifiDto.getTbPublicWifiInfo().getRow().get(j).getXSwifiAdres2());
                    preparedStatement.setString(6, wifiDto.getTbPublicWifiInfo().getRow().get(j).getXSwifiInstlFloor());
                    preparedStatement.setString(7, wifiDto.getTbPublicWifiInfo().getRow().get(j).getXSwifiInstlTy());
                    preparedStatement.setString(8, wifiDto.getTbPublicWifiInfo().getRow().get(j).getXSwifiInstlMby());
                    preparedStatement.setString(9, wifiDto.getTbPublicWifiInfo().getRow().get(j).getXSwifiSvcSe());
                    preparedStatement.setString(10, wifiDto.getTbPublicWifiInfo().getRow().get(j).getXSwifiCmcwr());
                    preparedStatement.setString(11, wifiDto.getTbPublicWifiInfo().getRow().get(j).getXSwifiCnstcYear());
                    preparedStatement.setString(12, wifiDto.getTbPublicWifiInfo().getRow().get(j).getXSwifiInoutDoor());
                    preparedStatement.setString(13, wifiDto.getTbPublicWifiInfo().getRow().get(j).getXSwifiRemars3());
                    preparedStatement.setString(14, wifiDto.getTbPublicWifiInfo().getRow().get(j).getLnt());
                    preparedStatement.setString(15, wifiDto.getTbPublicWifiInfo().getRow().get(j).getLat());
                    preparedStatement.setString(16, wifiDto.getTbPublicWifiInfo().getRow().get(j).getWorkDttm());

                    preparedStatement.executeUpdate();

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listTotalCount;
    }

    public static List<ShowWifiInfo> SelectAllWifiInfo() {
        String url = "jdbc:mariadb://172.30.1.10:3306/mission1";
        String dbUserId = "mission1user";
        String dbPassword = "zero";

        List<ShowWifiInfo> result = new ArrayList<>();  // 반환할 List 객체 생성

        // 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            // 데이터베이스 Connection 생성
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = "select * " +
                    " from wifi_info ";

            preparedStatement = connection.prepareStatement(sql);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                ShowWifiInfo showWifiInfo = new ShowWifiInfo();

                showWifiInfo.setxSwifiMgrNo(rs.getString("X_SWIFI_MGR_NO"));
                showWifiInfo.setxSwifiWrdofc(rs.getString("X_SWIFI_WRDOFC"));
                showWifiInfo.setxSwifiMainNm(rs.getString("X_SWIFI_MAIN_NM"));
                showWifiInfo.setxSwifiAdres1(rs.getString("X_SWIFI_ADRES1"));
                showWifiInfo.setxSwifiAdres2(rs.getString("X_SWIFI_ADRES2"));
                showWifiInfo.setxSwifiInstlFloor(rs.getString("X_SWIFI_INSTL_FLOOR"));
                showWifiInfo.setxSwifiInstlTy(rs.getString("X_SWIFI_INSTL_TY"));
                showWifiInfo.setxSwifiInstlMby(rs.getString("X_SWIFI_INSTL_MBY"));
                showWifiInfo.setxSwifiSvcSe(rs.getString("X_SWIFI_SVC_SE"));
                showWifiInfo.setxSwifiCmcwr(rs.getString("X_SWIFI_CMCWR"));
                showWifiInfo.setxSwifiCnstcYear(rs.getString("X_SWIFI_CNSTC_YEAR"));
                showWifiInfo.setxSwifiInoutDoor(rs.getString("X_SWIFI_INOUT_DOOR"));
                showWifiInfo.setxSwifiRemars3(rs.getString("X_SWIFI_REMARS3"));
                showWifiInfo.setLat(Double.parseDouble(rs.getString("LAT")));
                showWifiInfo.setLnt(Double.parseDouble(rs.getString("LNT")));
                showWifiInfo.setWorkDttm(rs.getString("WORK_DTTM"));

                result.add(showWifiInfo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    public static Row SelectOneWifiInfo(String xSwifiMgrNo) {
        String url = "jdbc:mariadb://172.30.1.10:3306/mission1";
        String dbUserId = "mission1user";
        String dbPassword = "zero";

        Row result = new Row();  // 반환할 List 객체 생성

        // 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            // 데이터베이스 Connection 생성
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = " select * " +
                    " from wifi_info " +
                    " where X_SWIFI_MGR_NO = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, xSwifiMgrNo);

            rs = preparedStatement.executeQuery();


            rs.next();  // 없으면 SQLDataException 발생

            result.setXSwifiMgrNo(rs.getString("X_SWIFI_MGR_NO"));
            result.setXSwifiWrdofc(rs.getString("X_SWIFI_WRDOFC"));
            result.setXSwifiMainNm(rs.getString("X_SWIFI_MAIN_NM"));
            result.setXSwifiAdres1(rs.getString("X_SWIFI_ADRES1"));
            result.setXSwifiAdres2(rs.getString("X_SWIFI_ADRES2"));
            result.setXSwifiInstlFloor(rs.getString("X_SWIFI_INSTL_FLOOR"));
            result.setXSwifiInstlTy(rs.getString("X_SWIFI_INSTL_TY"));
            result.setXSwifiInstlMby(rs.getString("X_SWIFI_INSTL_MBY"));
            result.setXSwifiSvcSe(rs.getString("X_SWIFI_SVC_SE"));
            result.setXSwifiCmcwr(rs.getString("X_SWIFI_CMCWR"));
            result.setXSwifiCnstcYear(rs.getString("X_SWIFI_CNSTC_YEAR"));
            result.setXSwifiInoutDoor(rs.getString("X_SWIFI_INOUT_DOOR"));
            result.setXSwifiRemars3(rs.getString("X_SWIFI_REMARS3"));
            result.setLat(rs.getString("LAT"));
            result.setLnt(rs.getString("LNT"));
            result.setWorkDttm(rs.getString("WORK_DTTM"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
    }


    // 히스토리 목록을 다루는 메소드들
    public static void CreateHistoryTable() {
        String url = "jdbc:mariadb://172.30.1.10:3306/mission1";
        String dbUserId = "mission1user";
        String dbPassword = "zero";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
            statement = connection.createStatement();

            String sql = " create table if not exists history " +
                    " ( " +
                    " historyID integer auto_increment primary key, " +
                    " historyLAT varchar(100), " +
                    " historyLNT varchar(100), " +
                    " historyDate DATETIME " +
                    " ) ";

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void InsertHistory(String lat, String lnt) {
        String url = "jdbc:mariadb://172.30.1.10:3306/mission1";
        String dbUserId = "mission1user";
        String dbPassword = "zero";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = " insert into history " +
                    " ( historyLAT, historyLNT, historyDate ) " +
                    " values " +
                    " ( ?, ?, now() ) ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, lat);
            preparedStatement.setString(2, lnt);

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<HistoryDTO> SelectAllHistoryInfo() {
        String url = "jdbc:mariadb://172.30.1.10:3306/mission1";
        String dbUserId = "mission1user";
        String dbPassword = "zero";

        List<HistoryDTO> result = new ArrayList<>();  // 반환할 List 객체 생성

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = "select * " +
                    " from history ";

            preparedStatement = connection.prepareStatement(sql);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                HistoryDTO historyDTO = new HistoryDTO();

                historyDTO.setHistoryID(rs.getString("historyID"));
                historyDTO.setHistoryLAT(rs.getString("historyLAT"));
                historyDTO.setHistoryLNT(rs.getString("historyLNT"));
                historyDTO.setHistoryDate(rs.getString("historyDate"));

                result.add(historyDTO);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static HistoryDTO SelectOneHistoryInfo(String historyID) {
        HistoryDTO result = new HistoryDTO();

        String url = "jdbc:mariadb://172.30.1.10:3306/mission1";
        String dbUserId = "mission1user";
        String dbPassword = "zero";

        // 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            // 데이터베이스 Connection 생성
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = " select * " +
                    " from history " +
                    " where historyId= ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, historyID);

            rs = preparedStatement.executeQuery();

            rs.next();  // 없으면 SQLDataException 발생

            result.setHistoryID(rs.getString("historyID"));
            result.setHistoryLAT(rs.getString("historyLAT"));
            result.setHistoryLNT(rs.getString("historyLNT"));
            result.setHistoryDate(rs.getString("historyDate"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    public static void DeleteOneHistoryInfo(String historyID) {

        String url = "jdbc:mariadb://172.30.1.10:3306/mission1";
        String dbUserId = "mission1user";
        String dbPassword = "zero";

        // 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            // 데이터베이스 Connection 생성
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = " delete from history " +
                    " where historyID = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, historyID);

            preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    // 북마크와 북마크 그룹 테이블을 생성하는 메서드
    public static void CreateBookmarkRelatedTable() {
        String url = "jdbc:mariadb://172.30.1.10:3306/mission1";
        String dbUserId = "mission1user";
        String dbPassword = "zero";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
            statement = connection.createStatement();

            String sql = " create table if not exists bookmarkGroup ( " +
                    " bookmarkNumber integer auto_increment primary key, " +
                    " bookmarkName varchar(100), " +
                    " bookmarkSeq varchar(100), " +
                    " bookmarkRegistDate DATETIME , " +
                    " bookmarkEditDate DATETIME )";

            statement.executeUpdate(sql);


            sql = " create table if not exists bookmark " +
                    " ( " +
                    " bookmarkNumber integer auto_increment primary key, " +
                    " bookmarkName varchar(100), " +
                    " WifiName varchar(100), " +
                    " bookmarkRegistDate DATETIME, " +
                    " constraint fk_bookmarkNumber foreign key(bookmarkNumber) references bookmarkGroup(bookmarkNumber) on delete cascade " +
                    " ) ";

            statement.executeUpdate(sql);


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    // 북마크 그룹을 다루는 메소드들

    public static List<BookmarkGroupDTO> SelectAllBookmarkGroup() {
        String url = "jdbc:mariadb://172.30.1.10:3306/mission1";
        String dbUserId = "mission1user";
        String dbPassword = "zero";

        List<BookmarkGroupDTO> result = new ArrayList<>();  // 반환할 List 객체 생성

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = "select * " +
                    " from bookmarkgroup ";

            preparedStatement = connection.prepareStatement(sql);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                BookmarkGroupDTO bookmarkGroupDTO = new BookmarkGroupDTO();

                bookmarkGroupDTO.setBookmarkNumber(Integer.parseInt(rs.getString("bookmarkNumber")));
                bookmarkGroupDTO.setBookmarkName(rs.getString("bookmarkName"));
                bookmarkGroupDTO.setBookmarkRegistDate(rs.getString("bookmarkRegistDate"));
                bookmarkGroupDTO.setBookmarkEditDate(rs.getString("bookmarkEditDate"));
                bookmarkGroupDTO.setBookmarkSeq(rs.getString("bookmarkSeq"));

                result.add(bookmarkGroupDTO);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void AddBookmarkGroup(String bookmarkName, String bookmarkSeq) {
        String url = "jdbc:mariadb://172.30.1.10:3306/mission1";
        String dbUserId = "mission1user";
        String dbPassword = "zero";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = " insert into bookmarkgroup " +
                    " (bookmarkName, bookmarkSeq, bookmarkRegistDate ) " +
                    " values " +
                    " (?, ?, now() ) ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bookmarkName);
            preparedStatement.setString(2, bookmarkSeq);

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void EditBookmarkGroup(String bookmarkNumber, String bookmarkName, String bookmarkSeq) {

        String url = "jdbc:mariadb://172.30.1.10:3306/mission1";
        String dbUserId = "mission1user";
        String dbPassword = "zero";

        // 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            // 데이터베이스 Connection 생성
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = " update bookmarkgroup " +
                    " set " +
                    " bookmarkName = ?," +
                    " bookmarkSeq = ?, " +
                    " bookmarkEditDate = now() " +
                    " where bookmarkNumber = ? ";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, bookmarkName);
            preparedStatement.setString(2, bookmarkSeq);
            preparedStatement.setString(3, bookmarkNumber);

            rs = preparedStatement.executeQuery();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public static BookmarkGroupDTO SelectOneBookmarkGroup(String bookmarkNumber) {
        BookmarkGroupDTO result = new BookmarkGroupDTO();

        String url = "jdbc:mariadb://172.30.1.10:3306/mission1";
        String dbUserId = "mission1user";
        String dbPassword = "zero";

        // 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            // 데이터베이스 Connection 생성
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = " select * " +
                    " from bookmarkgroup " +
                    " where bookmarkNumber = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bookmarkNumber);

            rs = preparedStatement.executeQuery();

            rs.next();  // 없으면 SQLDataException 발생

            result.setBookmarkNumber(Integer.parseInt(rs.getString("bookmarkNumber")));
            result.setBookmarkName(rs.getString("bookmarkName"));
            result.setBookmarkSeq(rs.getString("bookmarkSeq"));
            result.setBookmarkRegistDate(rs.getString("bookmarkRegistDate"));
            result.setBookmarkEditDate(rs.getString("bookmarkEditDate"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    public static void DeleteOneBookmarkGroup(String bookmarkNumber) {

        String url = "jdbc:mariadb://172.30.1.10:3306/mission1";
        String dbUserId = "mission1user";
        String dbPassword = "zero";

        // 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            // 데이터베이스 Connection 생성
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = " delete from bookmarkgroup " +
                    " where bookmarkNumber = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bookmarkNumber);

            preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    // 북마크를 다루는 메소드

    public static void AddBookmark(String WifiName, String bookmarkName) {
        String url = "jdbc:mariadb://172.30.1.10:3306/mission1";
        String dbUserId = "mission1user";
        String dbPassword = "zero";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = " insert into bookmark " +
                    " ( bookmarkName, WifiName, bookmarkRegistDate ) " +
                    " values " +
                    " ( ? , ? , now()); ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bookmarkName);
            preparedStatement.setString(2, WifiName);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<BookmarkDTO> SelectAllBookmark() {
        String url = "jdbc:mariadb://172.30.1.10:3306/mission1";
        String dbUserId = "mission1user";
        String dbPassword = "zero";

        List<BookmarkDTO> result = new ArrayList<>();  // 반환할 List 객체 생성

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = " select * " +
                    " from bookmark ";

            preparedStatement = connection.prepareStatement(sql);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                BookmarkDTO bookmarkDTO = new BookmarkDTO();

                bookmarkDTO.setBookmarkNumber(rs.getString("bookmarkNumber"));
                bookmarkDTO.setBookmarkName(rs.getString("bookmarkName"));
                bookmarkDTO.setWifiName(rs.getString("WifiName"));
                bookmarkDTO.setBookmarkRegistDate(rs.getString("bookmarkRegistDate"));

                result.add(bookmarkDTO);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static BookmarkDTO SelectOneBookmark(String bookmarkNumber) {
        BookmarkDTO result = new BookmarkDTO();

        String url = "jdbc:mariadb://172.30.1.10:3306/mission1";
        String dbUserId = "mission1user";
        String dbPassword = "zero";

        // 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            // 데이터베이스 Connection 생성
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = " select * " +
                    " from bookmark " +
                    " where bookmarkNumber = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bookmarkNumber);

            rs = preparedStatement.executeQuery();

            rs.next();  // 없으면 SQLDataException 발생

            result.setBookmarkNumber(rs.getString("bookmarkNumber"));
            result.setBookmarkName(rs.getString("bookmarkName"));
            result.setWifiName(rs.getString("WifiName"));
            result.setBookmarkRegistDate(rs.getString("bookmarkRegistDate"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    public static void DeleteOneBookmark(String bookmarkNumber) {

        String url = "jdbc:mariadb://172.30.1.10:3306/mission1";
        String dbUserId = "mission1user";
        String dbPassword = "zero";

        // 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            // 데이터베이스 Connection 생성
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = " delete from bookmarkgroup " +
                    " where bookmarkNumber = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bookmarkNumber);

            preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}