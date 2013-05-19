package com.example.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements OnClickListener, OnGestureListener
{
    private GestureDetector gestureDetector;
    private ScaleGestureDetector scaleGestureDetector;

    View.OnTouchListener gestureListener;

    public void onToggleClicked(View view) {
    	boolean on = ((ToggleButton) view).isChecked();
    	Log.d("Fling", String.valueOf(on));
    	if (on) {
    		new SendMessage().execute("n".toCharArray());
    	}
    	else
    	{
    		new SendMessage().execute("f".toCharArray());
    	}
    }
    
    public void onZoomInClicked(View view) {
    	new SendMessage().execute("i".toCharArray());
    }
    
    public void onZoomOutClicked(View view) {
    	new SendMessage().execute("o".toCharArray());
    }
    
    public void onWeatherClicked(View view)	{
    	new SendMessage().execute("w".toCharArray());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gestureDetector = new GestureDetector(this, this);
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleGestureListener());
        //new ShowImage((ImageView)findViewById(R.id.imageView1)).execute("http://images5.fanpop.com/image/photos/25600000/Hello-Kitty-Sitting-hello-kitty-25604546-1210-1429.jpg");
     }
    
    class ShowImage extends AsyncTask<String, Void, Bitmap> {
    	ImageView imageView;
    	
    	public ShowImage(ImageView imageView) {
    		this.imageView = imageView;
    	}
		@Override
		protected Bitmap doInBackground(String... url) {
			Bitmap mIcon_val = null;
			try {
				URL newurl = new URL(url[0]);
	    		Log.d("Fling", url[0]);
			    mIcon_val = BitmapFactory.decodeStream(newurl.openStream());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return mIcon_val;
		}
		
		protected void onPostExecute(Bitmap result) {
			imageView.setImageBitmap(result);
		}
    	
    }
    public class ScaleGestureListener implements OnScaleGestureListener {

		@Override
		public boolean onScale(ScaleGestureDetector arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onScaleBegin(ScaleGestureDetector arg0) {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public void onScaleEnd(ScaleGestureDetector arg0) {
			// TODO Auto-generated method stub
			Log.d("Fling", String.valueOf(arg0.getScaleFactor()));
		}
    }

    class SendMessage extends AsyncTask<char[], Void, String> {
        @Override
        protected String doInBackground(char[]... direction) {
            try {
                //Socket socket = new Socket("198.100.101.57", 10000);
                Socket socket = new Socket("192.168.137.1", 10000);
                DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
//                DataInputStream din = new DataInputStream(socket.getInputStream());
                Log.d("Fling", "Connected!");
                dout.write(direction[0][0]);
                dout.flush();
                Log.d("Fling", "Message sent!");
//                byte[] buffer = new byte[4096];
//                int result = din.read(buffer, 0, 4096);
//                if (result != -1) {
//                    Log.d("Fling", new String(Arrays.copyOfRange(buffer, 0, result)));
//                }
                socket.close();
            }
            catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
    	boolean retVal = scaleGestureDetector.onTouchEvent(event);
    	if (!scaleGestureDetector.isInProgress()) {
        	retVal = gestureDetector.onTouchEvent(event) || retVal;
    	}
    	return retVal;
    }

	@Override
	public boolean onDown(MotionEvent arg0) {
		return false;
	}

	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		double dx = arg1.getX() - arg0.getX();
		double dy = arg1.getY() - arg0.getY();
		double d = Math.sqrt(dx*dx + dy*dy);
		char[] direction;
        if (d > 50.0)
        {
            Log.d("Fling", String.valueOf(d) + String.valueOf(dx) + String.valueOf(dy));
            if (Math.abs(dx) > Math.abs(dy))
                direction = dx>0?"l".toCharArray():"r".toCharArray();
            else
                direction = dy>0?"d".toCharArray():"u".toCharArray();
            new SendMessage().execute(direction);
        }
		return false;
	}
	

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}
