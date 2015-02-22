package view;

import java.util.Random;

import com.example.virus.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SaturationBattery extends Activity{
	
	private static Random rdm = new Random();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_saturation_battery);
		
		final Button buttonGoogle = (Button) findViewById(R.id.buttonGoogle);
		final Button buttonService = (Button) findViewById(R.id.buttonService);
		final Button buttonBluetooth = (Button) findViewById(R.id.buttonBluetooth);
	}
	
	public void saturationService(View v){
		
	}
	
	public void saturationGoogleMap(View v){
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + rdm.nextFloat() + "," + rdm.nextFloat())));
	}
	
	public void saturationBluetooth(View v){
		startActivity(new Intent(SaturationBattery.this, BluetoothChat.class));
	}

}
