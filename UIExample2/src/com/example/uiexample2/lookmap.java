package com.example.uiexample2;

import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

public class lookmap extends Activity{
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// ��㈃��landscape(媛��) ��㈃�쇰� 怨����� �띠� 寃쎌�
	        setContentView(R.layout.lookmap);
	    }
	 

}