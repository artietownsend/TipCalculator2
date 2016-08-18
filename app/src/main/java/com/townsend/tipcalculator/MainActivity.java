package com.townsend.tipcalculator;

import java.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;


public class MainActivity extends AppCompatActivity
implements OnEditorActionListener, OnClickListener {

    //variables for the widgets
    private EditText billAmountEditText;
    private TextView tipTextView;
    private TextView totalTextView;
    private TextView percentTextView;
    private Button upButton;
    private Button downButton;

    private float tipPercent = .15f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //reference to the widgets
        billAmountEditText = (EditText) findViewById(R.id.billAmountEditText);
        tipTextView = (TextView) findViewById(R.id.tipTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);
        percentTextView = (TextView) findViewById(R.id.percentTextView);
        upButton = (Button) findViewById(R.id.upButton);
        downButton = (Button) findViewById(R.id.downButton);

        billAmountEditText.setOnEditorActionListener(this);
        upButton.setOnClickListener(this);
        downButton.setOnClickListener(this);
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {

        if(actionId == EditorInfo.IME_ACTION_DONE ||
           actionId == EditorInfo.IME_ACTION_UNSPECIFIED){

            calculateAndDisplay();
        }

        return false;
    }

    public void calculateAndDisplay(){

        //get bill amount
        String billAmountString = billAmountEditText.getText().toString();
        float billAmount;
        if(billAmountString.equals("")){
            billAmount = 0;
        }
        else{
            billAmount = Float.parseFloat(billAmountString);
        }

        //calculate tip and total
        float tipAmount = billAmount * tipPercent;
        float totalAmount = billAmount + tipAmount;

        //display results with formatting
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        tipTextView.setText(currency.format(tipAmount));
        totalTextView.setText(currency.format(totalAmount));

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.downButton:
                tipPercent = tipPercent - .01f;
                NumberFormat percent = NumberFormat.getPercentInstance();
                percentTextView.setText(percent.format(tipPercent));
                break;
            case R.id.upButton:
                tipPercent = tipPercent + .01f;
                percent = NumberFormat.getPercentInstance();
                percentTextView.setText(percent.format(tipPercent));
                break;
        }
    }
}
