package bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;
import java.io.IOException;
import java.util.UUID;

public class ConnectThread extends Thread
{
	  private static UUID SERVER_UUID = UUID.fromString("8ce255c0-200a-11e0-ac64-0800200c9a66");
	  private static String TAG = "CONNECT THREAD";
	  private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	  private final BluetoothDevice mmDevice;
	  private final BluetoothSocket mmSocket;
  
	  public ConnectThread(BluetoothDevice device)
	  {
		    Log.d(TAG, "+ CONSTRUCTEUR +");
		    mmDevice = device;
		    BluetoothSocket tmp =null;
		    try
		    {
		    	tmp = mmDevice.createInsecureRfcommSocketToServiceRecord(SERVER_UUID);
		    }
		    catch (IOException localIOException)
		    {
		    	Log.e(TAG, "- EXCEPTION CreateInsecureRfcommSocketToServiceRecord -");
		    }
		    this.mmSocket = tmp;
	  }

  
	  public void cancel()
	  {
		  Log.d(TAG, "+++ CANCEL +++");
		  try
		  {
			  this.mmSocket.close();
		  }
		  catch (IOException localIOException) {}
	  }
  
	  public void run()
	  {
		  Log.d(TAG, "++ RUN ++");
		  this.mBluetoothAdapter.cancelDiscovery();
		  try
		  {
			  this.mmSocket.connect();
		  }
		  catch (IOException localIOException1)
		  {
			  try
			  {
				  Log.e(TAG, "-- CONNECTION FAILURE --");
				  this.mmSocket.close();
			  }
			  catch (IOException localIOException2) {}
		  }
	  }
}
