package com.extremecalculator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.extremecalculator.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends BaseActivity {

    @Bind(R.id.tv_scientificCalculator)
    TextView tvScientificCalculator;
    @Bind(R.id.tv_volumeCalculator)
    TextView tvVolumeCalculator;
    @Bind(R.id.tv_torqueCalculator)
    TextView tvTorqueCalculator;
    @Bind(R.id.tv_areaCalculator)
    TextView tvAreaCalculator;
    @Bind(R.id.tv_speedDistanceTimeCalculator)
    TextView tvSpeedDistanceTimeCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
    }


    /** Scientific Calculator Module    */
    @OnClick({R.id.tv_scientificCalculator})
    void clickScientificCalculator(View view)
    {
        if(!isClickEnable())
            return;

        startScientificCalculatorActivity();
    }

    private void startScientificCalculatorActivity()
    {
        Intent intent = new Intent(mContext, ScientificCalculatorActivity.class);
        startActivity(intent);
    }

    /** Volume Calculator Module    */
    @OnClick({R.id.tv_volumeCalculator})
    void clickVolumeCalculator(View view)
    {
        if(!isClickEnable())
            return;

        startVolumeCalculatorActivity();
    }

    private void startVolumeCalculatorActivity()
    {
        Intent intent = new Intent(mContext, VolumeCalculatorActivity.class);
        startActivity(intent);
    }

    /** Torque Calculator Module    */
    @OnClick({R.id.tv_torqueCalculator})
    void clickTorqueCalculator(View view)
    {
        if(!isClickEnable())
            return;

        startTorqueCalculatorActivity();
    }

    private void startTorqueCalculatorActivity()
    {
        Intent intent = new Intent(mContext, TorqueCalculatorActivity.class);
        startActivity(intent);
    }

    /** Area Calculator Module    */
    @OnClick({R.id.tv_areaCalculator})
    void clickAreaCalculator(View view)
    {
        if(!isClickEnable())
            return;

        startAreaCalculatorActivity();
    }

    private void startAreaCalculatorActivity()
    {
        Intent intent = new Intent(mContext, AreaCalculatorActivity.class);
        startActivity(intent);
    }

    /** Speed Distance Time Calculator Module    */
    @OnClick({R.id.tv_speedDistanceTimeCalculator})
    void clickSpeedDistanceTimeCalculator(View view)
    {
        if(!isClickEnable())
            return;

        startSpeedDistanceAndTimeCalculatorActivity();
    }

    private void startSpeedDistanceAndTimeCalculatorActivity()
    {
        Intent intent = new Intent(mContext, SpeedDistanceAndTimeCalculatorActivity.class);
        startActivity(intent);
    }
}
