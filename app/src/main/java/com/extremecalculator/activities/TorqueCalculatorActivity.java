package com.extremecalculator.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.extremecalculator.R;
import com.extremecalculator.apputils.StringUtils;

import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TorqueCalculatorActivity extends BaseActivity {

    @Bind(R.id.ib_screenBack)
    ImageButton ibScreenBack;
    @Bind(R.id.tv_resultTitle)
    TextView tvResultTitle;
    @Bind(R.id.tv_torqueResult)
    TextView tvTorqueResult;
    @Bind(R.id.rb_torque)
    RadioButton rbTorque;
    @Bind(R.id.rb_force)
    RadioButton rbForce;
    @Bind(R.id.rb_distance)
    RadioButton rbDistance;
    @Bind(R.id.rg_torqueSelection)
    RadioGroup rgTorqueSelection;
    @Bind(R.id.edt_torqueValue)
    EditText edtTorqueValue;
    @Bind(R.id.tv_torqueScale)
    TextView tvTorqueScale;
    @Bind(R.id.ll_torqueView)
    LinearLayout llTorqueView;
    @Bind(R.id.edt_forceValue)
    EditText edtForceValue;
    @Bind(R.id.tv_forceScale)
    TextView tvForceScale;
    @Bind(R.id.ll_forceView)
    LinearLayout llForceView;
    @Bind(R.id.edt_distanceValue)
    EditText edtDistanceValue;
    @Bind(R.id.tv_distanceScale)
    TextView tvDistanceScale;
    @Bind(R.id.ll_distanceView)
    LinearLayout llDistanceView;
    @Bind(R.id.tv_calculate)
    TextView tvCalculate;
    @Bind(R.id.tv_clear)
    TextView tvClear;

    private String[] torqueScaleTypes;
    private String[] forceScaleTypes;
    private String[] distanceScaleTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torque_calculator);
        ButterKnife.bind(this);

        init();
    }

    private void init() {

        //  Get Scale Types
        torqueScaleTypes = getResources().getStringArray(R.array.torqueScaleTypes);
        forceScaleTypes = getResources().getStringArray(R.array.forceScaleTypes);
        distanceScaleTypes = getResources().getStringArray(R.array.distanceScaleTypes);

        // Set Default Scale Types
        tvTorqueScale.setText(torqueScaleTypes[0]);
        tvForceScale.setText(forceScaleTypes[0]);
        tvDistanceScale.setText(distanceScaleTypes[0]);

        // Set Torque as Default
        rbTorque.setChecked(true);

        llTorqueView.setVisibility(View.GONE);

        rgTorqueSelection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_torque:

                        // Clear the Result
                        tvTorqueResult.setText("0000");
                        edtTorqueValue.setText("");

                        llTorqueView.setVisibility(View.GONE);
                        llForceView.setVisibility(View.VISIBLE);
                        llDistanceView.setVisibility(View.VISIBLE);

                        break;
                    case R.id.rb_force:

                        // Clear the Result
                        tvTorqueResult.setText("0000");
                        edtForceValue.setText("");

                        llTorqueView.setVisibility(View.VISIBLE);
                        llForceView.setVisibility(View.GONE);
                        llDistanceView.setVisibility(View.VISIBLE);

                        break;
                    case R.id.rb_distance:

                        // Clear the Result
                        tvTorqueResult.setText("0000");
                        edtDistanceValue.setText("");

                        llTorqueView.setVisibility(View.VISIBLE);
                        llForceView.setVisibility(View.VISIBLE);
                        llDistanceView.setVisibility(View.GONE);

                        break;
                }
            }
        });
    }

    /**
     * Change Scale Module
     */
    @OnClick({R.id.tv_torqueScale})
    void clickSelectTorqueScale(View view) {
        if (!isClickEnable())
            return;

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
        builder.setItems(torqueScaleTypes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvTorqueScale.setText(torqueScaleTypes[which]);
            }
        });
        builder.setTitle("Select Scale");
        builder.show();

    }

    @OnClick({R.id.tv_forceScale})
    void clickSelectForceScale(View view) {
        if (!isClickEnable())
            return;

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
        builder.setItems(forceScaleTypes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvForceScale.setText(forceScaleTypes[which]);
            }
        });
        builder.setTitle("Select Scale");
        builder.show();

    }

    @OnClick({R.id.tv_distanceScale})
    void clickSelectDistanceScale(View view) {
        if (!isClickEnable())
            return;

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
        builder.setItems(distanceScaleTypes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvDistanceScale.setText(distanceScaleTypes[which]);
            }
        });
        builder.setTitle("Select Scale");
        builder.show();
    }

    /**
     * Clear the Value
     */
    @OnClick({R.id.tv_clear})
    void clickClear(View view) {
        if (!isClickEnable())
            return;

        tvTorqueResult.setText("0000");

        edtTorqueValue.setText("");
        edtForceValue.setText("");
        edtDistanceValue.setText("");
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

        if (rbTorque.isChecked())
            calculateTorque();
        else if (rbForce.isChecked())
            calculateForce();
        else if (rbDistance.isChecked())
            calculateDistance();
    }

    private boolean isDataValidate() {
        boolean isDataValidate = true;

        if (rbTorque.isChecked()) {
            if (TextUtils.isEmpty(edtForceValue.getText().toString().trim())) {
                isDataValidate = false;
                showErrorMessage("Please Enter Force Value");
            } else if (StringUtils.countMatches(edtForceValue.getText().toString().trim(), '.') > 1) {
                isDataValidate = false;
                showErrorMessage("Please Enter Valid Force Value");
            } else if (TextUtils.isEmpty(edtDistanceValue.getText().toString().trim())) {
                isDataValidate = false;
                showErrorMessage("Please Enter Distance Value");
            } else if (StringUtils.countMatches(edtDistanceValue.getText().toString().trim(), '.') > 1) {
                isDataValidate = false;
                showErrorMessage("Please Enter Valid Distance Value");
            }

        } else if (rbForce.isChecked()) {
            if (TextUtils.isEmpty(edtTorqueValue.getText().toString().trim())) {
                isDataValidate = false;
                showErrorMessage("Please Enter Torque Value");
            } else if (StringUtils.countMatches(edtTorqueValue.getText().toString().trim(), '.') > 1) {
                isDataValidate = false;
                showErrorMessage("Please Enter Valid Torque Value");
            } else if (TextUtils.isEmpty(edtDistanceValue.getText().toString().trim())) {
                isDataValidate = false;
                showErrorMessage("Please Enter Distance Value");
            } else if (StringUtils.countMatches(edtDistanceValue.getText().toString().trim(), '.') > 1) {
                isDataValidate = false;
                showErrorMessage("Please Enter Valid Distance Value");
            }

        } else if (rbDistance.isChecked()) {

            if (TextUtils.isEmpty(edtTorqueValue.getText().toString().trim())) {
                isDataValidate = false;
                showErrorMessage("Please Enter Torque Value");
            } else if (StringUtils.countMatches(edtTorqueValue.getText().toString().trim(), '.') > 1) {
                isDataValidate = false;
                showErrorMessage("Please Enter Valid Torque Value");
            } else if (TextUtils.isEmpty(edtForceValue.getText().toString().trim())) {
                isDataValidate = false;
                showErrorMessage("Please Enter Force Value");
            } else if (StringUtils.countMatches(edtForceValue.getText().toString().trim(), '.') > 1) {
                isDataValidate = false;
                showErrorMessage("Please Enter Valid Force Value");
            }
        }
        return isDataValidate;
    }

    private void calculateTorque() {
        try {
            double forceValue = convertForceValueInNewton(Double.parseDouble(edtForceValue.getText().toString().trim()),
                    tvForceScale.getText().toString().trim());

            double distanceValue = convertDistanceValueInMeter(Double.parseDouble(edtDistanceValue.getText().toString().trim()),
                    tvDistanceScale.getText().toString().trim());

            double resultValue = forceValue * distanceValue;
            setResult(resultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void calculateForce() {
        try {
            double torqueValue = convertTorqueValueInNewtonMeter(Double.parseDouble(edtTorqueValue.getText().toString().trim()),
                    tvTorqueScale.getText().toString().trim());

            double distanceValue = convertDistanceValueInMeter(Double.parseDouble(edtDistanceValue.getText().toString().trim()),
                    tvDistanceScale.getText().toString().trim());

            double resultValue = torqueValue / distanceValue;
            setResult(resultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calculateDistance() {
        try {
            double torqueValue = convertTorqueValueInNewtonMeter(Double.parseDouble(edtTorqueValue.getText().toString().trim()),
                    tvTorqueScale.getText().toString().trim());

            double forceValue = convertForceValueInNewton(Double.parseDouble(edtForceValue.getText().toString().trim()),
                    tvForceScale.getText().toString().trim());

            double resultValue = torqueValue / forceValue;
            setResult(resultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double convertForceValueInNewton(double value, String scaleType) {

        if (scaleType.compareToIgnoreCase("Dyne") == 0) {
            value = value / 100000;
        } else if (scaleType.compareToIgnoreCase("Gram Force") == 0) {
            value = value * 0.00980665;
        } else if (scaleType.compareToIgnoreCase("Kgs-Meter/sec^2") == 0) {

        } else if (scaleType.compareToIgnoreCase("Kip") == 0) {
            value = value * 4448.22161526051;
        } else if (scaleType.compareToIgnoreCase("Pound Force") == 0) {
            value = value * 4.44822161526051;
        } else if (scaleType.compareToIgnoreCase("Poundal") == 0) {
            value = value * 0.138254954376;
        }
        return value;
    }

    private double convertDistanceValueInMeter(double value, String scaleType) {
        if (scaleType.compareToIgnoreCase("Centimeter") == 0) {
            value = value / 100;
        } else if (scaleType.compareToIgnoreCase("Attometer") == 0) {
            value = (((value / 1000000) / 1000000) / 1000000);
        } else if (scaleType.compareToIgnoreCase("Dekameter") == 0) {
            value = value * 10;
        } else if (scaleType.compareToIgnoreCase("Decimeter") == 0) {
            value = value / 10;
        } else if (scaleType.compareToIgnoreCase("Exameter") == 0) {

        } else if (scaleType.compareToIgnoreCase("Femtometer") == 0) {

        } else if (scaleType.compareToIgnoreCase("Foot") == 0) {
            value = value * 0.3048;
        } else if (scaleType.compareToIgnoreCase("Hectometer") == 0) {
            value = value * 100;
        } else if (scaleType.compareToIgnoreCase("Inch") == 0) {
            value = value * 0.0254;
        } else if (scaleType.compareToIgnoreCase("Kilometer") == 0) {
            value = value * 1000;
        } else if (scaleType.compareToIgnoreCase("Megameter") == 0) {

        } else if (scaleType.compareToIgnoreCase("Micrometer") == 0) {
            value = value / 1000000;
        } else if (scaleType.compareToIgnoreCase("Mile") == 0) {
            value = value * 1609.344;
        } else if (scaleType.compareToIgnoreCase("Millimeter") == 0) {
            value = value * 0.001;
        } else if (scaleType.compareToIgnoreCase("Nanometer") == 0) {
            value = value / 1000000000;
        } else if (scaleType.compareToIgnoreCase("Petameter") == 0) {

        } else if (scaleType.compareToIgnoreCase("Picometer") == 0) {

        } else if (scaleType.compareToIgnoreCase("Yard") == 0) {
            value = value * 0.9144;
        }
        return value;
    }

    private double convertTorqueValueInNewtonMeter(double value, String scaleType) {
        if (scaleType.compareToIgnoreCase("Dyne-Centimeter") == 0) {
            value = value * 0.001;
        } else if (scaleType.compareToIgnoreCase("Gram-Centimeter") == 0) {
            value = value * 0.9806649999788;
        } else if (scaleType.compareToIgnoreCase("Kilogram-Centimeter") == 0) {
            value = value * 0.9806649999788;
        } else if (scaleType.compareToIgnoreCase("Kilogram-Meter") == 0) {
            value = value * 9.806649999788;
        } else if (scaleType.compareToIgnoreCase("Newton-Centimeter") == 0) {
            value = value * 0.01;
        } else if (scaleType.compareToIgnoreCase("Pound-Foot") == 0) {
            value = value * 1.35581794833;
        } else if (scaleType.compareToIgnoreCase("Pound-Inch") == 0) {
            value = value * 0.112984829333;
        }
        return value;
    }

    private void setResult(double result) {
        tvTorqueResult.setText(new DecimalFormat("#.##").format(result));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.ib_screenBack})
    void clickBack(View view) {
        if (!isClickEnable())
            return;

        onBackPressed();
    }
}
