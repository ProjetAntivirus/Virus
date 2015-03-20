package bluetooth;

import java.util.HashSet;
import java.util.Set;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class BluetoothService  extends Service
{
	  static int REQUEST = 2;
	  static String TAG = "BLUETOOTH_SERVICE";
	  Set<BluetoothDevice> devices = new HashSet<BluetoothDevice>();
	  AcceptThread mAcceptThread;
	  ConnectThread mConnectThread;
	  BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
	  final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver()
	  {
		  public void onReceive(Context context, Intent intent) {
	          String action = intent.getAction();
	          if (BluetoothDevice.ACTION_FOUND.equals(action)) {
	              BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
	              devices.add(device);
	          }
		  }	
	  };

  
	  private void connection(BluetoothDevice device)
	  {
		  	Log.d(TAG, "++ CONNECTION ++");
		  	if (this.mConnectThread == null)
		  	{
		  		this.mConnectThread = new ConnectThread(device);
		  		this.mConnectThread.start();
		  	}
	  }
  
	  private void searchPairedDevices()
	  {
		  devices = mAdapter.getBondedDevices();
	  }
  
	  public IBinder onBind(Intent paramIntent)
	  {
		  return null;
	  }
  
	  public void onCreate()
	  {
		  Log.d(TAG, "+ ON CREATE BLUETOOTH +");
		  if (!this.mAdapter.isEnabled()) {
			  this.mAdapter.enable();
		  }
		  IntentFilter localIntentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		  registerReceiver(this.mBroadcastReceiver, localIntentFilter);
	  }
  
	  public void onDestroy()
	  {
		  Log.d("ON DESTROY", "+++ ON DESTROY +++");
		  unregisterReceiver(this.mBroadcastReceiver);
		  this.mAdapter.disable();
		  super.onDestroy();
	  }
  
	  public int onStartCommand(Intent intent,int flags,int startId)
	  {
		  Log.d(TAG, "++ ON START COMMAND BLUETOOTH ++");
		  this.mAcceptThread = new AcceptThread(mAdapter);
		  this.mAdapter.startDiscovery();
		  this.mAcceptThread.start();
		  searchPairedDevices();
		  for(BluetoothDevice d : devices){	  
			  try{
				  connection(d);
			  }
			  catch (Exception localException) {}
		  }
		  return super.onStartCommand(intent, flags, startId);
	  }
}
