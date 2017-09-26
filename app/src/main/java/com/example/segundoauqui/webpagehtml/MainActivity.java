package com.example.segundoauqui.webpagehtml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.segundoauqui.webpagehtml.Model.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.wvWebView)
    WebView wvWebView;
    DataBase dataBaseHelper = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //Rodrigo Chavez
        WebViewClient webViewClient = new WebViewClient();
        WebSettings webSettings = wvWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wvWebView.addJavascriptInterface(new Object()
        {
            @JavascriptInterface
            public void performClick(String strl)
            {
                try {
                    /*here*/
                    boolean isSaved;
                    User user = new User(strl);
                    isSaved =  dataBaseHelper.saveUser(user);
                    if(isSaved)
                        Toast.makeText(MainActivity.this, strl + "  Saved", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(MainActivity.this, "Error while saving..." , Toast.LENGTH_SHORT).show();
                }catch (Exception  e){
                    Toast.makeText(MainActivity.this, "Error while saving..." + e, Toast.LENGTH_SHORT).show();}

            }
        }, "ok");
        wvWebView.setWebViewClient(webViewClient);
        String customHtml = "<div align=\"center\">\n" +
                "<form action=\"/action_page.php\">\n" +
                "  <textarea name=\"myTextBox\" cols=\"20\" rows=\"4\" style=\" id=\"txtfname\">\n" +
                "\t</textarea><br><br>\n" +
                "   \t<script>\n" +
                "    function getValues() {\n" +
                "    document.getElementById(\"btnOK\").value = document.getElementById(\"txtfname\").value;}\n" +
                "    </script>\n" +
                "    <button type=\"button\" value=\"\" id=\"btnOK\" onclick=\"getValues();ok.performClick(this.value);\">Submit</button>\n" +
                "</form>\n" +
                "</div>\n";
        wvWebView.loadData(customHtml, "text/html", "UTF-8");

    }
}
