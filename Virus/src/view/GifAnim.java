package view;

import com.example.virus.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class GifAnim extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		 Intent intent=getIntent();
		 boolean isfullScreen =  intent.getBooleanExtra("isFullScreen", false);
		 if(isfullScreen ){
			 requestWindowFeature(Window.FEATURE_NO_TITLE);
		     getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		     WindowManager.LayoutParams.FLAG_FULLSCREEN);
		 }
		 setContentView(R.layout.view_gif);
	}
}
