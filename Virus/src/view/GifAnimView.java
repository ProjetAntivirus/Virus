package view;

import java.io.InputStream;

import com.example.virus.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.view.View;

public class GifAnimView extends View{

	private Movie movie;
	private long _start_time; 
	
	public GifAnimView(Context context) {
		super(context);
		init();
	}
	
	public GifAnimView(Context context, AttributeSet attrs){
		super(context, attrs);
		init();
	}
	
	public GifAnimView(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		InputStream mInputStream = getContext().getResources().openRawResource(R.drawable.gif_matrix);
		movie = Movie.decodeStream(mInputStream);
		
	}
	@Override
	protected void onDraw(Canvas canvas){
		canvas.drawColor(Color.TRANSPARENT); 
		super.onDraw(canvas);
		long _current_time = android.os.SystemClock.uptimeMillis();
		
		if (0 ==_start_time){ 
			_start_time = _current_time;
		}
		if (null!=movie){ 
			int _relatif_time = (int) ((_current_time - _start_time) % movie.duration());
			movie.setTime(_relatif_time); double scalex = (double) this.getWidth() / (double) movie.width(); 
			double scaley = (double) this.getHeight() / (double) movie.height();
			canvas.scale((float) scalex, (float) scaley); 
			movie.draw(canvas, (float) scalex, (float) scaley);
			this.invalidate(); 
		}
	}

}
