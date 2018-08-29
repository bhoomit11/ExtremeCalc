package com.extremecalculator.activities;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.extremecalculator.R;
import com.google.common.math.DoubleMath;

import java.text.DecimalFormat;

import bsh.Interpreter;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScientificCalculatorActivity extends BaseActivity {

    @Bind(R.id.ib_screenBack)
    ImageButton ibScreenBack;
    @Bind(R.id.tv_resultTitle)
    TextView tvResultTitle;
    @Bind(R.id.tv_areaCalculatorResult)
    TextView tvAreaCalculatorResult;
    @Bind(R.id.tv_scSqrt)
    TextView tvScSqrt;
    @Bind(R.id.tv_scRoot)
    TextView tvScRoot;
    @Bind(R.id.tv_scIn)
    TextView tvScIn;
    @Bind(R.id.tv_scLog)
    TextView tvScLog;
    @Bind(R.id.tv_scTan)
    TextView tvScTan;
    @Bind(R.id.tv_scaTan)
    TextView tvScaTan;
    @Bind(R.id.tv_scXRestToTwo)
    TextView tvScXRestToTwo;
    @Bind(R.id.tv_scXRestToY)
    TextView tvScXRestToY;
    @Bind(R.id.tv_eRestToX)
    TextView tvERestToX;
    @Bind(R.id.tv_scTenRestToX)
    TextView tvScTenRestToX;
    @Bind(R.id.tv_scCos)
    TextView tvScCos;
    @Bind(R.id.tv_scaCos)
    TextView tvScaCos;
    @Bind(R.id.tv_scPlusOrMinus)
    TextView tvScPlusOrMinus;
    @Bind(R.id.tv_scOneOfX)
    TextView tvScOneOfX;
    @Bind(R.id.tv_scXFactorial)
    TextView tvScXFactorial;
    @Bind(R.id.tv_scPercentage)
    TextView tvScPercentage;
    @Bind(R.id.tv_scSin)
    TextView tvScSin;
    @Bind(R.id.tv_scaSin)
    TextView tvScaSin;
    @Bind(R.id.tv_scNumberSeven)
    TextView tvScNumberSeven;
    @Bind(R.id.tv_scNumberEight)
    TextView tvScNumberEight;
    @Bind(R.id.tv_scNumberNine)
    TextView tvScNumberNine;
    @Bind(R.id.tv_scSignDivision)
    TextView tvScSignDivision;
    @Bind(R.id.tv_scMR)
    TextView tvScMR;
    @Bind(R.id.tv_scaMS)
    TextView tvScaMS;
    @Bind(R.id.tv_scNumberFour)
    TextView tvScNumberFour;
    @Bind(R.id.tv_scNumberFive)
    TextView tvScNumberFive;
    @Bind(R.id.tv_scNumberSix)
    TextView tvScNumberSix;
    @Bind(R.id.tv_scSignMultiplication)
    TextView tvScSignMultiplication;
    @Bind(R.id.tv_scConstant)
    TextView tvScConstant;
    @Bind(R.id.tv_scNumberOne)
    TextView tvScNumberOne;
    @Bind(R.id.tv_scNumberTwo)
    TextView tvScNumberTwo;
    @Bind(R.id.tv_scNumberThree)
    TextView tvScNumberThree;
    @Bind(R.id.tv_scSignMinus)
    TextView tvScSignMinus;
    @Bind(R.id.tv_scStartingBrace)
    TextView tvScStartingBrace;
    @Bind(R.id.tv_scEndingBraces)
    TextView tvScEndingBraces;
    @Bind(R.id.tv_scNumberZero)
    TextView tvScNumberZero;
    @Bind(R.id.tv_scDecimalPoint)
    TextView tvScDecimalPoint;
    @Bind(R.id.tv_scE)
    TextView tvScE;
    @Bind(R.id.tv_scSignAddition)
    TextView tvScSignAddition;
    @Bind(R.id.tv_scSignEqualTO)
    TextView tvScSignEqualTO;
    @Bind(R.id.tv_btnClear)
    TextView tvBtnClear;

    boolean clearflag = true;
    boolean Eflag=true;
    private boolean isRootFunctionEnable = false;
    private double rootXValue;
    private boolean isRestToFunctionEnable = false;
    private double restToXValue;
    private String[] Constants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scientific_calculator);
        ButterKnife.bind(this);

        setButtons();

        Constants =getResources().getStringArray(R.array.canstants);
    }

    private void setButtons() {

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float dpWidth = outMetrics.widthPixels;

        Resources r = getResources();
        float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, r.getDisplayMetrics());

        float individualButtonWidth = (dpWidth / 6) - padding;

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) individualButtonWidth, (int) individualButtonWidth);
        LinearLayout.LayoutParams doubleWidthLayoutParams = new LinearLayout.LayoutParams(((int) individualButtonWidth) * 2, (int) individualButtonWidth);

        //  First Line
        tvScSqrt.setLayoutParams(layoutParams);
        tvScRoot.setLayoutParams(layoutParams);
        tvScIn.setLayoutParams(layoutParams);
        tvScLog.setLayoutParams(layoutParams);
        tvScTan.setLayoutParams(layoutParams);
        tvScaTan.setLayoutParams(layoutParams);

        //  Second Line

        tvScXRestToTwo.setLayoutParams(layoutParams);
        tvScXRestToY.setLayoutParams(layoutParams);
        tvERestToX.setLayoutParams(layoutParams);
        tvScTenRestToX.setLayoutParams(layoutParams);
        tvScCos.setLayoutParams(layoutParams);
        tvScaCos.setLayoutParams(layoutParams);

        //  Third Line

        tvScPlusOrMinus.setLayoutParams(layoutParams);
        tvScOneOfX.setLayoutParams(layoutParams);
        tvScXFactorial.setLayoutParams(layoutParams);
        tvScPercentage.setLayoutParams(layoutParams);
        tvScSin.setLayoutParams(layoutParams);
        tvScaSin.setLayoutParams(layoutParams);

        //  Forth Line

        tvScNumberSeven.setLayoutParams(layoutParams);
        tvScNumberEight.setLayoutParams(layoutParams);
        tvScNumberNine.setLayoutParams(layoutParams);
        tvScSignDivision.setLayoutParams(layoutParams);
        tvScMR.setLayoutParams(layoutParams);
        tvScaMS.setLayoutParams(layoutParams);

        //  Fifth Line

        tvScNumberFour.setLayoutParams(layoutParams);
        tvScNumberFive.setLayoutParams(layoutParams);
        tvScNumberSix.setLayoutParams(layoutParams);
        tvScSignMultiplication.setLayoutParams(layoutParams);
        tvScConstant.setLayoutParams(doubleWidthLayoutParams);

        //  Sixth Line

        tvScNumberOne.setLayoutParams(layoutParams);
        tvScNumberTwo.setLayoutParams(layoutParams);
        tvScNumberThree.setLayoutParams(layoutParams);
        tvScSignMinus.setLayoutParams(layoutParams);
        tvScStartingBrace.setLayoutParams(layoutParams);
        tvScEndingBraces.setLayoutParams(layoutParams);

        //  Seventh Line

        tvScNumberZero.setLayoutParams(layoutParams);
        tvScDecimalPoint.setLayoutParams(layoutParams);
        tvScE.setLayoutParams(layoutParams);
        tvScSignAddition.setLayoutParams(layoutParams);
        tvScSignEqualTO.setLayoutParams(doubleWidthLayoutParams);
    }

    @OnClick({R.id.tv_scNumberZero, R.id.tv_scNumberOne, R.id.tv_scNumberTwo, R.id.tv_scNumberThree,
            R.id.tv_scNumberFour, R.id.tv_scNumberFive, R.id.tv_scNumberSix, R.id.tv_scNumberSeven,
            R.id.tv_scNumberEight, R.id.tv_scNumberNine, R.id.tv_scDecimalPoint})
    void clickNumber(View view) {
        if (clearflag) {
            tvAreaCalculatorResult.setText("");
        }
        switch (view.getId()) {
            case R.id.tv_scNumberZero:
                tvAreaCalculatorResult.setText(tvAreaCalculatorResult.getText().toString() + "0");
                clearflag = false;
                break;
            case R.id.tv_scNumberOne:
                tvAreaCalculatorResult.setText(tvAreaCalculatorResult.getText().toString() + "1");
                clearflag = false;
                break;
            case R.id.tv_scNumberTwo:
                tvAreaCalculatorResult.setText(tvAreaCalculatorResult.getText().toString() + "2");
                clearflag = false;
                break;
            case R.id.tv_scNumberThree:
                tvAreaCalculatorResult.setText(tvAreaCalculatorResult.getText().toString() + "3");
                clearflag = false;
                break;
            case R.id.tv_scNumberFour:
                tvAreaCalculatorResult.setText(tvAreaCalculatorResult.getText().toString() + "4");
                clearflag = false;
                break;
            case R.id.tv_scNumberFive:
                tvAreaCalculatorResult.setText(tvAreaCalculatorResult.getText().toString() + "5");
                clearflag = false;
                break;
            case R.id.tv_scNumberSix:
                tvAreaCalculatorResult.setText(tvAreaCalculatorResult.getText().toString() + "6");
                clearflag = false;
                break;
            case R.id.tv_scNumberSeven:
                tvAreaCalculatorResult.setText(tvAreaCalculatorResult.getText().toString() + "7");
                clearflag = false;
                break;
            case R.id.tv_scNumberEight:
                tvAreaCalculatorResult.setText(tvAreaCalculatorResult.getText().toString() + "8");
                clearflag = false;
                break;
            case R.id.tv_scNumberNine:
                tvAreaCalculatorResult.setText(tvAreaCalculatorResult.getText().toString() + "9");
                clearflag = false;
                break;
            case R.id.tv_scDecimalPoint:
                tvAreaCalculatorResult.setText(tvAreaCalculatorResult.getText().toString() + ".");
                clearflag = false;
                break;
        }
    }

    @OnClick({R.id.tv_scSignDivision, R.id.tv_scSignMultiplication, R.id.tv_scSignMinus,
            R.id.tv_scSignAddition, R.id.tv_scSignEqualTO, R.id.tv_scE, R.id.tv_scPercentage})
    void clickExpressionSign(View view) {
        if (clearflag) {
            tvAreaCalculatorResult.setText("");
        }
        switch (view.getId()) {
            case R.id.tv_scSignDivision:
                tvAreaCalculatorResult.setText(tvAreaCalculatorResult.getText().toString() + "/");
                clearflag = false;
                break;
            case R.id.tv_scSignMultiplication:
                tvAreaCalculatorResult.setText(tvAreaCalculatorResult.getText().toString() + "*");
                clearflag = false;
                break;
            case R.id.tv_scSignMinus:
                tvAreaCalculatorResult.setText(tvAreaCalculatorResult.getText().toString() + "-");
                clearflag = false;
                break;
            case R.id.tv_scSignAddition:
                tvAreaCalculatorResult.setText(tvAreaCalculatorResult.getText().toString() + "+");
                clearflag = false;
                break;
            case R.id.tv_scSignEqualTO:
                try {

                    if (isRootFunctionEnable) {
                        isRootFunctionEnable = false;
                        double xValue = rootXValue;
                        rootXValue = 0;
                        try {
                            if (!TextUtils.isEmpty(tvAreaCalculatorResult.getText().toString().trim())) {
                                if (TextUtils.isDigitsOnly(tvAreaCalculatorResult.getText().toString().trim())) {
                                    double result = Math.pow(xValue, (1 / Double.parseDouble(tvAreaCalculatorResult.getText().toString().trim())));
                                    tvAreaCalculatorResult.setText(new DecimalFormat("#.####").format(result));
                                } else {
                                    tvAreaCalculatorResult.setText("Syntax Error");
                                }
                            } else {
                                tvAreaCalculatorResult.setText("Syntax Error");
                            }
                        } catch (Exception e) {
                            tvAreaCalculatorResult.setText("Syntax Error");
                        }
                        return;
                    }

                    if (isRestToFunctionEnable) {
                        isRestToFunctionEnable = false;
                        double xValue = restToXValue;
                        restToXValue = 0;
                        try {
                            if (!TextUtils.isEmpty(tvAreaCalculatorResult.getText().toString().trim())) {
                                if (TextUtils.isDigitsOnly(tvAreaCalculatorResult.getText().toString().trim())) {
                                    double result = Math.pow(xValue,  Double.parseDouble(tvAreaCalculatorResult.getText().toString().trim()));
                                    tvAreaCalculatorResult.setText(new DecimalFormat("#.####").format(result));
                                } else {
                                    tvAreaCalculatorResult.setText("Syntax Error");
                                }
                            } else {
                                tvAreaCalculatorResult.setText("Syntax Error");
                            }
                        } catch (Exception e) {
                            tvAreaCalculatorResult.setText("Syntax Error");
                        }
                        return;
                    }
//                    if(tvAreaCalculatorResult.getText().toString().contains("E")){
//                        try {
//                            Double eResult = Double.parseDouble(tvAreaCalculatorResult.getText().toString());
//                            eResult = Math.exp(eResult);
//                            tvAreaCalculatorResult.setText(String.valueOf(eResult.doubleValue()));
//                        }catch (Exception e){
//                            e.printStackTrace();
//                            showErrorMessage("Syntax Error");
//                        }
//                    }
                    Interpreter interpreter = new Interpreter();
                    interpreter.eval("result = " + tvAreaCalculatorResult.getText().toString());
                    String abc = String.valueOf(interpreter.get("result"));
                    if(tvAreaCalculatorResult.getText().toString().contains("E")){
                        tvAreaCalculatorResult.setText(new DecimalFormat("#.##E0").format((Double.valueOf(abc).doubleValue())));
                    }
                    tvAreaCalculatorResult.setText(new DecimalFormat("#.####").format((Double.valueOf(abc).doubleValue())));

                } catch (Exception e) {
                    e.printStackTrace();
                    showErrorMessage("Syntax Error");
                    tvAreaCalculatorResult.setText("Syntax Error");
                    tvAreaCalculatorResult.postDelayed(new Runnable() {
                        public void run() {
                            tvAreaCalculatorResult.setText("0");
                            clearflag=true;
                        }
                    }, 1000);
                }
                break;
            case R.id.tv_scE:
                if (clearflag) {
                    tvAreaCalculatorResult.setText("");
                }
                if(Eflag) {
                    tvAreaCalculatorResult.setText(tvAreaCalculatorResult.getText().toString()+"E");
                    Eflag=false;
                    clearflag = false;
                }
                break;
            case R.id.tv_scPercentage:
                try{
                    Interpreter interpreter = new Interpreter();
                    interpreter.eval("result = " + tvAreaCalculatorResult.getText().toString());
                    String result = String.valueOf(interpreter.get("result"));
                    tvAreaCalculatorResult.setText(new DecimalFormat("#.####").format((Double.valueOf(result))/100));
                }catch (Exception e){
                    e.printStackTrace();
                    showErrorMessage("Syntax Error");
                    tvAreaCalculatorResult.setText("Syntax Error");
                    tvAreaCalculatorResult.postDelayed(new Runnable() {
                        public void run() {
                            tvAreaCalculatorResult.setText("0");
                            clearflag=true;
                        }
                    }, 1000);                }
                clearflag = false;
                break;
        }
    }

    @OnClick({R.id.tv_scStartingBrace, R.id.tv_scEndingBraces, R.id.tv_scConstant,
            R.id.tv_scMR, R.id.tv_scaMS})
    void clickConstants(View view) {
        if (clearflag) {
            tvAreaCalculatorResult.setText("");
        }
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(view == tvScStartingBrace){
            tvAreaCalculatorResult.setText(tvAreaCalculatorResult.getText().toString()+"(");
            clearflag = false;
        }
        else if(view == tvScEndingBraces){
            tvAreaCalculatorResult.setText(tvAreaCalculatorResult.getText().toString()+")");
            clearflag = false;
        }
        else if(view == tvScConstant){
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
            builder.setItems(Constants, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.setTitle("Constant Values");
            builder.show();
        }
        else if(view == tvScaMS){
            if (tvAreaCalculatorResult.getText().toString().matches("[0-9]+")){
                sharedPreferences.edit().putString("Memory",tvAreaCalculatorResult.getText().toString()).commit();
            }
        }
        else if(view == tvScMR){
            tvAreaCalculatorResult.setText(sharedPreferences.getString("Memory",""));
        }
    }

    @OnClick({R.id.tv_scSqrt, R.id.tv_scRoot, R.id.tv_scIn, R.id.tv_scLog, R.id.tv_scTan, R.id.tv_scaTan,
            R.id.tv_scCos, R.id.tv_scaCos, R.id.tv_scSin, R.id.tv_scaSin})
    void clickMathFunction(View view) {
        if (view == tvScSqrt) {
            // Calculate square-root
            try {
                double result = Math.sqrt(Double.parseDouble(tvAreaCalculatorResult.getText().toString().trim()));
                tvAreaCalculatorResult.setText(new DecimalFormat("#.####").format(result));
            } catch (Exception e) {
                tvAreaCalculatorResult.setText("Syntax Error");
            }
        } else if (view == tvScRoot) {
            if (!isRootFunctionEnable) {
                if (!TextUtils.isEmpty(tvAreaCalculatorResult.getText().toString().trim())) {
                    if (TextUtils.isDigitsOnly(tvAreaCalculatorResult.getText().toString().trim())) {
                        try {
                            rootXValue = Double.parseDouble(tvAreaCalculatorResult.getText().toString().trim());
                            tvAreaCalculatorResult.setText("");
                            isRootFunctionEnable = true;
                        } catch (Exception e) {
                            tvAreaCalculatorResult.setText("Syntax Error");
                        }
                    }
                }
            }
        } else if (view == tvScIn) {
            // Calculate Log 10
            try {
                double result = Math.log10(Double.parseDouble(tvAreaCalculatorResult.getText().toString().trim()));
                tvAreaCalculatorResult.setText(new DecimalFormat("#.####").format(result));
            } catch (Exception e) {
                tvAreaCalculatorResult.setText("Syntax Error");
            }
        } else if (view == tvScLog) {
            // Calculate Log
            try {
                double result = Math.log(Double.parseDouble(tvAreaCalculatorResult.getText().toString().trim()));
                tvAreaCalculatorResult.setText(new DecimalFormat("#.####").format(result));
            } catch (Exception e) {
                tvAreaCalculatorResult.setText("Syntax Error");
            }
        } else if (view == tvScTan) {
            // Calculate Tan
            try {
                double result = Math.tan(Double.parseDouble(tvAreaCalculatorResult.getText().toString().trim()));
                tvAreaCalculatorResult.setText(new DecimalFormat("#.####").format(result));
            } catch (Exception e) {
                tvAreaCalculatorResult.setText("Syntax Error");
            }
        } else if (view == tvScaTan) {
            // Calculate Atan
            try {
                double result = Math.atan(Double.parseDouble(tvAreaCalculatorResult.getText().toString().trim()));
                tvAreaCalculatorResult.setText(new DecimalFormat("#.####").format(result));
            } catch (Exception e) {
                tvAreaCalculatorResult.setText("Syntax Error");
            }
        } else if (view == tvScCos) {
            // Calculate Cos
            try {
                double result = Math.cos(Double.parseDouble(tvAreaCalculatorResult.getText().toString().trim()));
                tvAreaCalculatorResult.setText(new DecimalFormat("#.####").format(result));
            } catch (Exception e) {
                tvAreaCalculatorResult.setText("Syntax Error");
            }
        } else if (view == tvScCos) {
            // Calculate Cos
            try {
                double result = Math.cos(Double.parseDouble(tvAreaCalculatorResult.getText().toString().trim()));
                tvAreaCalculatorResult.setText(new DecimalFormat("#.####").format(result));
            } catch (Exception e) {
                tvAreaCalculatorResult.setText("Syntax Error");
            }
        } else if (view == tvScaCos) {
            // Calculate ACos
            try {
                double result = Math.acos(Double.parseDouble(tvAreaCalculatorResult.getText().toString().trim()));
                tvAreaCalculatorResult.setText(new DecimalFormat("#.####").format(result));
            } catch (Exception e) {
                tvAreaCalculatorResult.setText("Syntax Error");
            }
        } else if (view == tvScSin) {
            // Calculate Sin
            try {
                double result = Math.sin(Double.parseDouble(tvAreaCalculatorResult.getText().toString().trim()));
                tvAreaCalculatorResult.setText(new DecimalFormat("#.####").format(result));
            } catch (Exception e) {
                tvAreaCalculatorResult.setText("Syntax Error");
            }
        } else if (view == tvScaSin) {
            // Calculate ASin
            try {
                double result = Math.asin(Double.parseDouble(tvAreaCalculatorResult.getText().toString().trim()));
                tvAreaCalculatorResult.setText(new DecimalFormat("#.####").format(result));
            } catch (Exception e) {
                tvAreaCalculatorResult.setText("Syntax Error");
            }
        }
    }

    @OnClick({R.id.tv_scXRestToTwo, R.id.tv_scXRestToY, R.id.tv_scTenRestToX, R.id.tv_eRestToX, R.id.tv_scPlusOrMinus,
            R.id.tv_scOneOfX, R.id.tv_scXFactorial})
    void clickFunctions(View view) {
        if (view == tvScXRestToTwo) {
            // Calculate Square
            try {
                double result = (Double.parseDouble(tvAreaCalculatorResult.getText().toString().trim())) *
                        Double.parseDouble(tvAreaCalculatorResult.getText().toString().trim());
                tvAreaCalculatorResult.setText(new DecimalFormat("#.####").format(result));
            } catch (Exception e) {
                tvAreaCalculatorResult.setText("Syntax Error");
            }
        } else if (view == tvScXRestToY) {

            if (!isRestToFunctionEnable) {
                if (!TextUtils.isEmpty(tvAreaCalculatorResult.getText().toString().trim())) {
                    if (TextUtils.isDigitsOnly(tvAreaCalculatorResult.getText().toString().trim())) {
                        try {
                            restToXValue = Double.parseDouble(tvAreaCalculatorResult.getText().toString().trim());
                            tvAreaCalculatorResult.setText("");
                            isRestToFunctionEnable = true;
                        } catch (Exception e) {
                            tvAreaCalculatorResult.setText("Syntax Error");
                        }
                    }
                }
            }
        } else if (view == tvScTenRestToX) {
            // Calculate 10 rest to x
            try {
                if (!(tvAreaCalculatorResult.getText().toString().trim().contains("."))) {
                    double result = Math.pow(10, Integer.parseInt(tvAreaCalculatorResult.getText().toString().trim()));
                    tvAreaCalculatorResult.setText(new DecimalFormat("#.####").format(result));
                }
            } catch (Exception e) {
                tvAreaCalculatorResult.setText("Syntax Error");
            }
        } else if (view == tvERestToX) {
// Calculate e rest to x
            try {
                if (!(tvAreaCalculatorResult.getText().toString().trim().contains("."))) {
                    double result = Math.pow(Math.E, Integer.parseInt(tvAreaCalculatorResult.getText().toString().trim()));
                    tvAreaCalculatorResult.setText(new DecimalFormat("#.####").format(result));
                }
            } catch (Exception e) {
                tvAreaCalculatorResult.setText("Syntax Error");
            }

        } else if (view == tvScPlusOrMinus) {
            //Invert Sign
            try {
                double result = (Double.parseDouble(tvAreaCalculatorResult.getText().toString())) * (-1);
                tvAreaCalculatorResult.setText(new DecimalFormat("#.####").format(result));
            }catch (Exception e){
                e.printStackTrace();
                tvAreaCalculatorResult.setText("Syntax Error");
            }
        } else if (view == tvScOneOfX) {

            // Calculate 1 of X
            try {
                double result = (1 / Double.parseDouble(tvAreaCalculatorResult.getText().toString().trim()));
                tvAreaCalculatorResult.setText(new DecimalFormat("#.####").format(result));
            } catch (Exception e) {
                tvAreaCalculatorResult.setText("Syntax Error");
            }
        } else if (view == tvScXFactorial) {
            // Calculate Factorial
            try {
                if (!(tvAreaCalculatorResult.getText().toString().trim().contains("."))) {
                    double result = DoubleMath.factorial(Integer.parseInt(tvAreaCalculatorResult.getText().toString().trim()));
                    tvAreaCalculatorResult.setText(new DecimalFormat("#.####").format(result));
                }
            } catch (Exception e) {
                tvAreaCalculatorResult.setText("Syntax Error");
            }
        }
    }

    @OnClick({R.id.tv_btnClear})
    void clickClear(View view) {
        tvAreaCalculatorResult.setText("0");
        isRootFunctionEnable = false;
        rootXValue = 0;
        isRestToFunctionEnable = false;
        restToXValue = 0;
        clearflag=true;
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
