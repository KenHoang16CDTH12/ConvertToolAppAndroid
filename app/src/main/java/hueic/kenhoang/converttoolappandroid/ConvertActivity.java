package hueic.kenhoang.converttoolappandroid;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ConvertActivity extends AppCompatActivity {
    private EditText edInput;
    private TextView tvResult;
    private Spinner spUnit;

    Bundle bundle;
    int a;
    int pos;
    private String[] arrUnit;
    ArrayAdapter<CharSequence> adapter;
    private int currentUnit = 0;//meter
            //Distance Convert
    private float [][] arrDistanceConvertRate
/*
               meter                    | mile             | cm              | inch          |yd
*/
        = {
                 {1,                  0.000621371f,         100,              39.3701f,     1.09361f},
                 {1609.34f,                      1,      160934,                 63360,         1760},
                 {0.01f,                6.2137e-6f,           1,            0.3936996f,   0.0109361f},
                 {0.0254f,              1.5783e-5f,       2.54f,                     1,   0.0277778f},
                 {0.9144f,            0.000568182f,       91.4f,                    36,         1   }
              };

              //Weight Convert
    private float [][] arrWeightConvertRate
/*
                gram       | kilogram          | kip     | microgram                |miligram      |pound          |tạ       |tấn
*/
      = {
              {   1,           0.001f,         0.000f,            1000000,              1000,       0.002f,            0,           0},
              {1000,                1,         0.002f,         1000000000,           1000000,       2.205f,        0.01f,     0.001f },
              {453592.37f,   453.592f,              1,      453592370000f,        453592370f,         1000,       4.536f,      0.454f},
              {1000000,             0,              0,                  1,                 0,            0,            0,           0},
              {0.001f,              0,              0,               1000,                 1,            0,            0,           0},
              {453.592f,       0.454f,         0.001f,          453592370,        453592.37f,            1,       0.005f,           0},
              {100000,            100,         0.220f,      100000000000f,         100000000,     220.462f,            1,        0.1f},
              {1000000,          1000,         2.205f,     1000000000000f,        1000000000,    2204.623f,           10,           1} };

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
        setContentView(R.layout.activity_convert);
        addControls();
        addEvents();
    }

    private void addEvents() {

        spUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                switch (position) {
                  case 0:
                      currentUnit = 0;
                      tvResult.setText("Result");
                      break;
                  case 1:
                      currentUnit = 1;
                      tvResult.setText("Result");
                      break;
                  case 2:
                      currentUnit = 2;
                      tvResult.setText("Result");
                  case 3:
                      currentUnit = 3;
                      tvResult.setText("Result");
                      break;
                  case 4:
                      currentUnit = 4;
                      tvResult.setText("Result");
                      break;
                  case 5:
                      currentUnit = 5;
                      tvResult.setText("Result");
                      break;
                  case 6:
                      currentUnit = 6;
                      tvResult.setText("Result");
                  case 7:
                      currentUnit = 7;
                      tvResult.setText("Result");
                      break;
              }
              adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        edInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (a == MainActivity.DISTANCE_CONVERT) {
                    convert0(currentUnit, arrDistanceConvertRate);
                }
                else if (a == MainActivity.WEIGHT_CONVERT) {
                    convert1(currentUnit, arrWeightConvertRate);
                }
                else if (a == MainActivity.TEMPERATURE_CONVERT) {
                    convert2(currentUnit);

                }
                else if (a == MainActivity.SPEED_CONVERT) {
                    convert3(currentUnit);
                }

            }
        });
    }

    private void convert3(int currentUnit) {
        if (edInput.getText().toString().trim().length() <= 0) {
            tvResult.setText("Result");
            return;
        }

        //Convert
        float input = Float.parseFloat(edInput.getText().toString());
        String result = "";

        for (int i = 0; i < arrUnit.length; i++) {
            if (currentUnit != i) {
                result += arrUnit[i];
                result += ": ";
                if (pos == 0) {
                    result += input*1000 / 3600;
                }
                else {
                    result += input * 3.6 ;
                }
                result += "\n";
            }
        }
        tvResult.setText(result);
    }

    private void convert2(int currentUnit) {
        if (edInput.getText().toString().trim().length() <= 0) {
            tvResult.setText("Result");
            return;
        }

        //Convert
        float input = Float.parseFloat(edInput.getText().toString());
        String result = "";

        for (int i = 0; i < arrUnit.length; i++) {
            if (currentUnit != i) {
                result += arrUnit[i];
                result += ": ";
                if (pos == 0) {
                    result += input * 1.8 + 32;
                }
                else {
                    result += (input - 32)/1.8 ;
                }
                result += "\n";
            }
        }
        tvResult.setText(result);
    }

    private void convert1(int currentUnit, float[][] arrWeightConvertRate) {
        if (edInput.getText().toString().trim().length() <= 0) {
            tvResult.setText("Result");
            return;
        }

        //Convert
        float input = Float.parseFloat(edInput.getText().toString());
        String result = "";

        for (int i = 0; i < arrUnit.length; i++) {
            if (currentUnit != i) {
                result += arrUnit[i];
                result += ": ";
                result += input*arrWeightConvertRate[currentUnit][i];
                result += "\n";
            }
        }
        tvResult.setText(result);
    }


    private void convert0(int currentUnit, float[][] arrDistanceConvertRate) {
        //Check Input
        if (edInput.getText().toString().trim().length() <= 0) {
            tvResult.setText("Result");
            return;
        }

        //Convert
        float input = Float.parseFloat(edInput.getText().toString());
        String result = "";

        for (int i = 0; i < arrUnit.length; i++) {
            if (currentUnit != i) {
                result += arrUnit[i];
                result += ": ";
                result += input*arrDistanceConvertRate[currentUnit][i];
                result += "\n";
            }
        }
        tvResult.setText(result);
    }

    private void addControls() {
        //findview
        edInput = (EditText) findViewById(R.id.edInput);
        tvResult = (TextView) findViewById(R.id.tvResult);
        spUnit = (Spinner) findViewById(R.id.spUnit);
        //get values of arrUnit
        Intent intent = getIntent();
        bundle = intent.getBundleExtra("MyBundle");
        a = bundle.getInt("ConvertType");
        if (a == MainActivity.DISTANCE_CONVERT) {
            arrUnit = getResources().getStringArray(R.array.unit_distance);
            //set adapter
            adapter = ArrayAdapter.createFromResource(this, R.array.unit_distance, R.layout.support_simple_spinner_dropdown_item);
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        }
        else if (a == MainActivity.WEIGHT_CONVERT) {
            arrUnit = getResources().getStringArray(R.array.unit_weight);
            //set adapter
            adapter = ArrayAdapter.createFromResource(this, R.array.unit_weight, R.layout.support_simple_spinner_dropdown_item);
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        }
        else if (a == MainActivity.TEMPERATURE_CONVERT) {
            arrUnit = getResources().getStringArray(R.array.unit_temperature);
            //set adapter
            adapter = ArrayAdapter.createFromResource(this, R.array.unit_temperature, R.layout.support_simple_spinner_dropdown_item);
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        }
        else if (a == MainActivity.SPEED_CONVERT) {
            arrUnit = getResources().getStringArray(R.array.unit_speed);
            //set adapter
            adapter = ArrayAdapter.createFromResource(this, R.array.unit_speed, R.layout.support_simple_spinner_dropdown_item);
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        }
        spUnit.setAdapter(adapter);
    }
}
