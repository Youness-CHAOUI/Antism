package com.injaz2019.antism.classes.Utils;

import com.injaz2019.antism.classes.Metier.Video;

import java.util.ArrayList;

public class YoutubeApiConfig {
    public static ArrayList<Video> expertsVideos = new ArrayList<Video>() {
        {
            add(new Video(1, "آشنو هو التوحد ؟ مع الاخصائية النفسية مريم وحمان", "2kVhCnBTc38", "3 دقائق و 19 ثانية"));
            add(new Video(1, "الكشف بكري بالذهب مشري - آجي نكتاشفوا - أعراض التوحد مع الأخصائية النفسية - مريم وحمان", "7NRTcxrMQYo", "6 دقائق و 1 ثانية"));
            add(new Video(1, "الكشف بكري بالذهب مشري -  آجي نكتاشفو2 - طرق تشخيص التوحد مع الأخصائية النفسية - مريم وحمان", "Fn_fu9ol_xA", "2 دقائق و 5 ثانية"));
            add(new Video(1, "كيفاش نتعامل مع نوبات الغضب عند الطفل التوحدي ؟ مع الأخصائية النفسية - مريم وحمان", "ziF6Q_7-NZ4", "3 دقائق و 43 ثانية"));
            add(new Video(1, "من تقديم الاخصائية مريم وحمان - أشناهوا - ABA", "ZIS3HAkLjdw", "3 دقائق و 23 ثانية"));
            add(new Video(1, "من تقديم الاخصائية مريم وحمان - أشناهوا - ABA - 2", "ck2A_04lAWs", "0 دقائق و 57 ثانية"));
            add(new Video(1, "ABA الأخصائية النفسية - مريم وحمان - كيفاش نعاون ولدي باعتماد تقنيات ل", "-tx3iH7nNzs", "5 دقائق و 36 ثانية"));
            add(new Video(1, "شكون هما الأشخاص اللي كايتدخلو من أجل تتبع حالة الطفل التوحدي ؟ مع الأخصائية النفسية - مريم وحمان", "DMeHCsJsCqs", "6 دقائق و 52 ثانية"));

            add(new Video(1, "كيفاش نعرف بلي ولدي عندو التوحد ؟ مع الدكتور أحمد فارسي", "MwDTzm2ePf0", "7 دقائق و 36 ثانية"));
            add(new Video(1, "كيفاش نخدمو على موهبة طفلنا التوحدي ؟", "czDfEsfMonk", "3 دقائق و 28 ثانية"));
            add(new Video(1, "كيفاش نخليو وليداتنا يندامجو فالمجتمع ؟", "0csi6FdzSe4", "2 دقائق و 27 ثانية"));
            add(new Video(1, "كيفاش كايوجهوني مسؤليين الجمعيات و المراكز نخدم مع ولدي فالدار ؟", "smltQQ7uIlY", "12 دقائق و 24 ثانية"));
//            add(new Video(1, "xxxxxxxxx", "xxxxx", "تت دقائق و نن ثانية"));
        }
    };
    public static ArrayList<Video> parentsVideos = new ArrayList<Video>() {
        {
            add(new Video(1, "أباء أقوى من التحدي - الجزء الأول", "U2ipKcd5gEE", "15 دقائق و 37 ثانية"));
            add(new Video(1, "أباء أقوى من التحدي - الجزء الثاني", "eIkG7-gtjzk", "12 دقائق و 43 ثانية"));
            add(new Video(1, "أباء أقوى من التحدي - الجزء الثالث", "GIrMxcMNWWs", "8 دقائق و 34 ثانية"));
//            add(new Video(1, "xxxxxxxxx", "xxxxx", "تت دقائق و نن ثانية"));
        }
    };
    private static String API_KEY = "AIzaSyD1XMlBm-R82TAg2AR3AcUYkCfVXitAsrE";

    public YoutubeApiConfig() {
    }

    public static String getApiKey() {
        return API_KEY;
    }
}
