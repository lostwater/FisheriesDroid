package com.xyxd.fisher.Activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.http.SslError;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.GeolocationPermissions;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.Gson;
import com.xyxd.fisher.R;
import com.xyxd.fisher.model.Event;
import com.xyxd.fisher.model.Shop;

public class WebMapActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_map);

        String str = getIntent().getExtras().get("shop").toString();
        Gson gson = new Gson();
        Shop shop = gson.fromJson(str, Shop.class);

        webView = (WebView) findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //当返回值是true的时候由webView来打开，为false的时候则由第三方或者系统默认的浏览器打开
                //return false;
                view.loadUrl(url);
                return true;
            }



            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                // callback.invoke(String origin, boolean allow, boolean remember);
                callback.invoke(origin, true, false);
            }
        });

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the location provider.
                location.getLongitude();
            }

            @Override
            public void onProviderDisabled(String provider) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1000, locationListener);

        WebSettings webSettings = webView.getSettings();
        webSettings.setAllowFileAccess(true);//设置启用或禁止访问文件数据
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true); //设置是否支持JavaScript
        webSettings.setBlockNetworkImage(false);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadsImagesAutomatically(true);
        //webSettings.
        if(shop.getLatitude()==0)
            shop.setLatitude(39.916973);
        if(shop.getLongitude() == 0)
            shop.setLongitude(116.410046);
        String la = shop.getLatitude().toString();
        String lg = shop.getLongitude().toString();
        //String url = "http://api.map.baidu.com/marker?location=40.047669,116.313082&title=myplace&content=myplace&output=html&src=xyxd|fisher";    //调起百度PC或web地图，且在（lat:39.916979519873，lng:116.41004950566）坐标点上显示名称\"我的位置\"，内容\"百度奎科大厦\"的信息窗口。\n"
        //String url = "http://map.baidu.com/mobile/webapp/place/marker/qt=inf&vt=map&act=read_share&code=131/third_party=uri_api&point=12948053.94%7C4845121&title=我的位置&content=百度奎科大厦";
        //String url = "http://m.amap.com/share/index/__q="+lg+","+la+"&lnglat="+la+","+lg+"&name="+shop.getName();
        //String url = "http://api.map.baidu.com/marker?location="+la+","+lg+"&title="+shop.getName()+"&content=" +shop.getName()+ "&output=html&src=xyxd|fisher";    //可以在PC、移动设备浏览器上打开打开该链接显示地图上的点\n";
        String url = "http://m.amap.com/?q="+la+","+lg +"&name="+shop.getName();
        webView.loadUrl(url);
    }
}
