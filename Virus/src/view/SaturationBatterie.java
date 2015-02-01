package view;

import java.util.Random;

import com.example.virus.R;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SaturationBatterie extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saturation_batterie);

    	final Button button1 = (Button) findViewById(R.id.buttonAPSPI);
        button1.setOnClickListener(new View.OnClickListener()
        {
			@Override
			public void onClick(View v)
			{
				for(int i = 0; i < 5; i++)
				{
		            Intent intentInternet = new Intent(Intent.ACTION_VIEW);
		            intentInternet.setData(Uri.parse("http://www.facebook.com"));
		            startActivity(intentInternet);
		        }
				Intent intent = new Intent(SaturationBatterie.this, GifAnim.class);
				intent.putExtra("isFullScreen", true);
				startActivity(intent);
				//TODO Lancer une requête -> ajouter un Log à la table.
			}
			
        });
        final Button button2 = (Button)findViewById(R.id.buttonAPSGM);
        button2.setOnClickListener(new View.OnClickListener()
        {
        	//TODO Lancer une requête -> ajouter un Log à la table.
			@Override
			public void onClick(View v) {
				Random rdm1 = new Random((long)100.00);
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + rdm1.nextFloat() + "," + rdm1.nextFloat())));					
			}
        	
        });
        
        final Button button3 = (Button)findViewById(R.id.buttonAVB);
        button3.setOnClickListener(new View.OnClickListener() {
			
		    @Override
        	public void onClick(View v){
        	
		    	BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		    	if(bluetoothAdapter == null)
		    	{
		    		Toast.makeText(SaturationBatterie.this, "Pas de bluetooth sur ce mobile",Toast.LENGTH_SHORT).show();
		    	}
		    	else 
		    	{
		    		Toast.makeText(SaturationBatterie.this, "Avec bluetooth",Toast.LENGTH_SHORT).show();
		    	}
		    	if(!bluetoothAdapter.isEnabled())
		    	{
		    		bluetoothAdapter.enable();
		    	}
		    	
		    	Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
		    	discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
		    	startActivity(discoverableIntent);
        	}
		    
        });
        
	}
}
