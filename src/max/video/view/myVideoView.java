package max.video.view;

import android.content.Context;
import android.widget.VideoView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;


public class myVideoView extends VideoView {

	private WindowManager wm=(WindowManager)getContext().getApplicationContext().getSystemService("window");
	private WindowManager.LayoutParams wmParams = ((MyApplication)getContext().getApplicationContext()).getMywmParams();
	
	private int prevX;
	private int prevY;
	
	
	public myVideoView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			prevX = (int) event.getX();
			prevY = (int) event.getY();
			this.pause();
			break;
		case MotionEvent.ACTION_MOVE:
			if (Math.abs(event.getX()-prevX)>10 || Math.abs(event.getY()-prevY)>10) {
				wmParams.x = (int) event.getX();
				wmParams.y = (int) event.getY();
				wm.updateViewLayout(this, wmParams);
			}
			prevX = (int) event.getX();
			prevY = (int) event.getY();
			break;
		case MotionEvent.ACTION_UP:
			//wmParams.x -= 50;
			//wmParams.y -= 50;
			//wm.updateViewLayout(this, wmParams);
			this.start();
			break;
		}
		return true;
	}

}
