package max.video.view;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.MediaController;
import android.widget.Toast;
import android.util.Log;

public class Test_video_viewActivity extends Activity {
    /**
     * TODO: Set the path variable to a streaming video URL or a local media
     * file path.
     */
    private String path = "file:///sdcard/MPEG4.mkv";
	private WindowManager wm = null;
	private WindowManager.LayoutParams wmParams = null;
	private myVideoView mVideoView = null;
    
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        createView();
        
        if (path == "") {
            // Tell the user to provide a media file URL/path.
            Toast.makeText(
            		Test_video_viewActivity.this,
                    "Please edit VideoViewDemo Activity, and set path"
                            + " variable to your media file URL/path",
                    Toast.LENGTH_LONG).show();

        } else {

            /*
             * Alternatively,for streaming media you can use
             * mVideoView.setVideoURI(Uri.parse(URLstring));
             */
            mVideoView.setVideoPath(path);
            mVideoView.setMediaController(new MediaController(this));
            mVideoView.requestFocus();
            mVideoView.start();

        }
    }
    
    private void createView() {
    	mVideoView = new myVideoView(getApplicationContext());
    	wm=(WindowManager)getApplicationContext().getSystemService("window");
    	wmParams = ((MyApplication)getApplication()).getMywmParams();
    	wmParams.type=LayoutParams.TYPE_PHONE;
        wmParams.format=PixelFormat.RGBA_8888;
        wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL | LayoutParams.FLAG_NOT_FOCUSABLE;
        wmParams.gravity = Gravity.CENTER;
        wmParams.x=0;
        wmParams.y=0;
        wmParams.width=320;
        wmParams.height=480;
        wm.addView(mVideoView, wmParams);
    }
    
    @Override
    public void onDestroy(){
    	super.onDestroy();
    	wm.removeView(mVideoView);
    }
    
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d("myVideoView", "### touched");
		return true;
	}
}