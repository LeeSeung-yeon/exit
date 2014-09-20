package com.example.uiexample2;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends Activity implements OnClickListener {
	//요
	ImageButton goMap;//요
	ImageButton goHow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        
        goMap = (ImageButton)findViewById(R.id.searchexit);//요
        goMap.setOnClickListener(this);//요
        
        goHow =(ImageButton)findViewById(R.id.exithow);
        goHow.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		int id = v.getId();
		
		if(id == R.id.searchexit){
			Intent i = new Intent(this,lookmap.class);
			startActivity(i);
		}
		
		else if(id ==R.id.exithow){
			Intent d = new Intent(this,howto.class);
			startActivity(d);
		}
		
	}
}
