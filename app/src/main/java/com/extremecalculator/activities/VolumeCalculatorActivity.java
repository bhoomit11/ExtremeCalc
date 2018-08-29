package com.extremecalculator.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.extremecalculator.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VolumeCalculatorActivity extends BaseActivity {

    @Bind(R.id.ib_screenBack)
    ImageButton ibScreenBack;

    //Result
    @Bind(R.id.tv_resultTitle)
    TextView tvResultTitle;
    @Bind(R.id.tv_volumeResult)
    TextView tvVolumeResult;

    //Change Types
    @Bind(R.id.tv_volumeType)
    TextView tvVolumeType;
    @Bind(R.id.tv_caculationType)
    TextView tvCaculationType;
    @Bind(R.id.tv_conversionType)
    TextView tvConversionType;

    //Conversion
    @Bind(R.id.edit_Conversionvalue)
    EditText editConversionvalue;

    //Footer Buttons
    @Bind(R.id.tv_calculate)
    TextView tvCalculate;
    @Bind(R.id.tv_clear)
    TextView tvClear;

    //Volume Calc Types Layout
    @Bind(R.id.calculation_input)
    LinearLayout calculationInput;
    @Bind(R.id.conversion_input)
    LinearLayout conversionInput;

    //Cube Volume
    @Bind(R.id.ll_cubeVolume)
    TableLayout llCubeVolume;
    @Bind(R.id.tv_CubeunitType)
    TextView tvCubeunitType;
    @Bind(R.id.edit_Cubewidth)
    EditText editCubeWidth;
    @Bind(R.id.edit_Cubeheight)
    EditText editCubeHeight;
    @Bind(R.id.edit_Cubelength)
    EditText editCubeLength;

    //cone Volume
    @Bind(R.id.ll_ConeVolume)
    TableLayout llConeVolume;
    @Bind(R.id.tv_ConeunitType)
    TextView tvConeunitType;
    @Bind(R.id.edit_ConeRadius)
    EditText editConeRadius;
    @Bind(R.id.edit_Coneheight)
    EditText editConeheight;

    //cylinder Volume
    @Bind(R.id.tv_CylinderunitType)
    TextView tvCylinderunitType;
    @Bind(R.id.edit_CylinderRadius)
    EditText editCylinderRadius;
    @Bind(R.id.edit_Cylinderheight)
    EditText editCylinderheight;
    @Bind(R.id.ll_CylinderVolume)
    TableLayout llCylinderVolume;

    //Rectangle Room Volume
    @Bind(R.id.tv_RecRoomunitType)
    TextView tvRecRoomunitType;
    @Bind(R.id.edit_RecRoomwidth)
    EditText editRecRoomwidth;
    @Bind(R.id.edit_RecRoomlength)
    EditText editRecRoomlength;
    @Bind(R.id.edit_RecRoomheight)
    EditText editRecRoomheight;
    @Bind(R.id.ll_RecRoomVolume)
    TableLayout llRecRoomVolume;

    //Pyramid Volume
    @Bind(R.id.tv_PyramidunitType)
    TextView tvPyramidunitType;
    @Bind(R.id.edit_PyramidBase)
    EditText editPyramidBase;
    @Bind(R.id.edit_Pyramidheight)
    EditText editPyramidheight;
    @Bind(R.id.ll_PyramidVolume)
    TableLayout llPyramidVolume;

    //Sphere Volume
    @Bind(R.id.tv_SphereUnitType)
    TextView tvSphereUnitType;
    @Bind(R.id.edit_SphereRadius)
    EditText editSphereRadius;
    @Bind(R.id.ll_SphereVolume)
    TableLayout llSphereVolume;

    //Ellipsoid Volume
    @Bind(R.id.tv_EllipsoidUnitType)
    TextView tvEllipsoidUnitType;
    @Bind(R.id.edit_EllipsoidR1)
    EditText editEllipsoidR1;
    @Bind(R.id.edit_EllipsoidR2)
    EditText editEllipsoidR2;
    @Bind(R.id.edit_EllipsoidR3)
    EditText editEllipsoidR3;
    @Bind(R.id.ll_EllipsoidVolume)
    TableLayout llEllipsoidVolume;

    private String[] VolumeType;
    private String[] calculationType;
    private String[] unitType;
    private String[] conversionType;

    private final double pi = Math.PI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_calculator);
        ButterKnife.bind(this);
        init();
    }

    public void init() {
        //  Get Types
        VolumeType = getResources().getStringArray(R.array.VolumeType);
        calculationType = getResources().getStringArray(R.array.calculationType);
        unitType = getResources().getStringArray(R.array.unitType);
        conversionType = getResources().getStringArray(R.array.conversionType);

        // Set Default Types
        tvVolumeType.setText(VolumeType[0]);
        tvCaculationType.setText(calculationType[0]);
        tvCubeunitType.setText(unitType[0]);
        tvConeunitType.setText(unitType[0]);
        tvCylinderunitType.setText(unitType[0]);
        tvRecRoomunitType.setText(unitType[0]);
        tvPyramidunitType.setText(unitType[0]);
        tvSphereUnitType.setText(unitType[0]);
        tvEllipsoidUnitType.setText(unitType[0]);
        tvConversionType.setText(conversionType[0]);

        llCubeVolume.setVisibility(View.VISIBLE);
        llConeVolume.setVisibility(View.GONE);
        llCylinderVolume.setVisibility(View.GONE);
        llRecRoomVolume.setVisibility(View.GONE);
        llPyramidVolume.setVisibility(View.GONE);
        llSphereVolume.setVisibility(View.GONE);
        llEllipsoidVolume.setVisibility(View.GONE);
    }


    @OnClick({R.id.tv_volumeType})
    void clickSelectVolumeType(View view) {
        if (!isClickEnable())
            return;
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
        builder.setItems(VolumeType, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvVolumeType.setText(VolumeType[which]);
                tvResultTitle.setText("Result : ");
                tvVolumeResult.setText("0000");
                switch (which) {
                    case 0:
                        calculationInput.setVisibility(View.VISIBLE);
                        conversionInput.setVisibility(View.GONE);
                        break;
                    case 1:
                        calculationInput.setVisibility(View.GONE);
                        conversionInput.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        builder.setTitle("Select Volume Type");
        builder.show();
    }

    @OnClick({R.id.tv_caculationType})
    void clickSelectCaculationType(View view) {
        if (!isClickEnable())
            return;

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
        builder.setItems(calculationType, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvCaculationType.setText(calculationType[which]);
                clearAll();
                switch (which) {
                    case 0:
                        llCubeVolume.setVisibility(View.VISIBLE);
                        llConeVolume.setVisibility(View.GONE);
                        llCylinderVolume.setVisibility(View.GONE);
                        llRecRoomVolume.setVisibility(View.GONE);
                        llPyramidVolume.setVisibility(View.GONE);
                        llSphereVolume.setVisibility(View.GONE);
                        llEllipsoidVolume.setVisibility(View.GONE);
                        break;
                    case 1:
                        llCubeVolume.setVisibility(View.GONE);
                        llConeVolume.setVisibility(View.VISIBLE);
                        llCylinderVolume.setVisibility(View.GONE);
                        llRecRoomVolume.setVisibility(View.GONE);
                        llPyramidVolume.setVisibility(View.GONE);
                        llSphereVolume.setVisibility(View.GONE);
                        llEllipsoidVolume.setVisibility(View.GONE);
                        break;
                    case 2:
                        llCubeVolume.setVisibility(View.GONE);
                        llConeVolume.setVisibility(View.GONE);
                        llCylinderVolume.setVisibility(View.VISIBLE);
                        llRecRoomVolume.setVisibility(View.GONE);
                        llPyramidVolume.setVisibility(View.GONE);
                        llSphereVolume.setVisibility(View.GONE);
                        llEllipsoidVolume.setVisibility(View.GONE);
                        break;
                    case 3:
                        llCubeVolume.setVisibility(View.GONE);
                        llConeVolume.setVisibility(View.GONE);
                        llCylinderVolume.setVisibility(View.GONE);
                        llRecRoomVolume.setVisibility(View.VISIBLE);
                        llPyramidVolume.setVisibility(View.GONE);
                        llSphereVolume.setVisibility(View.GONE);
                        llEllipsoidVolume.setVisibility(View.GONE);
                        break;
                    case 4:
                        llCubeVolume.setVisibility(View.GONE);
                        llConeVolume.setVisibility(View.GONE);
                        llCylinderVolume.setVisibility(View.GONE);
                        llRecRoomVolume.setVisibility(View.GONE);
                        llPyramidVolume.setVisibility(View.VISIBLE);
                        llSphereVolume.setVisibility(View.GONE);
                        llEllipsoidVolume.setVisibility(View.GONE);
                        break;
                    case 5:
                        llCubeVolume.setVisibility(View.GONE);
                        llConeVolume.setVisibility(View.GONE);
                        llCylinderVolume.setVisibility(View.GONE);
                        llRecRoomVolume.setVisibility(View.GONE);
                        llPyramidVolume.setVisibility(View.GONE);
                        llSphereVolume.setVisibility(View.VISIBLE);
                        llEllipsoidVolume.setVisibility(View.GONE);
                        break;
                    case 6:
                        llCubeVolume.setVisibility(View.GONE);
                        llConeVolume.setVisibility(View.GONE);
                        llCylinderVolume.setVisibility(View.GONE);
                        llRecRoomVolume.setVisibility(View.GONE);
                        llPyramidVolume.setVisibility(View.GONE);
                        llSphereVolume.setVisibility(View.GONE);
                        llEllipsoidVolume.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        builder.setTitle("Select Calculation Type");
        builder.show();
    }

    @OnClick({R.id.tv_conversionType})
    void clickSelectConversionType(View view) {
        if (!isClickEnable())
            return;

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
        builder.setItems(conversionType, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvConversionType.setText(conversionType[which]);
                clearAll();
            }
        });
        builder.setTitle("Select Conversion Type");
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

        if (calculationInput.getVisibility() == View.VISIBLE) {
            calculateVolume();
        } else if (conversionInput.getVisibility() == View.VISIBLE) {
            conversionVolume();
        }
    }

    private boolean isDataValidate() {
        boolean isDataValidate = true;

        if (calculationInput.getVisibility() == View.VISIBLE) {
            if (llCubeVolume.getVisibility() == View.VISIBLE) {
                if (TextUtils.isEmpty(editCubeWidth.getText().toString().trim())) {
                    isDataValidate = false;
                    showErrorMessage("Please Enter Width Value");
                } else if (TextUtils.isEmpty(editCubeLength.getText().toString().trim())) {
                    isDataValidate = false;
                    showErrorMessage("Please Enter Length Value");
                } else if (TextUtils.isEmpty(editCubeHeight.getText().toString().trim())) {
                    isDataValidate = false;
                    showErrorMessage("Please Enter Height Value");
                }
            }

            if (llConeVolume.getVisibility() == View.VISIBLE) {
                if (TextUtils.isEmpty(editConeRadius.getText().toString().trim())) {
                    isDataValidate = false;
                    showErrorMessage("Please Enter Radius Value");
                } else if (TextUtils.isEmpty(editConeheight.getText().toString().trim())) {
                    isDataValidate = false;
                    showErrorMessage("Please Enter Height Value");
                }
            }

            if (llCylinderVolume.getVisibility() == View.VISIBLE) {
                if (TextUtils.isEmpty(editCylinderRadius.getText().toString().trim())) {
                    isDataValidate = false;
                    showErrorMessage("Please Enter Radius Value");
                } else if (TextUtils.isEmpty(editCylinderheight.getText().toString().trim())) {
                    isDataValidate = false;
                    showErrorMessage("Please Enter Height Value");
                }
            }

            if (llRecRoomVolume.getVisibility() == View.VISIBLE) {
                if (TextUtils.isEmpty(editRecRoomwidth.getText().toString().trim())) {
                    isDataValidate = false;
                    showErrorMessage("Please Enter Width Value");
                } else if (TextUtils.isEmpty(editRecRoomlength.getText().toString().trim())) {
                    isDataValidate = false;
                    showErrorMessage("Please Enter Length Value");
                } else if (TextUtils.isEmpty(editRecRoomheight.getText().toString().trim())) {
                    isDataValidate = false;
                    showErrorMessage("Please Enter Height Value");
                }
            }

            if (llPyramidVolume.getVisibility() == View.VISIBLE) {
                if (TextUtils.isEmpty(editPyramidBase.getText().toString().trim())) {
                    isDataValidate = false;
                    showErrorMessage("Please Enter Base Value");
                } else if (TextUtils.isEmpty(editPyramidheight.getText().toString().trim())) {
                    isDataValidate = false;
                    showErrorMessage("Please Enter Height Value");
                }
            }

            if (llSphereVolume.getVisibility() == View.VISIBLE) {
                if (TextUtils.isEmpty(editSphereRadius.getText().toString().trim())) {
                    isDataValidate = false;
                    showErrorMessage("Please Radius Width Value");
                }
            }

            if (llEllipsoidVolume.getVisibility() == View.VISIBLE) {
                if (TextUtils.isEmpty(editEllipsoidR1.getText().toString().trim())) {
                    isDataValidate = false;
                    showErrorMessage("Please Enter R1 Value");
                } else if (TextUtils.isEmpty(editEllipsoidR2.getText().toString().trim())) {
                    isDataValidate = false;
                    showErrorMessage("Please Enter R2 Value");
                } else if (TextUtils.isEmpty(editEllipsoidR3.getText().toString().trim())) {
                    isDataValidate = false;
                    showErrorMessage("Please Enter R3 Value");
                }
            }
        } else if (conversionInput.getVisibility() == View.VISIBLE) {
            if (TextUtils.isEmpty(editConversionvalue.getText().toString().trim())) {
                isDataValidate = false;
                showErrorMessage("Please Enter Value");
            }
        }

        return isDataValidate;
    }

    /*
    **Calculate Volume
     */
    void calculateVolume() {
        tvResultTitle.setText("Result : ");
        if (calculationInput.getVisibility() == View.VISIBLE) {
            if (llCubeVolume.getVisibility() == View.VISIBLE) {
                try {
                    double l, w, h, vol;
                    l = Double.parseDouble(editCubeLength.getText().toString());
                    w = Double.parseDouble(editCubeWidth.getText().toString());
                    h = Double.parseDouble(editCubeHeight.getText().toString());
                    vol = l * w * h;
                    NumberFormat nf = new DecimalFormat("##.##");
                    tvVolumeResult.setText(nf.format(vol));
                    tvResultTitle.setText(tvResultTitle.getText().toString() + "Volume of Cube in "+tvCubeunitType.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (llConeVolume.getVisibility() == View.VISIBLE) {
                try {
                    double radius, height, vol;
                    radius = Double.parseDouble(editConeRadius.getText().toString());
                    height = Double.parseDouble(editConeheight.getText().toString());
                    vol = (pi * radius * radius) * (height / 3);
                    NumberFormat nf = new DecimalFormat("##.##");
                    tvVolumeResult.setText(nf.format(vol));
                    tvResultTitle.setText(tvResultTitle.getText().toString() + "Volume of Cone in "+tvConeunitType.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (llCylinderVolume.getVisibility() == View.VISIBLE) {
                try {
                    double radius, height, vol;
                    radius = Double.parseDouble(editCylinderRadius.getText().toString());
                    height = Double.parseDouble(editCylinderheight.getText().toString());
                    vol = (pi * radius * radius) * (height);
                    NumberFormat nf = new DecimalFormat("##.##");
                    tvVolumeResult.setText(nf.format(vol));
                    tvResultTitle.setText(tvResultTitle.getText().toString() + "Volume of Cylinder in "+tvCylinderunitType.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (llRecRoomVolume.getVisibility() == View.VISIBLE) {
                try {
                    Double l, w, h, vol;
                    l = Double.parseDouble(editRecRoomlength.getText().toString());
                    w = Double.parseDouble(editRecRoomwidth.getText().toString());
                    h = Double.parseDouble(editRecRoomheight.getText().toString());
                    vol = l * w * h;
                    NumberFormat nf = new DecimalFormat("##.##");
                    tvVolumeResult.setText(nf.format(vol));
                    tvResultTitle.setText(tvResultTitle.getText().toString() + "Volume of Squre or Rectangle Room in "+tvRecRoomunitType.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (llPyramidVolume.getVisibility() == View.VISIBLE) {
                try {
                    double base, height, vol;
                    base = Double.parseDouble(editPyramidBase.getText().toString());
                    height = Double.parseDouble(editPyramidheight.getText().toString());
                    vol = ((double) 1 / 3) * base * height;
                    NumberFormat nf = new DecimalFormat("##.##");
                    tvVolumeResult.setText(nf.format(vol));
                    tvResultTitle.setText(tvResultTitle.getText().toString() + "Volume of Pyramid in "+tvPyramidunitType.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (llSphereVolume.getVisibility() == View.VISIBLE) {
                try {
                    double radius, vol;
                    radius = Double.parseDouble(editSphereRadius.getText().toString());
                    vol = ((double) 4 / 3) * (pi * radius * radius * radius);
                    NumberFormat nf = new DecimalFormat("##.##");
                    tvVolumeResult.setText(nf.format(vol));
                    tvResultTitle.setText(tvResultTitle.getText().toString() + "Volume of Sphere in "+tvSphereUnitType.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (llEllipsoidVolume.getVisibility() == View.VISIBLE) {
                try {
                    double r1, r2, r3, vol;
                    r1 = Double.parseDouble(editEllipsoidR1.getText().toString());
                    r2 = Double.parseDouble(editEllipsoidR2.getText().toString());
                    r3 = Double.parseDouble(editEllipsoidR3.getText().toString());
                    vol = ((double) 4 / 3) * (pi * r1 * r2 * r3);
                    NumberFormat nf = new DecimalFormat("##.##");
                    tvVolumeResult.setText(nf.format(vol));
                    tvResultTitle.setText(tvResultTitle.getText().toString() + "Volume of Ellipsoid in "+tvEllipsoidUnitType.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /*
    Volume Conversion
     */
    void conversionVolume() {
        if(conversionInput.getVisibility()==View.VISIBLE){
            if(tvConversionType.getText().toString().equals(conversionType[0])){
                double value,result;
                value=Double.parseDouble(editConversionvalue.getText().toString());
                result=value*(0.000578704);
                NumberFormat nf = new DecimalFormat("##.####");
                tvVolumeResult.setText(nf.format(result));
            }else if(tvConversionType.getText().toString().equals(conversionType[1])){
                double value,result;
                value=Double.parseDouble(editConversionvalue.getText().toString());
                result=value*1728;
                NumberFormat nf = new DecimalFormat("##.####");
                tvVolumeResult.setText(nf.format(result));
            }else if(tvConversionType.getText().toString().equals(conversionType[2])){
                double value,result;
                value=Double.parseDouble(editConversionvalue.getText().toString());
                result=value*(0.01732);
                NumberFormat nf = new DecimalFormat("##.####");
                tvVolumeResult.setText(nf.format(result));
            }else if(tvConversionType.getText().toString().equals(conversionType[3])){
                double value,result;
                value=Double.parseDouble(editConversionvalue.getText().toString());
                result=value*(57.75);
                NumberFormat nf = new DecimalFormat("##.####");
                tvVolumeResult.setText(nf.format(result));
            }else if(tvConversionType.getText().toString().equals(conversionType[4])){
                double value,result;
                value=Double.parseDouble(editConversionvalue.getText().toString());
                result=value*(0.03704);
                NumberFormat nf = new DecimalFormat("##.####");
                tvVolumeResult.setText(nf.format(result));
            }
            else if(tvConversionType.getText().toString().equals(conversionType[5])){
                double value,result;
                value=Double.parseDouble(editConversionvalue.getText().toString());
                result=value*(27);
                NumberFormat nf = new DecimalFormat("##.####");
                tvVolumeResult.setText(nf.format(result));
            }
            else if(tvConversionType.getText().toString().equals(conversionType[6])){
                double value,result;
                value=Double.parseDouble(editConversionvalue.getText().toString());
                result=value*(264.2);
                NumberFormat nf = new DecimalFormat("##.####");
                tvVolumeResult.setText(nf.format(result));
            }
            else if(tvConversionType.getText().toString().equals(conversionType[7])){
                double value,result;
                value=Double.parseDouble(editConversionvalue.getText().toString());
                result=value*(0.003785);
                NumberFormat nf = new DecimalFormat("##.####");
                tvVolumeResult.setText(nf.format(result));
            }
            else if(tvConversionType.getText().toString().equals(conversionType[8])){
                double value,result;
                value=Double.parseDouble(editConversionvalue.getText().toString());
                result=value*(0.1337);
                NumberFormat nf = new DecimalFormat("##.####");
                tvVolumeResult.setText(nf.format(result));
            }
            else if(tvConversionType.getText().toString().equals(conversionType[9])){
                double value,result;
                value=Double.parseDouble(editConversionvalue.getText().toString());
                result=value*(7.48052);
                NumberFormat nf = new DecimalFormat("##.####");
                tvVolumeResult.setText(nf.format(result));
            }
            else if(tvConversionType.getText().toString().equals(conversionType[10])){
                double value,result;
                value=Double.parseDouble(editConversionvalue.getText().toString());
                result=value*(0.035314);
                NumberFormat nf = new DecimalFormat("##.####");
                tvVolumeResult.setText(nf.format(result));
            }
            else if(tvConversionType.getText().toString().equals(conversionType[11])){
                double value,result;
                value=Double.parseDouble(editConversionvalue.getText().toString());
                result=value*(28.32);
                NumberFormat nf = new DecimalFormat("##.####");
                tvVolumeResult.setText(nf.format(result));
            }
            else if(tvConversionType.getText().toString().equals(conversionType[12])){
                double value,result;
                value=Double.parseDouble(editConversionvalue.getText().toString());
                result=value*(0.01639);
                NumberFormat nf = new DecimalFormat("##.####");
                tvVolumeResult.setText(nf.format(result));
            }
            else if(tvConversionType.getText().toString().equals(conversionType[13])){
                double value,result;
                value=Double.parseDouble(editConversionvalue.getText().toString());
                result=value*(61.02);
                NumberFormat nf = new DecimalFormat("##.####");
                tvVolumeResult.setText(nf.format(result));
            }
            else if(tvConversionType.getText().toString().equals(conversionType[14])){
                double value,result;
                value=Double.parseDouble(editConversionvalue.getText().toString());
                result=value*(0.001);
                NumberFormat nf = new DecimalFormat("##.####");
                tvVolumeResult.setText(nf.format(result));
            }
            else if(tvConversionType.getText().toString().equals(conversionType[15])){
                double value,result;
                value=Double.parseDouble(editConversionvalue.getText().toString());
                result=value*(1000);
                NumberFormat nf = new DecimalFormat("##.####");
                tvVolumeResult.setText(nf.format(result));
            }
            else if(tvConversionType.getText().toString().equals(conversionType[16])){
                double value,result;
                value=Double.parseDouble(editConversionvalue.getText().toString());
                result=value*(3.785);
                NumberFormat nf = new DecimalFormat("##.####");
                tvVolumeResult.setText(nf.format(result));
            }
            else if(tvConversionType.getText().toString().equals(conversionType[17])){
                double value,result;
                value=Double.parseDouble(editConversionvalue.getText().toString());
                result=value*(0.2642);
                NumberFormat nf = new DecimalFormat("##.####");
                tvVolumeResult.setText(nf.format(result));
            }
            else if(tvConversionType.getText().toString().equals(conversionType[18])){
                double value,result;
                value=Double.parseDouble(editConversionvalue.getText().toString());
                result=value*(0.9463);
                NumberFormat nf = new DecimalFormat("##.####");
                tvVolumeResult.setText(nf.format(result));
            }
            else if(tvConversionType.getText().toString().equals(conversionType[19])){
                double value,result;
                value=Double.parseDouble(editConversionvalue.getText().toString());
                result=value*(1.057);
                NumberFormat nf = new DecimalFormat("##.####");
                tvVolumeResult.setText(nf.format(result));
            }
            else if(tvConversionType.getText().toString().equals(conversionType[20])){
                double value,result;
                value=Double.parseDouble(editConversionvalue.getText().toString());
                result=value*(0.0584);
                NumberFormat nf = new DecimalFormat("##.####");
                tvVolumeResult.setText(nf.format(result));
            }
            else if(tvConversionType.getText().toString().equals(conversionType[21])){
                double value,result;
                value=Double.parseDouble(editConversionvalue.getText().toString());
                result=value*(17.118);
                NumberFormat nf = new DecimalFormat("##.####");
                tvVolumeResult.setText(nf.format(result));
            }
        }
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

    @OnClick({R.id.tv_CubeunitType, R.id.tv_ConeunitType, R.id.tv_CylinderunitType, R.id.tv_RecRoomunitType, R.id.tv_PyramidunitType, R.id.tv_SphereUnitType, R.id.tv_EllipsoidUnitType})
    public void onClick(View view) {
        AlertDialog.Builder builder;
        switch (view.getId()) {
            case R.id.tv_CubeunitType:
                builder = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
                builder.setItems(unitType, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvCubeunitType.setText(unitType[which]);
                    }
                });
                builder.setTitle("Select Unit Type");
                builder.show();
                break;
            case R.id.tv_ConeunitType:
                builder = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
                builder.setItems(unitType, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvConeunitType.setText(unitType[which]);
                    }
                });
                builder.setTitle("Select Unit Type");
                builder.show();
                break;
            case R.id.tv_CylinderunitType:
                builder = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
                builder.setItems(unitType, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvCylinderunitType.setText(unitType[which]);
                    }
                });
                builder.setTitle("Select Unit Type");
                builder.show();
                break;
            case R.id.tv_RecRoomunitType:
                builder = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
                builder.setItems(unitType, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvRecRoomunitType.setText(unitType[which]);
                    }
                });
                builder.setTitle("Select Unit Type");
                builder.show();
                break;
            case R.id.tv_PyramidunitType:
                builder = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
                builder.setItems(unitType, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvPyramidunitType.setText(unitType[which]);
                    }
                });
                builder.setTitle("Select Unit Type");
                builder.show();
                break;
            case R.id.tv_SphereUnitType:
                builder = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
                builder.setItems(unitType, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvSphereUnitType.setText(unitType[which]);
                    }
                });
                builder.setTitle("Select Unit Type");
                builder.show();
                break;
            case R.id.tv_EllipsoidUnitType:
                builder = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
                builder.setItems(unitType, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvEllipsoidUnitType.setText(unitType[which]);
                    }
                });
                builder.setTitle("Select Unit Type");
                builder.show();
                break;
        }
    }

    void clearAll(){
        tvResultTitle.setText("Result : ");
        tvVolumeResult.setText("0000");
        editCubeHeight.setText("");
        editCubeLength.setText("");
        editCubeWidth.setText("");
        editConeRadius.setText("");
        editConeheight.setText("");
        editCylinderheight.setText("");
        editCylinderRadius.setText("");
        editRecRoomheight.setText("");
        editRecRoomwidth.setText("");
        editRecRoomlength.setText("");
        editPyramidheight.setText("");
        editPyramidBase.setText("");
        editSphereRadius.setText("");
        editEllipsoidR1.setText("");
        editEllipsoidR2.setText("");
        editEllipsoidR3.setText("");
        editConversionvalue.setText("");
    }
}
