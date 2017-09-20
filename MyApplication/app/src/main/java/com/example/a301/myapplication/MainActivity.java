package com.example.a301.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button testButton;
    TextView textView;

    RetrofitService service;
    static String reqURL = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastGrib?ServiceKey=gXBXUzv%2FaP2AnP29hzBPRAYxFL28gTrw%2BWeq9BksUPMBGn4e1Q5yOtK1AtOIufpUCgLTJY0RLWSwMTp96232mg%3D%3D&nx=60&ny=127&base_date=20170919&base_time=2100&_type=json";


    public GsonVO gsonList;
    public static ArrayList<DataVO> dataList = new ArrayList<DataVO>();
    public static ArrayList<DataObject> arr = new ArrayList<DataObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.TestText);
        testButton = (Button) findViewById(R.id.button1);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });


    }


    public void loadData() {

        service = RetrofitClient.getClient(reqURL).create(RetrofitService.class);
        service.loadAnswer().enqueue(new Callback<GsonVO>() {
            @Override
            public void onResponse(Call<GsonVO> call, Response<GsonVO> response) {
                if (response.isSuccessful()) {
                    gsonList = response.body();
                    dataList = gsonList.getDataList();

                    for (int i = 0; i < dataList.size(); i++) {
                        String baseDate = dataList.get(i).getBaseDate();
                        String baseTime = dataList.get(i).getBaseTime();
                        String category = dataList.get(i).getCategory();
                        String nx = dataList.get(i).getNx();
                        String ny = dataList.get(i).getNy();
                        String obsrValue = dataList.get(i).getObsrValue();
                        arr.add(new DataObject(baseDate, baseTime, category, nx, ny, obsrValue));
                    }

                    textView.setText(arr.get(0).getBaseDate() + "\n" + arr.get(0).getBaseTime() + "\n" + arr.get(0).getCategory() + "\n" + arr.get(0).getObsrValue() + "\n");
                } else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<GsonVO> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");
            }
        });
    }

}
