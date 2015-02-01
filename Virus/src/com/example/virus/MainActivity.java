package com.example.virus;


import view.SaturationBatterie;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_main);

    	final Button button1 = (Button) findViewById(R.id.buttonAPSB);
        button1.setOnClickListener(new View.OnClickListener()
        {
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(MainActivity.this, SaturationBatterie.class);
				startActivity(intent);	
			}
			
        });
        final Button button2 = (Button) findViewById(R.id.buttonESS);
        button2.setOnClickListener(new View.OnClickListener()
        {
			@Override
			public void onClick(View v)
			{
				
			}
			
        });
        
        final Button button3 = (Button) findViewById(R.id.buttonCLL);
        button3.setOnClickListener(new View.OnClickListener()
        {
			@Override
			public void onClick(View v)
			{
				
				/*
				Intent intent = new Intent(MainActivity.this, SaturationBatterie.class);
				startActivity(intent);
				*/
			}
			
        });
        
    }	
    	
}
