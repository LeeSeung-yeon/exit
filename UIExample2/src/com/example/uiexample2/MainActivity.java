package com.example.uiexample2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity implements OnClickListener {

	Button goMap;
	Button goHow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        goMap = (Button)findViewById(R.id.button2);
        goMap.setOnClickListener(this);        
        goHow =(Button)findViewById(R.id.button3);
        goHow.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		int id = v.getId();
		
		if(id == R.id.button2){
			Intent i = new Intent(this,lookmap.class);
			startActivity(i);
		}
		
		else if(id ==R.id.button3){
			Intent d = new Intent(this,howto.class);
			startActivity(d);
		}
		
	}
}
