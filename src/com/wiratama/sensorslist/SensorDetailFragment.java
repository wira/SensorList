package com.wiratama.sensorslist;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A fragment representing a single Sensor detail screen. This fragment is
 * either contained in a {@link SensorListActivity} in two-pane mode (on
 * tablets) or a {@link SensorDetailActivity} on handsets.
 */
public class SensorDetailFragment extends Fragment implements SensorEventListener {
	
	/**
	 * The sensor type
	 */
	private String type;
	
	/**
	 * The fragment argument representing the sensor type that this fragment
	 * represents.
	 */
	public static final String ARG_SENSOR_TYPE = "sensor_type";

	/**
	 * The sensor manager and the sensor
	 */
	private/* final*/ SensorManager mSensorManager;
    private Sensor mSensor;
    
    /**
     * The layout for detail fragment
     */
    private LinearLayout linearLayout;
    private TextView txt1, txt2, txt3, txt4, txt5;
    
	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public SensorDetailFragment() {
		
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
		mSensor = null;
		
		if (getArguments().containsKey(ARG_SENSOR_TYPE)) {
			type = getArguments().getString(ARG_SENSOR_TYPE);
			mSensor = mSensorManager.getDefaultSensor(Integer.valueOf(type));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_sensor_detail,
				container, false);

		if (type != null) {
			/*((TextView) rootView.findViewById(R.id.sensor_detail))
						.setText(type);*/
			linearLayout = (LinearLayout) rootView.findViewById(R.id.info);
			txt1 = new TextView(getActivity());
			txt2 = new TextView(getActivity());
			txt3 = new TextView(getActivity());
			txt4 = new TextView(getActivity());
			txt5 = new TextView(getActivity());
			txt1.setVisibility(0);
			txt2.setVisibility(0);
			txt3.setVisibility(0);
			txt4.setVisibility(0);
			txt5.setVisibility(0);
			linearLayout.addView(txt1);
			linearLayout.addView(txt2);
			linearLayout.addView(txt3);
			linearLayout.addView(txt4);
			linearLayout.addView(txt5);
		}

		return rootView;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		switch (mSensor.getType()) {
			case Sensor.TYPE_ACCELEROMETER : {
		        txt1.setVisibility(1);
		        txt2.setVisibility(1);
		        txt3.setVisibility(1);

		        txt1.setText("x : " + String.valueOf(event.values[0]));
		        txt2.setText("y : " + String.valueOf(event.values[1]));
		        txt3.setText("z : " + String.valueOf(event.values[2]));
				break;
			}
			case Sensor.TYPE_AMBIENT_TEMPERATURE : {
				// TODO : Fill
				break;
			}
			case Sensor.TYPE_GAME_ROTATION_VECTOR : {
				// TODO : Fill
				break;
			}
			case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR : {
				// TODO : Fill
				break;
			}
			case Sensor.TYPE_GRAVITY : {
				// TODO : Fill
				break;
			}
			case Sensor.TYPE_GYROSCOPE : {
				// TODO : Fill
				break;
			}
			case Sensor.TYPE_LIGHT : {
		        txt1.setVisibility(1);

		        txt1.setText("Light (in lux) : " + String.valueOf(event.values[0]));
				break;
			}
			case Sensor.TYPE_LINEAR_ACCELERATION : {
				// TODO : Fill
				break;
			}
			case Sensor.TYPE_MAGNETIC_FIELD : {
				// TODO : Fill
				break;
			}
			case Sensor.TYPE_PRESSURE : {
				float pressure_value = 0.0f;
			    float height = 0.0f;
			    
			    pressure_value = event.values[0];
		        height = SensorManager.getAltitude(SensorManager.PRESSURE_STANDARD_ATMOSPHERE, pressure_value);
		        
		        txt1.setVisibility(1);
		        txt2.setVisibility(1);
		        txt1.setText("Pressure Value (in hPa / Millibar) : " + String.valueOf(pressure_value));
		        txt2.setText("Height : " + String.valueOf(height));
				break;
			}
			case Sensor.TYPE_PROXIMITY : {
		        txt1.setVisibility(1);

		        txt1.setText("Distance : " + String.valueOf(event.values[0]));
				break;
			}
			case Sensor.TYPE_RELATIVE_HUMIDITY : {
				// TODO : Fill
				break;
			}
			case Sensor.TYPE_ROTATION_VECTOR : {
				// TODO : Fill
				break;
			}
			case Sensor.TYPE_SIGNIFICANT_MOTION : {
				// TODO : Fill
				break;
			}
			case Sensor.TYPE_STEP_COUNTER : {
				// TODO : Fill
				break;
			}
			case Sensor.TYPE_STEP_DETECTOR : {
		        // TODO : Fill
				break;
			}
			default : break;
		}
	}
	
	public void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        Log.d("RESUME", "onResume");
    }

	public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
        Log.d("PAUSE", "onPause");
    }
}
