package bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.util.UUID;

public class AcceptThread extends Thread {
	
	  private static String MY_NAME = "VIRUS";
	  private static UUID MY_UUID = UUID.fromString("8ce255c0-200a-11e0-ac64-0800200c9a66");
	  private static String TAG = "ACCEPT THREAD";
	  private final BluetoothServerSocket mmServerSocket;
	  private final BluetoothAdapter mAdapter;
  
	  public AcceptThread(BluetoothAdapter adapter)
	  {
		  mAdapter = adapter;
		  Log.d(TAG, "+ CONSTRUCTOR +");
		  BluetoothServerSocket tmp = null;
		  try
		  {
			  tmp  = mAdapter.listenUsingRfcommWithServiceRecord(MY_NAME, MY_UUID);
		  }
		  catch (IOException localIOException)
		  {
			  Log.e(TAG, "- EXCEPTION ListenUsingRfcommWithServiceRecord -");
		  }
		  this.mmServerSocket = tmp;
	  }
  
	  public void cancel()
	  {
		  Log.d(TAG, "+++ CANCEL +++");
		  try
		  {
			  this.mmServerSocket.close();
		  }
		  catch (IOException localIOException) {}
	  }

	  public void run()
	  {
		  BluetoothSocket socket = null;
		  while (true){
			  try{
				  socket = mmServerSocket.accept();
				  if(socket != null){
					  mmServerSocket.close();
				  }
			  }catch (IOException ioe){}
		  }
	  }
}
