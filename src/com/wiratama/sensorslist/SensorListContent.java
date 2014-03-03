package com.wiratama.sensorslist;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class SensorListContent {
	/**
	 * An array of sensor items.
	 */
	public List<SensorItem> ITEMS;

	/**
	 * A map of sensor items, by ID.
	 */
	//public Map<String, sensorItem> ITEM_MAP = new HashMap<String, sensorItem>();
	
	public SensorListContent() {
		ITEMS = new ArrayList<SensorItem>();
	}
	
	public void add(SensorItem item){
		this.ITEMS.add(item);
		//this.ITEM_MAP.put(item.id, item);
	}
	
	public static class SensorItem implements Parcelable {
		public String id;
		public String name; // process name
		//public sensorManager.RunningAppProcessInfo item;
		public int pss; // pss memory (in kB)
		public int uss; // private dirty memory (in kB)
		public long rx; // received network bytes
		public long tx; // transmitted network
		public int cpu_usage; // in percent
		
		public SensorItem(String id, String name, int pss, int uss, String tx_rx, int cpu_usage) {
			this.pss = pss;
			this.uss = uss;
			this.rx = Long.parseLong(tx_rx.split(",")[0]);
			this.tx = Long.parseLong(tx_rx.split(",")[1]);
			this.cpu_usage = cpu_usage;
			this.id = id;
			this.name = name;
		}
		
		public SensorItem(Parcel in) {
			readFromParcel(in);
		}
		
		@Override
		public String toString() {
			return this.name;
		}

		@Override
		public int describeContents() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeString(id);
			dest.writeString(name);
			dest.writeInt(pss);
			dest.writeInt(uss);
			dest.writeLong(rx);
			dest.writeLong(tx);
			dest.writeInt(cpu_usage);
		}
		
		private void readFromParcel(Parcel in) {
			id = in.readString();
			name = in.readString();
			pss = in.readInt();
			uss = in.readInt();
			rx = in.readLong();
			tx = in.readLong();
			cpu_usage = in.readInt();
		}
		
		public static final Parcelable.Creator CREATOR = 
				new Parcelable.Creator() {
					public SensorItem createFromParcel(Parcel in) {
						return new SensorItem(in);
					}

					@Override
					public Object[] newArray(int size) {
						return new SensorItem[size];
					}
				};
	}
}
