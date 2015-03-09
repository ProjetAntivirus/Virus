package com.example.virus;

import sms.Sms;
import view.DatabaseActivity;
import view.SaturationBattery;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Button buttonSatuBatterie = (Button) findViewById(R.id.buttonSaturationBattery);
		final Button buttonEnvoyeSMS = (Button) findViewById(R.id.buttonSMS);
		final Button buttonConsLogs = (Button) findViewById(R.id.buttonLogs);
	}

	public void saturationBattery(View view) {
		Intent intent = new Intent(MainActivity.this, SaturationBattery.class);
		startActivity(intent);
	}

	public void sendSMS(View view) {
		Intent intent = new Intent(MainActivity.this, Sms.class);
		startActivity(intent);
	}

	public void checkLogs(View view) {
		Intent intent = new Intent(MainActivity.this, DatabaseActivity.class);
		startActivity(intent);
	}

}
