package max.video.view;

import android.content.Context;
import android.graphics.Rect;
import android.widget.VideoView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;


public class myVideoView extends VideoView {

	private WindowManager wm=(WindowManager)getContext().getApplicationContext().getSystemService("window" );
	private WindowManager.LayoutParams wmParams = ((MyApplication)getContext().getApplicationContext()).getMywmParams();
	
	private float prevX = 0f;
	private float prevY = 0f;
	
	private int statusBarHeight;
	
	
	
	public myVideoView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			prevX = event.getRawX();
			prevY = event.getRawY();
			this.pause();
			break;
		case MotionEvent.ACTION_MOVE:
			if(statusBarHeight == 0){  
	            View rootView  = this.getRootView();  
	            Rect r = new Rect();  
	            rootView.getWindowVisibleDisplayFrame(r);  
	            statusBarHeight = r.top;  
	        } 
			wmParams.x = (int) (event.getRawX() - prevX);
			wmParams.y = (int) (event.getRawY() - prevY - statusBarHeight);
			wm.updateViewLayout(this, wmParams);				
			break;
		case MotionEvent.ACTION_UP:
			this.start();
			break;
		}
		return true;
	}

}
