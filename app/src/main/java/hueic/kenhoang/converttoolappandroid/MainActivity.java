package hueic.kenhoang.converttoolappandroid;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout distanceItem, weightItem, temperatureItem, speedItem;
    public static int DISTANCE_CONVERT = 0;
    public static int WEIGHT_CONVERT = 1;
    public static int TEMPERATURE_CONVERT = 2;
    public static int SPEED_CONVERT = 3;

    private static int convertType = DISTANCE_CONVERT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //hide action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        distanceItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDistanceConvert();
            }
        });
        weightItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleWeightConver();
            }
        });
        temperatureItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTemperatureConvert();
            }
        });
        speedItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSpeedConvert();
            }
        });
    }

    private void handleSpeedConvert() {
        convertType = SPEED_CONVERT;
        Intent intent = new Intent(this, ConvertActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ConvertType", convertType);
        intent.putExtra("MyBundle", bundle);
        startActivity(intent);
    }

    private void handleTemperatureConvert() {
        convertType = TEMPERATURE_CONVERT;
        Intent intent = new Intent(this, ConvertActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ConvertType", convertType);
        intent.putExtra("MyBundle", bundle);
        startActivity(intent);
    }

    private void handleWeightConver() {
        convertType = WEIGHT_CONVERT;
        Intent intent = new Intent(this, ConvertActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ConvertType", convertType);
        intent.putExtra("MyBundle", bundle);
        startActivity(intent);
    }

    private void handleDistanceConvert() {
        convertType = DISTANCE_CONVERT;
        Intent intent = new Intent(this, ConvertActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ConvertType", convertType);
        intent.putExtra("MyBundle", bundle);
        startActivity(intent);
    }

    private void addControls() {
        distanceItem = (RelativeLayout) findViewById(R.id.DistanceItem);
        weightItem = (RelativeLayout) findViewById(R.id.WeightItem);
        temperatureItem = (RelativeLayout) findViewById(R.id.TemperatureItem);
        speedItem = (RelativeLayout) findViewById(R.id.SpeedItem);
    }
}
