package rs.xmood.droidtv.rsxmood;

import android.net.Uri;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.content.Intent;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ladedialog
        final ProgressDialog pd = ProgressDialog.show(this, "", "Verbinde zum Server...", true);
        WebView mWebView = (WebView) findViewById(R.id.activity_main_webview);
        //Aktiviere JavaScript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //WebView URL
        mWebView.loadUrl("http://rs.edu-point.de/#{%22url%22:%22/Dashboard%22}");
        //Entfernen des Ladedialoges
        mWebView.setWebViewClient(new WebViewClient() {
                                      @Override
                                      public void onPageFinished(WebView view, String url) {
                                          if (pd.isShowing()) {
                                              pd.dismiss();
                                          }
                                      }
                                  }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Aktion beim Klicken auf Versionsinfo
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //Oeffnet Acivity_Info beim Klick auf Versionsinfo
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, Info.class));
        }
        if (id == R.id.App_Update) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
            startActivity(browserIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    //DoubleTapBack zum Beenden
    private static final int TIME_INTERVAL = 2500; // Countdown zum Schliessen
    private long mBackPressed;

        @Override
            public void onBackPressed() {
                if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()){
                    super.onBackPressed();
                    return;
                }
                else { Toast.makeText(getBaseContext(), "Zum Beenden erneut tippen", Toast.LENGTH_SHORT).show(); }
            mBackPressed = System.currentTimeMillis();
            }

}
