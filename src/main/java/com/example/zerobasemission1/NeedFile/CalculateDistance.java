package com.example.zerobasemission1.NeedFile;

public class CalculateDistance {
    public static double calDistance(double lat, double lon, double getLat, double getLon) {
        double theta = lon - getLon;
        double dist = Math.sin(deg2rad(lat)) * Math.sin(deg2rad(getLat))
                + Math.cos(deg2rad(lat)) * Math.cos(deg2rad(getLat)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515 * 1609.344;

        return dist;  // 단위는 meter
    }

    // 10진수를 radian(라디안)으로 변환
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }


    // radian(라디안)을 10진수로 변환
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

}
