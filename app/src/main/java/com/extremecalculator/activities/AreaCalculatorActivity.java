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
import com.extremecalculator.apputils.StringUtils;

import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AreaCalculatorActivity extends BaseActivity {

    @Bind(R.id.ib_screenBack)
    ImageButton ibScreenBack;
    @Bind(R.id.tv_resultTitle)
    TextView tvResultTitle;
    @Bind(R.id.tv_areaCalculatorResult)
    TextView tvAreaCalculatorResult;
    @Bind(R.id.tv_areaCalculatorType)
    TextView tvAreaCalculatorType;
    @Bind(R.id.tv_areaCalculatorScale)
    TextView tvAreaCalculatorScale;
    @Bind(R.id.edt_rectangleLengthValue)
    EditText edtRectangleLengthValue;
    @Bind(R.id.tv_rectangleLengthScale)
    TextView tvRectangleLengthScale;
    @Bind(R.id.edt_rectangleEdgeValue)
    EditText edtRectangleEdgeValue;
    @Bind(R.id.tv_rectangleEdgeScale)
    TextView tvRectangleEdgeScale;
    @Bind(R.id.ll_calculateRectangleView)
    LinearLayout llCalculateRectangleView;
    @Bind(R.id.edt_triangleEdgeOneValue)
    EditText edtTriangleEdgeOneValue;
    @Bind(R.id.tv_triangleEdgeOneScale)
    TextView tvTriangleEdgeOneScale;
    @Bind(R.id.edt_triangleEdgeTwoValue)
    EditText edtTriangleEdgeTwoValue;
    @Bind(R.id.tv_triangleEdgeTwoScale)
    TextView tvTriangleEdgeTwoScale;
    @Bind(R.id.edt_triangleEdgeThreeValue)
    EditText edtTriangleEdgeThreeValue;
    @Bind(R.id.tv_triangleEdgeThreeScale)
    TextView tvTriangleEdgeThreeScale;
    @Bind(R.id.ll_calculateTriangleView)
    LinearLayout llCalculateTriangleView;
    @Bind(R.id.edt_trapezoidTopEdgeValue)
    EditText edtTrapezoidTopEdgeValue;
    @Bind(R.id.tv_trapezoidTopEdgeScale)
    TextView tvTrapezoidTopEdgeScale;
    @Bind(R.id.edt_trapezoidBottomEdgeValue)
    EditText edtTrapezoidBottomEdgeValue;
    @Bind(R.id.tv_trapezoidBottomEdgeScale)
    TextView tvTrapezoidBottomEdgeScale;
    @Bind(R.id.edt_trapezoidHeightEdgeValue)
    EditText edtTrapezoidHeightEdgeValue;
    @Bind(R.id.tv_trapezoidHeightEdgeScale)
    TextView tvTrapezoidHeightEdgeScale;
    @Bind(R.id.ll_calculateTrapezoidView)
    LinearLayout llCalculateTrapezoidView;
    @Bind(R.id.edt_circleRadiusValue)
    EditText edtCircleRadiusValue;
    @Bind(R.id.tv_circleRadiusScale)
    TextView tvCircleRadiusScale;
    @Bind(R.id.ll_calculateCircleView)
    LinearLayout llCalculateCircleView;
    @Bind(R.id.edt_ellipseMajorAxesValue)
    EditText edtEllipseMajorAxesValue;
    @Bind(R.id.tv_ellipseMajorAxesScale)
    TextView tvEllipseMajorAxesScale;
    @Bind(R.id.edt_ellipseMinorAxesValue)
    EditText edtEllipseMinorAxesValue;
    @Bind(R.id.tv_ellipseMinorAxesScale)
    TextView tvEllipseMinorAxesScale;
    @Bind(R.id.ll_calculateEllipseView)
    LinearLayout llCalculateEllipseView;
    @Bind(R.id.edt_parallelogramBaseValue)
    EditText edtParallelogramBaseValue;
    @Bind(R.id.tv_parallelogramBaseScale)
    TextView tvParallelogramBaseScale;
    @Bind(R.id.edt_parallelogramHeightValue)
    EditText edtParallelogramHeightValue;
    @Bind(R.id.tv_parallelogramHeightScale)
    TextView tvParallelogramHeightScale;
    @Bind(R.id.ll_calculateParallelogramView)
    LinearLayout llCalculateParallelogramView;
    @Bind(R.id.tv_calculate)
    TextView tvCalculate;
    @Bind(R.id.tv_clear)
    TextView tvClear;

    private String[] areaCalculatorShapes;
    private String[] areaCalculationScales;

    private TextView currentSelectedScaleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_calculator);
        ButterKnife.bind(this);

        init();
    }

    private void init() {

        areaCalculatorShapes = getResources().getStringArray(R.array.areaTypes);
        tvAreaCalculatorType.setText(areaCalculatorShapes[0]);

        //  Get Scale Types
        areaCalculationScales = getResources().getStringArray(R.array.areaCalculatorScale);

        // Set Default Scale Types
        tvAreaCalculatorScale.setText(areaCalculationScales[0]);

        tvRectangleLengthScale.setText(areaCalculationScales[0]);
        tvRectangleEdgeScale.setText(areaCalculationScales[0]);

        tvTriangleEdgeOneScale.setText(areaCalculationScales[0]);
        tvTriangleEdgeTwoScale.setText(areaCalculationScales[0]);
        tvTriangleEdgeThreeScale.setText(areaCalculationScales[0]);

        tvTrapezoidTopEdgeScale.setText(areaCalculationScales[0]);
        tvTrapezoidBottomEdgeScale.setText(areaCalculationScales[0]);
        tvTrapezoidHeightEdgeScale.setText(areaCalculationScales[0]);

        tvCircleRadiusScale.setText(areaCalculationScales[0]);

        tvEllipseMajorAxesScale.setText(areaCalculationScales[0]);
        tvEllipseMinorAxesScale.setText(areaCalculationScales[0]);

        tvParallelogramBaseScale.setText(areaCalculationScales[0]);
        tvParallelogramHeightScale.setText(areaCalculationScales[0]);


    }

    @OnClick({R.id.tv_areaCalculatorType})
    void clickAreaCalculatorType(View view) {
        if (!isClickEnable())
            return;

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
        builder.setItems(areaCalculatorShapes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvAreaCalculatorType.setText(areaCalculatorShapes[which]);

                llCalculateRectangleView.setVisibility(View.GONE);
                llCalculateTriangleView.setVisibility(View.GONE);
                llCalculateTrapezoidView.setVisibility(View.GONE);
                llCalculateCircleView.setVisibility(View.GONE);
                llCalculateEllipseView.setVisibility(View.GONE);
                llCalculateParallelogramView.setVisibility(View.GONE);


                if (areaCalculatorShapes[which].compareToIgnoreCase("Rectangle") == 0) {
                    llCalculateRectangleView.setVisibility(View.VISIBLE);

                    edtRectangleEdgeValue.setText("");
                    edtRectangleLengthValue.setText("");
                } else if (areaCalculatorShapes[which].compareToIgnoreCase("Triangle") == 0) {
                    llCalculateTriangleView.setVisibility(View.VISIBLE);

                    edtTriangleEdgeOneValue.setText("");
                    edtTriangleEdgeTwoValue.setText("");
                    edtTriangleEdgeThreeValue.setText("");
                } else if (areaCalculatorShapes[which].compareToIgnoreCase("Trapezoid") == 0) {
                    llCalculateTrapezoidView.setVisibility(View.VISIBLE);

                    edtTrapezoidTopEdgeValue.setText("");
                    edtTrapezoidBottomEdgeValue.setText("");
                    edtTrapezoidHeightEdgeValue.setText("");
                } else if (areaCalculatorShapes[which].compareToIgnoreCase("Circle") == 0) {
                    llCalculateCircleView.setVisibility(View.VISIBLE);

                    edtCircleRadiusValue.setText("");
                } else if (areaCalculatorShapes[which].compareToIgnoreCase("Ellipse") == 0) {
                    llCalculateEllipseView.setVisibility(View.VISIBLE);

                    edtEllipseMajorAxesValue.setText("");
                    edtEllipseMinorAxesValue.setText("");
                } else if (areaCalculatorShapes[which].compareToIgnoreCase("Parallelogram") == 0) {
                    llCalculateParallelogramView.setVisibility(View.VISIBLE);

                    edtParallelogramBaseValue.setText("");
                    edtParallelogramHeightValue.setText("");
                }
            }
        });
        builder.setTitle("Select Scale");
        builder.show();

    }

    @OnClick({R.id.tv_areaCalculatorScale, R.id.tv_rectangleLengthScale, R.id.tv_rectangleEdgeScale,
            R.id.tv_triangleEdgeOneScale, R.id.tv_triangleEdgeTwoScale, R.id.tv_triangleEdgeThreeScale,
            R.id.tv_trapezoidTopEdgeScale, R.id.tv_trapezoidBottomEdgeScale, R.id.tv_trapezoidHeightEdgeScale,
            R.id.tv_circleRadiusScale, R.id.tv_ellipseMajorAxesScale, R.id.tv_ellipseMinorAxesScale,
            R.id.tv_parallelogramBaseScale, R.id.tv_parallelogramHeightScale})
    void clickScale(View view) {
        if (!isClickEnable())
            return;

        currentSelectedScaleView = (TextView) view;

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
        builder.setItems(areaCalculationScales, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (currentSelectedScaleView != null)
                    currentSelectedScaleView.setText(areaCalculationScales[which]);
            }
        });
        builder.setTitle("Select Scale");
        builder.show();
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

    @OnClick({R.id.tv_clear})
    void clickClear(View view) {
        if (!isClickEnable())
            return;

        edtRectangleEdgeValue.setText("");
        edtRectangleLengthValue.setText("");

        edtTriangleEdgeOneValue.setText("");
        edtTriangleEdgeTwoValue.setText("");
        edtTriangleEdgeThreeValue.setText("");

        edtTrapezoidTopEdgeValue.setText("");
        edtTrapezoidBottomEdgeValue.setText("");
        edtTrapezoidHeightEdgeValue.setText("");

        edtCircleRadiusValue.setText("");

        edtEllipseMajorAxesValue.setText("");
        edtEllipseMinorAxesValue.setText("");

        edtParallelogramBaseValue.setText("");
        edtParallelogramHeightValue.setText("");

        tvAreaCalculatorResult.setText("");
    }

    @OnClick({R.id.tv_calculate})
    void clickCalculate(View view) {
        if (!isClickEnable())
            return;

        String areaCalculatorType = tvAreaCalculatorType.getText().toString().trim();

        if (areaCalculatorType.compareToIgnoreCase("Rectangle") == 0) {
            calculateRectangleArea();
        } else if (areaCalculatorType.compareToIgnoreCase("Triangle") == 0) {
            calculateTriangleArea();
        } else if (areaCalculatorType.compareToIgnoreCase("Trapezoid") == 0) {
            calculateTrapezoidArea();
        } else if (areaCalculatorType.compareToIgnoreCase("Circle") == 0) {
            calculateCircleArea();
        } else if (areaCalculatorType.compareToIgnoreCase("Ellipse") == 0) {
            calculateEllipseArea();
        } else if (areaCalculatorType.compareToIgnoreCase("Parallelogram") == 0) {
            calculateParallelogramArea();
        }
    }

    /**
     * Calculate Rectangle
     */
    private void calculateRectangleArea() {
        try {
            if (TextUtils.isEmpty(edtRectangleEdgeValue.getText().toString().trim())) {
                showErrorMessage("Please Enter Edge Value");
                return;
            }

            if (StringUtils.countMatches(edtRectangleEdgeValue.getText().toString().trim(), '.') > 1) {
                showErrorMessage("Please Enter Valid Edge Value");
                return;
            }

            if (TextUtils.isEmpty(edtRectangleLengthValue.getText().toString().trim())) {
                showErrorMessage("Please Enter Length Value");
                return;
            }

            if (StringUtils.countMatches(edtRectangleLengthValue.getText().toString().trim(), '.') > 1) {
                showErrorMessage("Please Enter Valid Length Value");
                return;
            }

            double edgeValue = convertToCentimeter(Double.parseDouble(edtRectangleEdgeValue.getText().toString().trim()),
                    tvRectangleEdgeScale.getText().toString().trim());
            double lengthValue = convertToCentimeter(Double.parseDouble(edtRectangleLengthValue.getText().toString().trim()),
                    tvRectangleLengthScale.getText().toString().trim());

            Log.e("Log", "Edge Value ---> " + edgeValue);
            Log.e("Log", "Length Value ---> " + lengthValue);
            Log.e("Log", "Scale Type ---> " + tvAreaCalculatorScale.getText().toString().trim());

            double rectangleArea = convertCentimeterToScale((edgeValue * lengthValue), tvAreaCalculatorScale.getText().toString().trim());
            rectangleArea = convertCentimeterToScale(rectangleArea, tvAreaCalculatorScale.getText().toString().trim());
            setResult(rectangleArea);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculate Triangle
     */
    private void calculateTriangleArea() {
        try {
            if (TextUtils.isEmpty(edtTriangleEdgeOneValue.getText().toString().trim())) {
                showErrorMessage("Please Enter First Edge Value");
                return;
            }

            if (StringUtils.countMatches(edtTriangleEdgeOneValue.getText().toString().trim(), '.') > 1) {
                showErrorMessage("Please Enter Valid First Edge Value");
                return;
            }

            if (TextUtils.isEmpty(edtTriangleEdgeTwoValue.getText().toString().trim())) {
                showErrorMessage("Please Enter Second Edge Value");
                return;
            }

            if (StringUtils.countMatches(edtTriangleEdgeTwoValue.getText().toString().trim(), '.') > 1) {
                showErrorMessage("Please Enter Valid Second Edge Value");
                return;
            }

            if (TextUtils.isEmpty(edtTriangleEdgeThreeValue.getText().toString().trim())) {
                showErrorMessage("Please Enter Third Edge Value");
                return;
            }

            if (StringUtils.countMatches(edtTriangleEdgeThreeValue.getText().toString().trim(), '.') > 1) {
                showErrorMessage("Please Enter Valid Third Edge Value");
                return;
            }

            double firstEdgeValue = convertToCentimeter(Double.parseDouble(edtTriangleEdgeOneValue.getText().toString().trim()),
                    tvTrapezoidTopEdgeScale.getText().toString().trim());
            double secondEdgeValue = convertToCentimeter(Double.parseDouble(edtTriangleEdgeTwoValue.getText().toString().trim()),
                    tvTrapezoidBottomEdgeScale.getText().toString().trim());
            double thirdEdgeValue = convertToCentimeter(Double.parseDouble(edtTriangleEdgeThreeValue.getText().toString().trim()),
                    tvTrapezoidHeightEdgeScale.getText().toString().trim());

            double perimeterValue = ((firstEdgeValue + secondEdgeValue + thirdEdgeValue) / 2);
            double ans = Math.sqrt(perimeterValue * (perimeterValue - firstEdgeValue) * (perimeterValue - secondEdgeValue) * (perimeterValue - thirdEdgeValue));
            double triangleArea = convertCentimeterToScale(ans, tvAreaCalculatorScale.getText().toString().trim());
            triangleArea = convertCentimeterToScale(triangleArea, tvAreaCalculatorScale.getText().toString().trim());
            triangleArea = convertCentimeterToScale(triangleArea, tvAreaCalculatorScale.getText().toString().trim());

            setResult(triangleArea);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculate Trapezoid
     */
    private void calculateTrapezoidArea() {
        try {
            if (TextUtils.isEmpty(edtTrapezoidTopEdgeValue.getText().toString().trim())) {
                showErrorMessage("Please Enter Top Edge Value");
                return;
            }

            if (StringUtils.countMatches(edtTrapezoidTopEdgeValue.getText().toString().trim(), '.') > 1) {
                showErrorMessage("Please Enter Valid Top Edge Value");
                return;
            }

            if (TextUtils.isEmpty(edtTrapezoidBottomEdgeValue.getText().toString().trim())) {
                showErrorMessage("Please Enter Length Value");
                return;
            }

            if (StringUtils.countMatches(edtTrapezoidBottomEdgeValue.getText().toString().trim(), '.') > 1) {
                showErrorMessage("Please Enter Valid Length Value");
                return;
            }

            if (TextUtils.isEmpty(edtTrapezoidHeightEdgeValue.getText().toString().trim())) {
                showErrorMessage("Please Enter Length Value");
                return;
            }

            if (StringUtils.countMatches(edtTrapezoidHeightEdgeValue.getText().toString().trim(), '.') > 1) {
                showErrorMessage("Please Enter Valid Length Value");
                return;
            }

            double topEdgeValue = convertToCentimeter(Double.parseDouble(edtTrapezoidTopEdgeValue.getText().toString().trim()),
                    tvTrapezoidTopEdgeScale.getText().toString().trim());
            double bottomEdgeValue = convertToCentimeter(Double.parseDouble(edtTrapezoidBottomEdgeValue.getText().toString().trim()),
                    tvTrapezoidBottomEdgeScale.getText().toString().trim());
            double heightEdgeValue = convertToCentimeter(Double.parseDouble(edtTrapezoidHeightEdgeValue.getText().toString().trim()),
                    tvTrapezoidHeightEdgeScale.getText().toString().trim());

            double trapezoidArea = convertCentimeterToScale((((topEdgeValue + bottomEdgeValue) / 2) * heightEdgeValue), tvAreaCalculatorScale.getText().toString().trim());
            trapezoidArea = convertCentimeterToScale(trapezoidArea, tvAreaCalculatorScale.getText().toString().trim());
            trapezoidArea = convertCentimeterToScale(trapezoidArea, tvAreaCalculatorScale.getText().toString().trim());

            setResult(trapezoidArea);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculate Circle
     */
    private void calculateCircleArea() {
        try {
            if (TextUtils.isEmpty(edtCircleRadiusValue.getText().toString().trim())) {
                showErrorMessage("Please Enter Radius Value");
                return;
            }

            if (StringUtils.countMatches(edtCircleRadiusValue.getText().toString().trim(), '.') > 1) {
                showErrorMessage("Please Enter Valid Radius Value");
                return;
            }

            double radiusValue = convertToCentimeter(Double.parseDouble(edtCircleRadiusValue.getText().toString().trim()),
                    tvCircleRadiusScale.getText().toString().trim());

            double circleArea = convertCentimeterToScale((3.142 * (radiusValue * radiusValue)), tvAreaCalculatorScale.getText().toString().trim());

            setResult(circleArea);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculate Ellipse
     */
    private void calculateEllipseArea() {
        try {
            if (TextUtils.isEmpty(edtEllipseMajorAxesValue.getText().toString().trim())) {
                showErrorMessage("Please Enter Major Axes Value");
                return;
            }

            if (StringUtils.countMatches(edtEllipseMajorAxesValue.getText().toString().trim(), '.') > 1) {
                showErrorMessage("Please Enter Valid Major Axes Value");
                return;
            }

            if (TextUtils.isEmpty(edtEllipseMinorAxesValue.getText().toString().trim())) {
                showErrorMessage("Please Enter Minor Axes Value");
                return;
            }

            if (StringUtils.countMatches(edtEllipseMinorAxesValue.getText().toString().trim(), '.') > 1) {
                showErrorMessage("Please Enter Valid Minor Axes Value");
                return;
            }

            double majorAxesValue = convertToCentimeter(Double.parseDouble(edtEllipseMajorAxesValue.getText().toString().trim()),
                    tvEllipseMajorAxesScale.getText().toString().trim());
            double minorAxesValue = convertToCentimeter(Double.parseDouble(edtEllipseMinorAxesValue.getText().toString().trim()),
                    tvEllipseMinorAxesScale.getText().toString().trim());

            double ellipseArea = convertCentimeterToScale((3.142 * majorAxesValue * minorAxesValue), tvAreaCalculatorScale.getText().toString().trim());
            ellipseArea = convertCentimeterToScale(ellipseArea, tvAreaCalculatorScale.getText().toString().trim());

            setResult(ellipseArea);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculate Parallelogram
     */
    private void calculateParallelogramArea() {
        try {
            if (TextUtils.isEmpty(edtParallelogramBaseValue.getText().toString().trim())) {
                showErrorMessage("Please Enter Base Value");
                return;
            }

            if (StringUtils.countMatches(edtParallelogramBaseValue.getText().toString().trim(), '.') > 1) {
                showErrorMessage("Please Enter Valid Base Value");
                return;
            }

            if (TextUtils.isEmpty(edtParallelogramHeightValue.getText().toString().trim())) {
                showErrorMessage("Please Enter Height Value");
                return;
            }

            if (StringUtils.countMatches(edtParallelogramHeightValue.getText().toString().trim(), '.') > 1) {
                showErrorMessage("Please Enter Valid Height Value");
                return;
            }

            double BaseValue = convertToCentimeter(Double.parseDouble(edtParallelogramBaseValue.getText().toString().trim()),
                    tvParallelogramBaseScale.getText().toString().trim());
            double HeightValue = convertToCentimeter(Double.parseDouble(edtParallelogramHeightValue.getText().toString().trim()),
                    tvParallelogramHeightScale.getText().toString().trim());

            double parallelogramArea = convertCentimeterToScale((BaseValue * HeightValue), tvAreaCalculatorScale.getText().toString().trim());
            parallelogramArea = convertCentimeterToScale(parallelogramArea, tvAreaCalculatorScale.getText().toString().trim());

            setResult(parallelogramArea);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setResult(double result) {
        tvAreaCalculatorResult.setText(new DecimalFormat("#.##").format(result));
    }

    private double convertToCentimeter(double value, String scaleType) {

        Log.e("Log", scaleType + " To Centimeter Value Before ---> " + value);
        if (scaleType.compareToIgnoreCase("Foot") == 0) {
            value = value * 30.48;
        } else if (scaleType.compareToIgnoreCase("Yard") == 0) {
            value = value / 0.010936;
        } else if (scaleType.compareToIgnoreCase("Inch") == 0) {
            value = value * 2.54;
        } else if (scaleType.compareToIgnoreCase("Mile") == 0) {
            value = value * 160934.4;
        } else if (scaleType.compareToIgnoreCase("Meter") == 0) {
            value = value * 100;
        } else if (scaleType.compareToIgnoreCase("Kilometer") == 0) {
            value = value * 100000;
        }
        Log.e("Log", scaleType + " To Centimeter Value After ---> " + value);
        return value;
    }

    private double convertCentimeterToScale(double value, String scaleType) {

        Log.e("Log", "Centimeter To " + scaleType + " Value Before ---> " + value);
        if (scaleType.compareToIgnoreCase("Foot") == 0) {
            value = value / 30.48;
        } else if (scaleType.compareToIgnoreCase("Yard") == 0) {
            value = value * 0.010936;
        } else if (scaleType.compareToIgnoreCase("Inch") == 0) {
            value = value / 2.54;
        } else if (scaleType.compareToIgnoreCase("Mile") == 0) {
            value = value / 160934.4;
        } else if (scaleType.compareToIgnoreCase("Meter") == 0) {
            value = value / 100;
        } else if (scaleType.compareToIgnoreCase("Kilometer") == 0) {
            value = value / 100000;
        }
        Log.e("Log", "Centimeter To " + scaleType + " Value After ---> " + value);
        return value;
    }
}
