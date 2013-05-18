package com.example.test;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements OnClickListener, OnGestureListener
{
    private GestureDetector gestureDetector;
    private ToggleButton btn_onoff;
    View.OnTouchListener gestureListener;

    public void onToggleClicked(View view) {
    	boolean on = ((ToggleButton) view).isChecked();
    	Log.d("Fling", String.valueOf(on));
    	if (on) {
    		new SendMessage().execute("start".toCharArray());
    	}
    	else
    	{
    		new SendMessage().execute("stop".toCharArray());
    	}
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_onoff = (ToggleButton)findViewById(R.id.toggleButton1);


        gestureDetector = new GestureDetector(this, this);
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };

    }

    class SendMessage extends AsyncTask<char[], Void, String> {
        @Override
        protected String doInBackground(char[]... direction) {
            try {
                Socket socket = new Socket("192.168.137.1", 10000);
                DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
                dout.write(direction[0][0]);
                dout.flush();
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
    	return gestureDetector.onTouchEvent(event);
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
                direction = dx>0?"left".toCharArray():"right".toCharArray();
            else
                direction = dy>0?"down".toCharArray():"up".toCharArray();
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
