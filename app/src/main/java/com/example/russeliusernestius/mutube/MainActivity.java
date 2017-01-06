package com.example.russeliusernestius.mutube;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.params.CoreProtocolPNames;
import cz.msebera.android.httpclient.util.EntityUtils;


public class MainActivity extends AppCompatActivity {


    ListView lv;
    ArrayList<YoutubeBean> tmp_videos = new ArrayList<YoutubeBean>();
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        pd = new ProgressDialog(MainActivity.this);
        pd.setTitle("Loading....... ");

        new TheTask().execute();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                YoutubeBean item = (YoutubeBean) lv.getAdapter().getItem(position);
                Intent startVideo = new Intent(getApplicationContext(), YoutubePlayerFrag.class);
                startVideo.putExtra("VIDEO_ID",item.getId().toString() ) ;
                startActivity(startVideo);

               // Toast.makeText(MainActivity.this, item.getId().toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    public void getData() {

        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

        HttpGet request = new HttpGet("https://www.googleapis.com/youtube/v3/search?part=snippet&q=50cent&maxResults=50&type=video&order=date&key=AIzaSyBxSE61XG4570uPVo5YfQkf_13z88RKMIk");
        // HttpGet request = new HttpGet("https://www.googleapis.com/youtube/v3/search?part=snippet&q=Mr.Tecas&maxResults=50&type=video_row&key=AIzaSyBxSE61XG4570uPVo5YfQkf_13z88RKMIk");
        //
        try {
            HttpResponse response = httpclient.execute(request);
            HttpEntity resEntity = response.getEntity();
            String _response = EntityUtils.toString(resEntity); // content will be consume only once

            JSONObject json = new JSONObject(_response);

            JSONArray jsonArray = json.getJSONArray("items");

            for (int i = 0; i < jsonArray.length(); i++) {
                YoutubeBean ybean = new YoutubeBean();


                JSONObject jsonObject = jsonArray.getJSONObject(i);

                ybean.setTitle(jsonObject.getJSONObject("snippet").getString("title"));
                ybean.setId(jsonObject.getJSONObject("id").getString("videoId"));
                String thumbUrl = jsonObject.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("medium").getString("url");

                URL url1 = new URL(thumbUrl);
                Bitmap bmp = BitmapFactory.decodeStream(url1.openConnection().getInputStream());
                ybean.setThumbnails(bmp);

                tmp_videos.add(ybean);

            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        httpclient.getConnectionManager().shutdown();
    }

    class TheTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            pd.dismiss();
            YouTubeAdapter you = new YouTubeAdapter(MainActivity.this, tmp_videos);
            lv.setAdapter(you);
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
            getData();
            return null;
        }

    }
}

