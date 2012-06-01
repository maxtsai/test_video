package max.video.view;

import android.content.Context;
import android.widget.VideoView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;


public class myVideoView extends VideoView {

	private float mTouchStartX;
    private float mTouchStartY;
    private float x;
    private float y;
    
    private WindowManager wm=(WindowManager)getContext().getApplicationContext().getSystemService("window");
    private WindowManager.LayoutParams wmParams = ((MyApplication)getContext().getApplicationContext()).getMywmParams();
	
	
	public myVideoView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	     x = event.getRawX();   
	     y = event.getRawY();
	     Log.i("currP", "currX"+x+"====currY"+y);
	     switch (event.getAction()) {
	        case MotionEvent.ACTION_DOWN:
	    		this.stopPlayback();
	        	mTouchStartX =  event.getX();  
	        	mTouchStartY =  event.getY();
	            Log.i("startP", "startX"+mTouchStartX+"====startY"+mTouchStartY);
	            break;
	        case MotionEvent.ACTION_MOVE:	            
	            updateViewPosition();
	            break;

	        case MotionEvent.ACTION_UP:
	        	updateViewPosition();
	        	mTouchStartX=mTouchStartY=0;
	        	this.start();
	        	break;
	     }
	     return true;
	}
	
	 private void updateViewPosition(){
		//wmParams.x=(int) (x-mTouchStartX);
		//wmParams.y=(int) (y-mTouchStartY);
		 wmParams.x=(int) x;
		 wmParams.y=(int) y;

	    wm.updateViewLayout(this, wmParams);
	    
	 }

}
