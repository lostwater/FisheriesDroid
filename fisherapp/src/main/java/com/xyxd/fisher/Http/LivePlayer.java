package com.xyxd.fisher.Http;

/**
 * Created by lostw on 2016/4/16.
 */
public class LivePlayer {
    public static String H5Content(String activityId, int height)
    {
        String hString = height + "";
        hString = hString + "px";
        return          "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "    <title>直播</title>\n" +
                        "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n" +
                        "    <meta content=\"width=device-width,initial-scale=1,user-scalable=no\" name=\"viewport\">\n" +
                        "    <style type=\"text/css\">\n" +
                        "        body {\n" +
                        "            padding: 0;\n" +
                        "            margin: 0;\n" +
                        "        }\n" +
                        "    </style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<div id=\"player\" style=\"width:100%;height:" +hString +";\">\n" +
                        "    <script type=\"text/javascript\" charset=\"utf-8\" src=\"http://yuntv.letv.com/player/live/blive.js\"></script>\n" +
                        "    <script>\n" +
                        "        var player = new CloudLivePlayer();\n" +
                        "        player.init({activityId:\"" +activityId+ "\"});\n" +
                        "    </script>\n" +
                        "</div>\n" +
                        "</body>\n" +
                        "</html>";
    }

    public static String H5Content(String activityId)
    {
        return          "<!DOCTYPE html>\n" +
                "<html style=\"width:100%;height:100%;\">\n" +
                "<head>\n" +
                "    <title>直播</title>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n" +
                "    <meta content=\"height=device-height,width=device-width,initial-scale=1,user-scalable=no,initial-scale=1\" name=\"viewport\">\n" +
                "    <style type=\"text/css\">\n" +
                "        body {\n" +
                "            padding: 0;\n" +
                "            margin: 0;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body style=\"width:100%;height:100%;\">\n" +
                "<div id=\"player\" style=\"width:100%;height:100%;\">\n" +
                "    <script type=\"text/javascript\" charset=\"utf-8\" src=\"http://yuntv.letv.com/player/live/blive.js\"></script>\n" +
                "    <script>\n" +
                "        var player = new CloudLivePlayer();\n" +
                "        player.init({activityId:\"" +activityId+ "\"});\n" +
                "    </script>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";
    }
}
