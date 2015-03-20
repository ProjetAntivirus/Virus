package overcharging;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.example.virus.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class Maps  extends FragmentActivity  implements LocationListener
{
  GoogleMap googleMap;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_maps);
    
    this.googleMap = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
    this.googleMap.setMyLocationEnabled(true);
    
    LocationManager localLocationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
    String str = localLocationManager.getBestProvider(new Criteria(), true);
    Location localLocation = localLocationManager.getLastKnownLocation(str);
    if (localLocation != null) {
      onLocationChanged(localLocation);
    }
    localLocationManager.requestLocationUpdates(str, 20000L, 0.0F, this);
  }
  
  public void onLocationChanged(Location paramLocation)
  {
    TextView localTextView = (TextView)findViewById(R.id.tv_location);
    
    double d1 = paramLocation.getLatitude();
    double d2 = paramLocation.getLongitude();
    
    LatLng localLatLng = new LatLng(d1, d2);
    this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(localLatLng));
    this.googleMap.animateCamera(CameraUpdateFactory.zoomTo(15.0F));
    
    localTextView.setText("Latitude:" + d1 + ", Longitude:" + d2);
  }
  
  public void onProviderDisabled(String paramString) {}
  
  public void onProviderEnabled(String paramString) {}
  
  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle) {}
}
