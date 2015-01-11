package com.example.virus;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_main);
    	
    	final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener()
        {
			@Override
			public void onClick(View v)
			{
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=44.8373682,-0.576144")));			
				//TODO Lancer une requête -> ajouter un Log à la table.
			}
        });
    }	
    	
}
