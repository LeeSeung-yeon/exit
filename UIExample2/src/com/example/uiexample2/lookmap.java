package com.example.uiexample2;

import java.util.List;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.wizturn.sdk.WizTurnDelegate;
import com.wizturn.sdk.WizTurnManager;
import com.wizturn.sdk.WizTurnProximityState;
import com.wizturn.sdk.baseclass.IWizTurnController;
import com.wizturn.sdk.connect.WizTurnBeaconConnect;
import com.wizturn.sdk.entity.WizTurnBeacons;

public class lookmap extends Activity{

		public WizTurnBeacons mWizTurnBeacon;
		public WizTurnManager _wizturnMgr;
		public WizTurnBeaconConnect _connect;
		
		private final String UUID1 = "d5756247-57a2-4344-915d-222222222221";
		private final String UUID2 = "d5756247-57a2-4344-915d-222222222222";
		private final String UUID3 = "d5756247-57a2-4344-915d-222222222223";
		public final int REQUEST_ENABLE_BT = 0000;
		public int mMode;
		public final int SCANLIST = 0;
		public final int BEACON_DETAIL = 1;
		
		private double[] distances = {0,0,0};
		private double[] position = {0,0,0};
		
		public MapMove move;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// ��㈃��landscape(媛��) ��㈃�쇰� 怨����� �띠� 寃쎌�
		        setContentView(R.layout.lookmap);
		        wizturnMgr_setup();
		        
