package issa.example.com.searchview;

        import android.app.SearchManager;
        import android.content.Context;
        import android.content.Intent;
        import android.graphics.Color;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.v7.app.ActionBarActivity;
        import android.support.v7.widget.SearchView;
        import android.text.Html;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MotionEvent;
        import android.view.View;
        import android.webkit.WebSettings;
        import android.webkit.WebView;
        import android.widget.SeekBar;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;

public class SearchResultsActivity extends ActionBarActivity {
    private TextView display;
    private WebView webView;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        //handleIntent(getIntent());
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_values;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               // progress_values = progress;
                //webView.scrollTo(0, progress*6);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

      Intent searchIntent = getIntent();
        if (Intent.ACTION_SEARCH.equals(searchIntent.getAction())){

            String query = searchIntent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(SearchResultsActivity.this, query, Toast.LENGTH_SHORT).show();


          try {
                InputStream is = getAssets().open("dictionary");

                // We guarantee that the available method returns the total
                // size of the asset...  of course, this does mean that a single
                // asset can't be more than 2 gigs.
                int size = is.available();

                // Read the entire asset into a local byte buffer.
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();

                // Convert the buffer into a string.
                String text = new String(buffer);

                // Finally stick the string into the text view.
                // Replace with whatever you need to have the text into.

                TextView tv = (TextView)findViewById(R.id.text);
                tv.setText(text);

          } catch (IOException e) {
                // Should never happen!
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

}

