package com.example.it.registeration_nadon;

import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout notice;
    private Button courseBtn;
    private Button statisticsBtn;
    private Button scheduleBtn;
    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<Notice> noticeList;
    public static String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //화면 세로 고정정
       setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        userID = getIntent().getStringExtra("userID");

        courseBtn = findViewById(R.id.courseBtn);
        statisticsBtn = findViewById(R.id.statisticsBtn);
        scheduleBtn = findViewById(R.id.scheduleBtn);
        notice = findViewById(R.id.notice);

        courseBtn.setOnClickListener(this);
        statisticsBtn.setOnClickListener(this);
        scheduleBtn.setOnClickListener(this);

        noticeListView = findViewById(R.id.noticeListView);
        noticeList = new ArrayList<Notice>();

        adapter = new NoticeListAdapter(getApplicationContext(), noticeList);
        noticeListView.setAdapter(adapter);

        new BackgroundTask().execute();
    }

    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.courseBtn:
                notice.setVisibility(View.GONE);
                courseBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                statisticsBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                scheduleBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                fragmentTransaction.replace(R.id.fragment, new CourseFragmnet());
                break;
            case R.id.statisticsBtn:
                notice.setVisibility(View.GONE);
                courseBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                statisticsBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                scheduleBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                fragmentTransaction.replace(R.id.fragment, new StatisticsFragment());
                break;
            case R.id.scheduleBtn:
                notice.setVisibility(View.GONE);
                courseBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                statisticsBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                scheduleBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

                fragmentTransaction.replace(R.id.fragment, new ScheduleFragment());
                break;
        }
        fragmentTransaction.commit();

    }
    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            target = "http://thetrax3.cafe24.com/NoticeList.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();

                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String noticeContent, noticeName, noticeDate;

                while (count < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(count);
                    noticeContent = object.getString("noticeContent");
                    noticeName = object.getString("noticeName");
                    noticeDate = object.getString("noticeDate");
                    Notice notice = new Notice(noticeContent, noticeName, noticeDate);
                    noticeList.add(notice);
                    count++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private long lastTimeBackPressed;

    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis()-lastTimeBackPressed<1500){
            finish();
            return;
        }
        Toast.makeText(this, "뒤로가기 버튼을 한 번 더 눌러 종료합니다", Toast.LENGTH_SHORT).show();
        lastTimeBackPressed = System.currentTimeMillis();
    }
}