		        move = (MapMove)findViewById(R.id.mapmove);
		        
		}
		@Override
		protected void onStart() {
			super.onStart();
			Log.d("WizTurnBeacon" ,"onStart()");
			wizturnMgr_setup();

		}


		public void onBackPressed() {
			Log.d("WizTurnBeacon" ,"onBackPressed()");
			back();
		}

		@Override
		public void onDestroy() {
			super.onDestroy();
			Log.d("WizTurnBeacon" ,"onDestroy()");
			if (_wizturnMgr.isStarted()) {
				// WizTurnMgr Destroy
				_wizturnMgr.destroy();
			}
		}

		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			Log.d("WizTurnBeacon" ,"onActivityResult()");
			//BLE available
			if (requestCode == REQUEST_ENABLE_BT) {
				if (resultCode == Activity.RESULT_OK) {
					_wizturnMgr.setInitController();
					_wizturnMgr.setWizTurnDelegate(_wtDelegate);
					_wizturnMgr.startController();
				} else {
					//BLE Not available 
					Toast.makeText(this, "Bluetooth Low Energy not enabled", Toast.LENGTH_LONG).show();
				}
			}
			super.onActivityResult(requestCode, resultCode, data);
		}


		private WizTurnDelegate _wtDelegate = new WizTurnDelegate() {
			
			@Override
			public void onGetRSSI(IWizTurnController iWizTurnController, List<String> Data,
					List<Integer> RSSI) {
				// TODO Auto-generated method stub
				Log.d("WizTurnBeacon", "GATT BLE onGetRSSI wtDelegate");
			}
			
			@Override
			public void onGetProximity(IWizTurnController iWizTurnController,
					WizTurnProximityState proximity) {
				// TODO Auto-generated method stub
				Log.d("WizTurnBeacon", "GATT BLE on GetProximity wtDelegate");
				
			}
			
			@Override
			public void onGetDeviceList(IWizTurnController iWizTurnController,
					List<WizTurnBeacons> device) {
				// TODO Auto-generated method stub
				Log.d("WizTurnBeacon", "GATT BLE onGetDeviceList wtDelegate");
				
				for(int i=0;i<device.size();i++){
					if(device.get(i).getProximityUUID().equals(UUID1)){
						distances[0]=device.get(i).getDistance();
					
					}else if(device.get(i).getProximityUUID().equals(UUID2)){
						distances[1]=device.get(i).getDistance();
					}else if(device.get(i).getProximityUUID().equals(UUID3)){
						distances[2]=device.get(i).getDistance();
					}	
				}
//				Toast.makeText(getBaseContext(), "w: "+move.width+"h: "+move.height*0.8, Toast.LENGTH_SHORT).show();
				spot();
				move.userX=getPositionX()*10;
				move.userY=getPositionY()*10;
				//Toast.makeText(getBaseContext(), "x: "+move.userX+"y: "+move.userY, Toast.LENGTH_SHORT).show();
			}
		};

		public void back(){
			Log.d("WizTurnBeacon" ,"back()");
			if(mMode == SCANLIST){
				if (_wizturnMgr.isStarted()) {
					// WizTurn Scan Stop
					_wizturnMgr.stopController();
				}
				finish();
			}else if(mMode == BEACON_DETAIL){
				if(_connect != null && _connect.isConnected() == true){
					//disconnect
					_connect.close();
				}
				_wizturnMgr.startController();			

			}else{
				_wizturnMgr.destroy();
			}
		}

		public void wizturnMgr_setup(){
			Log.d("WizTurnBeacon" ,"wizturnMgr_setup()");
			_wizturnMgr = WizTurnManager.sharedInstance(this);

			// Check if device supports BLE.
			if (!_wizturnMgr.hasBluetooth()) {
				Toast.makeText(this, "Device does not have Bluetooth Low Energy", Toast.LENGTH_LONG).show();
				return;
			}
			// If BLE is not enabled, let user enable it.
			if (!_wizturnMgr.isBluetoothEnabled()) {
				Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
				startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
			} else {
				//Wizturn Scan Start
				_wizturnMgr.setInitController();
				_wizturnMgr.setWizTurnDelegate(_wtDelegate);
				_wizturnMgr.startController();
			}

		}
		
		private void spot(){
				double[] fstpo,sndpo,trdpo;
				
				fstpo = new double[]{10, 0, 0};
				sndpo = new double[]{2, 10, 0};
				trdpo = new double[]{8, 9, 0};
				
				for(int i=0;i<3;i++){
					fstpo[i]*=0.63;
					sndpo[i]*=0.63;
					trdpo[i]*=0.63;	
				}
				
				double[][] att ={
						{2*(fstpo[0]-sndpo[0]),2*(fstpo[1]-sndpo[1]),2*(fstpo[2]-sndpo[2]),(fstpo[0]*fstpo[0]+fstpo[1]*fstpo[1]+fstpo[2]*fstpo[2])-(sndpo[0]*sndpo[0]+sndpo[1]*sndpo[1]+sndpo[2]*sndpo[2])+(distances[1]*distances[1])-(distances[0]*distances[0])},
						{2*(sndpo[0]-trdpo[0]),2*(sndpo[1]-trdpo[1]),2*(sndpo[2]-trdpo[2]),(sndpo[0]*sndpo[0]+sndpo[1]*sndpo[1]+sndpo[2]*sndpo[2])-(trdpo[0]*trdpo[0]+trdpo[1]*trdpo[1]+trdpo[2]*trdpo[2])+(distances[2]*distances[2])-(distances[1]*distances[1])},
						{2*(trdpo[0]-fstpo[0]),2*(trdpo[1]-fstpo[1]),2*(trdpo[2]-fstpo[2]),(trdpo[0]*trdpo[0]+trdpo[1]*trdpo[1]+trdpo[2]*trdpo[2])-(fstpo[0]*fstpo[0]+fstpo[1]*fstpo[1]+fstpo[2]*fstpo[2])+(distances[0]*distances[0])-(distances[2]*distances[2])}};


				double[] a = {att[0][0],att[1][0],att[2][0]};
				
				if (a[1]==0 && a[2]==0) {
					
				}else if(a[1]==0){
					for(int m=0, n=0;n<4;n++){
						att[m][n]=att[m][n]*a[2];
					}
					for(int m=2, n=0;n<4;n++){
						att[m][n]=att[m][n]*a[0];
					}
				}else if(a[2]==0){
					for(int m=0, n=0;n<4;n++){
						att[m][n]=att[m][n]*a[1];
					}
					for(int m=1, n=0;n<4;n++){
						att[m][n]=att[m][n]*a[0];
					}
				}else {
					for(int m=0;m<3;m++){
						for(int n=0;n<4;n++){
							att[m][n]=att[m][n]*a[(m+1)%3]*a[(m+2)%3];
						}
					}
				}

				for(int m=1;m<3;m++){
					if (att[m][0]!=0) {
						for(int n=0;n<4;n++){
							att[m][n]-=att[0][n];	
						}
					}
				}
				
				a[1]=att[1][1];
				a[2]=att[2][1];

				if (a[2]!=0) {
					for(int n=0;n<4;n++){
						att[1][n]*=a[2];
						att[2][n]*=a[1];
					}

					for(int n=0;n<4;n++){
						att[2][n]-=att[1][n];
					}	
				}
				
				a[0]=att[0][2];
				a[1]=att[1][2];
				a[2]=att[2][2];
				
				if (a[0]==0 && a[1]==0) {
					
				}else if(a[0]==0){
					for(int m=1, n=0;n<4;n++){
						att[m][n]=att[m][n]*a[2];
					}
					for(int m=2, n=0;n<4;n++){
						att[m][n]=att[m][n]*a[1];
					}
				}else if(a[1]==0){
					for(int m=0, n=0;n<4;n++){
						att[m][n]=att[m][n]*a[2];
					}
					for(int m=2, n=0;n<4;n++){
						att[m][n]=att[m][n]*a[0];
					}
				}else {
					for(int m=0;m<3;m++){
						for(int n=0;n<4;n++){
							att[m][n]=att[m][n]*a[(m+1)%3]*a[(m+2)%3];
						}
					}
				}

				for(int m=0; m<2; m++){
					if (att[m][2]!=0) {
						for(int n=0;n<4;n++){
							att[m][n]-=att[2][n];	
						}
					}
				}


				a[0]=att[0][1];
				a[1]=att[1][1];
				
				if (a[0]!=0) {
					for(int n=0;n<4;n++){
						att[0][n]*=a[1];
						att[1][n]*=a[0];
					}

					for(int n=0;n<4;n++){
						att[0][n]-=att[1][n];
					}
					
				}
				position[0]=(att[0][3]/att[0][0]);
				position[1]=(att[1][3]/att[1][1]);
				position[2]=(att[2][3]/att[2][2]);
				
			}
		public int getPositionX(){
			return Integer.parseInt(String.valueOf(Math.round(position[0])));
		}

		public int getPositionY(){
			return Integer.parseInt(String.valueOf(Math.round(position[1])));
		}
}