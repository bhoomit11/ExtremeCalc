package com.extremecalculator.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.extremecalculator.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SpeedDistanceAndTimeCalculatorActivity extends BaseActivity {

    @Bind(R.id.ib_screenBack)
    ImageButton ibScreenBack;
    @Bind(R.id.tv_resultTitle)
    TextView tvResultTitle;
    @Bind(R.id.tv_SDTResult)
    TextView tvSDTResult;
    @Bind(R.id.tv_timeResultUnit)
    TextView tvTimeResultUnit;
    @Bind(R.id.tv_distanceResultUnit)
    TextView tvDistanceResultUnit;
    @Bind(R.id.tv_speedResultUnit)
    TextView tvSpeedResultUnit;
    @Bind(R.id.tv_SDTType)
    TextView tvSDTType;
    @Bind(R.id.ll_speedView)
    LinearLayout llSpeedView;
    @Bind(R.id.ll_distanceView)
    LinearLayout llDistanceView;
    @Bind(R.id.ll_timeview)
    LinearLayout llTimeview;
    @Bind(R.id.edt_speedValue)
    EditText edtSpeedValue;
    @Bind(R.id.tv_speedUnit)
    TextView tvSpeedUnit;
    @Bind(R.id.edt_distanceValue)
    EditText edtDistanceValue;
    @Bind(R.id.tv_distanceUnit)
    TextView tvDistanceUnit;
    @Bind(R.id.edt_TimeValue_hour)
    EditText edtTimeValue_hour;
    @Bind(R.id.edt_TimeValue_min)
    EditText edtTimeValue_min;
    @Bind(R.id.edt_TimeValue_sec)
    EditText edtTimeValue_sec;

    private String[] distanceUnit;
    private String[] speedUnits;
    private String[] TimeUnits;
    private String[] calculationType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_distance_and_time_calculator);
        ButterKnife.bind(this);
        init();
    }
    public void init(){
        //  Get Types
        distanceUnit=getResources().getStringArray(R.array.distanceUnit);
        speedUnits=getResources().getStringArray(R.array.speedUnits);
        TimeUnits=getResources().getStringArray(R.array.timeUnits);
        calculationType=getResources().getStringArray(R.array.SDTcalculationType);

        // Set Default Types
        tvSpeedUnit.setText(speedUnits[0]);
        tvDistanceUnit.setText(distanceUnit[0]);
        tvSDTType.setText(calculationType[0]);
        tvTimeResultUnit.setText("  -  ");
        tvDistanceResultUnit.setText(distanceUnit[0]);
        tvSpeedResultUnit.setText(speedUnits[0]);

        // Default Values
        llTimeview.setVisibility(View.GONE);
    }

    @OnClick({R.id.tv_speedUnit})
    void clickSelectSpeedUnit(View view){
        if (!isClickEnable())
            return;

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
        builder.setItems(speedUnits, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvSpeedUnit.setText(speedUnits[which]);
            }
        });
        builder.setTitle("Select Speed Unit");
        builder.show();
    }

    @OnClick({R.id.tv_distanceUnit})
    void clickSelectDistanceResulUnit(View view){
        if (!isClickEnable())
            return;

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
        builder.setItems(distanceUnit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvDistanceUnit.setText(distanceUnit[which]);
            }
        });
        builder.setTitle("Select Distance Unit");
        builder.show();
    }

    @OnClick({R.id.tv_distanceResultUnit})
    void clickSelectDistanceResultUnit(View view){
        if (!isClickEnable())
            return;

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
        builder.setItems(distanceUnit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvDistanceResultUnit.setText(distanceUnit[which]);
            }
        });
        builder.setTitle("Select Result Unit");
        builder.show();
    }

    @OnClick({R.id.tv_speedResultUnit})
    void clickSelectSpeedResultUnit(View view){
        if (!isClickEnable())
            return;

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
        builder.setItems(speedUnits, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvSpeedResultUnit.setText(speedUnits[which]);
            }
        });
        builder.setTitle("Select Result Unit");
        builder.show();
    }

    @OnClick({R.id.tv_SDTType})
    void clickSelectCalculationType(View view){
        if (!isClickEnable())
            return;

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
        builder.setItems(calculationType, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvSDTType.setText(calculationType[which]);
                clearAll();
                switch (which){
                    case 0:
                        llTimeview.setVisibility(View.GONE);
                        llDistanceView.setVisibility(View.VISIBLE);
                        llSpeedView.setVisibility(View.VISIBLE);

                        tvTimeResultUnit.setVisibility(View.VISIBLE);
                        tvDistanceResultUnit.setVisibility(View.GONE);
                        tvSpeedResultUnit.setVisibility(View.GONE);
                        break;
                    case 1:
                        llTimeview.setVisibility(View.VISIBLE);
                        llDistanceView.setVisibility(View.GONE);
                        llSpeedView.setVisibility(View.VISIBLE);

                        tvTimeResultUnit.setVisibility(View.GONE);
                        tvDistanceResultUnit.setVisibility(View.VISIBLE);
                        tvSpeedResultUnit.setVisibility(View.GONE);
                        break;
                    case 2:
                        llTimeview.setVisibility(View.VISIBLE);
                        llDistanceView.setVisibility(View.VISIBLE);
                        llSpeedView.setVisibility(View.GONE);

                        tvTimeResultUnit.setVisibility(View.GONE);
                        tvDistanceResultUnit.setVisibility(View.GONE);
                        tvSpeedResultUnit.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        builder.setTitle("Select Calculation Type");
        builder.show();
    }

    /**
     * Clear the Value
     */
    @OnClick({R.id.tv_clear})
    void clickClear(View view) {
        if (!isClickEnable())
            return;
        clearAll();
    }

    /**
     * Calculate Module
     */
    @OnClick({R.id.tv_calculate})
    void clickCalculate(View view) {
        if (!isClickEnable())
            return;
        if (!isDataValidate())
            return;
        tvResultTitle.setText("Result : ");
        if(tvSDTType.getText().toString().contains("Time"))
            calculateTime();
        else if(tvSDTType.getText().toString().contains("Distance"))
            calculateDistance();
        else if(tvSDTType.getText().toString().contains("Speed"))
            calculateSpeed();
    }

    private boolean isDataValidate() {
        boolean isDataValidate = true;

        if(llTimeview.getVisibility()==View.GONE){
            if (TextUtils.isEmpty(edtSpeedValue.getText().toString().trim())) {
                isDataValidate = false;
                showErrorMessage("Please Enter Speed Value");
            }
            else if (TextUtils.isEmpty(edtDistanceValue.getText().toString().trim())) {
                isDataValidate = false;
                showErrorMessage("Please Enter Distance Value");
            }
        }
        else if(llDistanceView.getVisibility()==View.GONE){
            if (TextUtils.isEmpty(edtSpeedValue.getText().toString().trim())) {
                isDataValidate = false;
                showErrorMessage("Please Enter Speed Value");
            }
            else if (TextUtils.isEmpty(edtTimeValue_hour.getText().toString().trim())) {
                isDataValidate = false;
                showErrorMessage("Please Enter Hour Value");
            }
            else if (TextUtils.isEmpty(edtTimeValue_min.getText().toString().trim())) {
                isDataValidate = false;
                showErrorMessage("Please Enter Minute Value");
            }
            else if (TextUtils.isEmpty(edtTimeValue_sec.getText().toString().trim())) {
                isDataValidate = false;
                showErrorMessage("Please Enter Second Value");
            }
        }
        else if(llSpeedView.getVisibility()==View.GONE){
            if (TextUtils.isEmpty(edtDistanceValue.getText().toString().trim())) {
                isDataValidate = false;
                showErrorMessage("Please Enter Distance Value");
            }
            else if (TextUtils.isEmpty(edtTimeValue_hour.getText().toString().trim())) {
                isDataValidate = false;
                showErrorMessage("Please Enter Hour Value");
            }
            else if (TextUtils.isEmpty(edtTimeValue_min.getText().toString().trim())) {
                isDataValidate = false;
                showErrorMessage("Please Enter Minute Value");
            }
            else if (TextUtils.isEmpty(edtTimeValue_sec.getText().toString().trim())) {
                isDataValidate = false;
                showErrorMessage("Please Enter Second Value");
            }
        }

        return isDataValidate;
    }


    private void calculateTime()
    {
        Double speed,distance,time;
        speed=Double.parseDouble(edtSpeedValue.getText().toString());
        distance=Double.parseDouble(edtDistanceValue.getText().toString());
        Long time_hour,time_min,time_sec;

        try {
            //Speed Calculation
            if (tvSpeedUnit.getText().toString().equals(speedUnits[0])) {
                speed=speed*1;
            } else if (tvSpeedUnit.getText().toString().equals(speedUnits[1])) {
                speed = speed*0.01666666666666666666666666;
            } else if (tvSpeedUnit.getText().toString().equals(speedUnits[2])) {
                speed = speed*0.27777777777777777777777777;
            } else if (tvSpeedUnit.getText().toString().equals(speedUnits[3])) {
                speed = speed*0.3048;
            } else if (tvSpeedUnit.getText().toString().equals(speedUnits[4])) {
                speed = speed*0.00508;
            } else if (tvSpeedUnit.getText().toString().equals(speedUnits[5])) {
                speed = speed*0.01524;
            } else if (tvSpeedUnit.getText().toString().equals(speedUnits[6])) {
                speed = speed*0.44704;
            } else if (tvSpeedUnit.getText().toString().equals(speedUnits[7])) {
                speed = speed*0.514444444444;
            } else if (tvSpeedUnit.getText().toString().equals(speedUnits[8])) {
                speed = speed*299792458;
            }

            //Distance calculation
            if (tvDistanceUnit.getText().toString().equals(distanceUnit[0])) {
                distance=distance*1;
            } else if (tvDistanceUnit.getText().toString().equals(distanceUnit[1])) {
                distance = distance*0.001 ;
            } else if (tvDistanceUnit.getText().toString().equals(distanceUnit[2])) {
                distance = distance*0.01 ;
            } else if (tvDistanceUnit.getText().toString().equals(distanceUnit[3])) {
                distance = distance*1000 ;
            } else if (tvDistanceUnit.getText().toString().equals(distanceUnit[4])) {
                distance = distance*0.0254 ;
            } else if (tvDistanceUnit.getText().toString().equals(distanceUnit[5])) {
                distance = distance*0.3048 ;
            } else if (tvDistanceUnit.getText().toString().equals(distanceUnit[6])) {
                distance = distance*0.9144 ;
            } else if (tvDistanceUnit.getText().toString().equals(distanceUnit[7])) {
                distance = distance*1.8288 ;
            } else if (tvDistanceUnit.getText().toString().equals(distanceUnit[8])) {
                distance = distance*1609.344 ;
            } else if (tvDistanceUnit.getText().toString().equals(distanceUnit[9])) {
                distance = distance*1852 ;
            } else if (tvDistanceUnit.getText().toString().equals(distanceUnit[10])) {
                distance = distance*(Long.parseLong("299792458")) ;
            } else if (edtDistanceValue.getText().toString().equals(distanceUnit[11])) {
                distance = distance*(Long.parseLong("17987547480")) ;
            } else if (edtDistanceValue.getText().toString().equals(distanceUnit[12])) {
                distance = distance*(Long.parseLong("1079252848800")) ;
            } else if (tvDistanceUnit.getText().toString().equals(distanceUnit[13])) {
                distance = distance*(Long.parseLong("9460895208540000"));
            } else if (tvDistanceUnit.getText().toString().equals(distanceUnit[14])){
                distance = distance*(Long.parseLong("30842518379800000")) ;
            } else if (tvDistanceUnit.getText().toString().equals(distanceUnit[15])) {
                distance = distance*(Long.parseLong("149597870000")) ;
            }

            Log.d("Speed", String.valueOf(speed));
            Log.d("Distance", String.valueOf(distance));
            time = (double)(distance/speed);
            time_hour = (long)(time.longValue() / 3600);
            time_min = (long)(time.longValue() % 3600) / 60;
            time_sec = (long)(time.longValue() % 3600) % 60;
            tvResultTitle.setText(tvResultTitle.getText().toString() + "Time From Speed & Distance");
            tvSDTResult.setText(time_hour + " Hour " + time_min + " Minute " + time_sec + " Seconds ");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void calculateDistance()
    {
        double speed,distance,time;
        double convertedDistance=0.0;
        speed=Double.parseDouble(edtSpeedValue.getText().toString());
        Long time_hour,time_min,time_sec;
        try {
            //Speed Calculation
            if (tvSpeedUnit.getText().toString().equals(speedUnits[0])) {
                speed = speed * 1;
            } else if (tvSpeedUnit.getText().toString().equals(speedUnits[1])) {
                speed = speed * 0.01666666666666666666666666;
            } else if (tvSpeedUnit.getText().toString().equals(speedUnits[2])) {
                speed = speed * 0.27777777777777777777777777;
            } else if (tvSpeedUnit.getText().toString().equals(speedUnits[3])) {
                speed = speed * 0.3048;
            } else if (tvSpeedUnit.getText().toString().equals(speedUnits[4])) {
                speed = speed * 0.00508;
            } else if (tvSpeedUnit.getText().toString().equals(speedUnits[5])) {
                speed = speed * 0.01524;
            } else if (tvSpeedUnit.getText().toString().equals(speedUnits[6])) {
                speed = speed * 0.44704;
            } else if (tvSpeedUnit.getText().toString().equals(speedUnits[7])) {
                speed = speed * 0.514444444444;
            } else if (tvSpeedUnit.getText().toString().equals(speedUnits[8])) {
                speed = speed * 299792458;
            }

            //Time Calculation
            time_hour=Long.parseLong(edtTimeValue_hour.getText().toString());
            time_min=Long.parseLong(edtTimeValue_min.getText().toString());
            time_sec=Long.parseLong(edtTimeValue_sec.getText().toString());

            time=(double)((((time_hour*60)+time_min)*60)+time_sec);

            distance=speed*time;

            //Distance Result calculation
            if (tvDistanceResultUnit.getText().toString().equals(distanceUnit[0])) {
                convertedDistance=distance*1;
            } else if (tvDistanceResultUnit.getText().toString().equals(distanceUnit[1])) {
                convertedDistance = distance/0.001 ;
            } else if (tvDistanceResultUnit.getText().toString().equals(distanceUnit[2])) {
                convertedDistance = distance/0.01 ;
            } else if (tvDistanceResultUnit.getText().toString().equals(distanceUnit[3])) {
                convertedDistance = distance/1000 ;
            } else if (tvDistanceResultUnit.getText().toString().equals(distanceUnit[4])) {
                convertedDistance = distance/0.0254 ;
            } else if (tvDistanceResultUnit.getText().toString().equals(distanceUnit[5])) {
                convertedDistance = distance/0.3048 ;
            } else if (tvDistanceResultUnit.getText().toString().equals(distanceUnit[6])) {
                convertedDistance = distance/0.9144 ;
            } else if (tvDistanceResultUnit.getText().toString().equals(distanceUnit[7])) {
                convertedDistance = distance/1.8288 ;
            } else if (tvDistanceResultUnit.getText().toString().equals(distanceUnit[8])) {
                convertedDistance = distance/1609.344 ;
            } else if (tvDistanceResultUnit.getText().toString().equals(distanceUnit[9])) {
                convertedDistance = distance/1852 ;
            } else if (tvDistanceResultUnit.getText().toString().equals(distanceUnit[10])) {
                convertedDistance = distance/(Long.parseLong("299792458")) ;
            } else if (tvDistanceResultUnit.getText().toString().equals(distanceUnit[11])) {
                convertedDistance = distance/(Long.parseLong("17987547480")) ;
            } else if (tvDistanceResultUnit.getText().toString().equals(distanceUnit[12])) {
                convertedDistance = distance/(Long.parseLong("1079252848800")) ;
            } else if (tvDistanceResultUnit.getText().toString().equals(distanceUnit[13])) {
                convertedDistance = distance/(Long.parseLong("9460895208540000"));
            } else if (tvDistanceResultUnit.getText().toString().equals(distanceUnit[14])){
                convertedDistance = distance/(Long.parseLong("30842518379800000")) ;
            } else if (tvDistanceResultUnit.getText().toString().equals(distanceUnit[15])) {
                convertedDistance = distance/(Long.parseLong("149597870000")) ;
            }

            tvResultTitle.setText(tvResultTitle.getText().toString() + "Distance From Speed & Time in "+
                    tvDistanceResultUnit.getText().toString());
            NumberFormat nf = new DecimalFormat("##.####");
            tvSDTResult.setText(nf.format(convertedDistance));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void calculateSpeed()
    {
        try {
            double speed,distance,time;
            double convertedSpeed=0.0;
            distance=Double.parseDouble(edtDistanceValue.getText().toString());
            Long time_hour,time_min,time_sec;

            //Distance calculation
            if (tvDistanceUnit.getText().toString().equals(distanceUnit[0])) {
                distance=distance/1;
            } else if (tvDistanceUnit.getText().toString().equals(distanceUnit[1])) {
                distance = distance*0.001 ;
            } else if (tvDistanceUnit.getText().toString().equals(distanceUnit[2])) {
                distance = distance*0.01 ;
            } else if (tvDistanceUnit.getText().toString().equals(distanceUnit[3])) {
                distance = distance*1000 ;
            } else if (tvDistanceUnit.getText().toString().equals(distanceUnit[4])) {
                distance = distance*0.0254 ;
            } else if (tvDistanceUnit.getText().toString().equals(distanceUnit[5])) {
                distance = distance*0.3048 ;
            } else if (tvDistanceUnit.getText().toString().equals(distanceUnit[6])) {
                distance = distance*0.9144 ;
            } else if (tvDistanceUnit.getText().toString().equals(distanceUnit[7])) {
                distance = distance*1.8288 ;
            } else if (tvDistanceUnit.getText().toString().equals(distanceUnit[8])) {
                distance = distance*1609.344 ;
            } else if (tvDistanceUnit.getText().toString().equals(distanceUnit[9])) {
                distance = distance*1852 ;
            } else if (tvDistanceUnit.getText().toString().equals(distanceUnit[10])) {
                distance = distance*(Long.parseLong("299792458")) ;
            } else if (edtDistanceValue.getText().toString().equals(distanceUnit[11])) {
                distance = distance*(Long.parseLong("17987547480")) ;
            } else if (edtDistanceValue.getText().toString().equals(distanceUnit[12])) {
                distance = distance*(Long.parseLong("1079252848800")) ;
            } else if (tvDistanceUnit.getText().toString().equals(distanceUnit[13])) {
                distance = distance*(Long.parseLong("9460895208540000"));
            } else if (tvDistanceUnit.getText().toString().equals(distanceUnit[14])){
                distance = distance*(Long.parseLong("30842518379800000")) ;
            } else if (tvDistanceUnit.getText().toString().equals(distanceUnit[15])) {
                distance = distance*(Long.parseLong("149597870000")) ;
            }

            //Time Calculation
            time_hour=Long.parseLong(edtTimeValue_hour.getText().toString());
            time_min=Long.parseLong(edtTimeValue_min.getText().toString());
            time_sec=Long.parseLong(edtTimeValue_sec.getText().toString());

            time=(double)((((time_hour*60)+time_min)*60)+time_sec);
            speed=(double)(distance/time);


            //Speed Result Calculation
            if (tvSpeedResultUnit.getText().toString().equals(speedUnits[0])) {
                convertedSpeed = speed / 1;
            } else if (tvSpeedResultUnit.getText().toString().equals(speedUnits[1])) {
                convertedSpeed = speed / 0.01666666666666666666666666;
            } else if (tvSpeedResultUnit.getText().toString().equals(speedUnits[2])) {
                convertedSpeed = speed / 0.27777777777777777777777777;
            } else if (tvSpeedResultUnit.getText().toString().equals(speedUnits[3])) {
                convertedSpeed = speed / 0.3048;
            } else if (tvSpeedResultUnit.getText().toString().equals(speedUnits[4])) {
                convertedSpeed = speed / 0.00508;
            } else if (tvSpeedResultUnit.getText().toString().equals(speedUnits[5])) {
                convertedSpeed = speed / 0.01524;
            } else if (tvSpeedResultUnit.getText().toString().equals(speedUnits[6])) {
                convertedSpeed = speed / 0.44704;
            } else if (tvSpeedResultUnit.getText().toString().equals(speedUnits[7])) {
                convertedSpeed = speed / 0.514444444444;
            } else if (tvSpeedResultUnit.getText().toString().equals(speedUnits[8])) {
                convertedSpeed = speed / 299792458;
            }

            tvResultTitle.setText(tvResultTitle.getText().toString() + "Speed From Distance & Time in " +
                    tvSpeedResultUnit.getText().toString());
            NumberFormat nf = new DecimalFormat("##.####");
            tvSDTResult.setText(nf.format(convertedSpeed));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    @OnClick({R.id.ib_screenBack})
    void clickBack(View view)
    {
        if(!isClickEnable())
            return;
        onBackPressed();
    }

    void clearAll(){
        tvResultTitle.setText("Result : ");
        tvSDTResult.setText("0000");
        edtSpeedValue.setText("");
        edtDistanceValue.setText("");
        edtTimeValue_hour.setText("");
        edtTimeValue_min.setText("");
        edtTimeValue_sec.setText("");
    }
}
