package overcharging;

import com.example.virus.R;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import android.os.Bundle;

public class GoogleMaps extends MapActivity {

	private MapView mapView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_google_maps);
		mapView = (MapView) this.findViewById(R.id.map);

	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}
