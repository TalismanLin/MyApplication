/**
 * Package:com.jialin
 * Author:Zhu JL
 * Date:2015��10��25��
 */
package com.asiainfo.myapplication;


import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

import com.asiainfo.myapplication.common.Const;
import com.asiainfo.myapplication.handler.CrashHandler;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhu JL
 *
 */
/**
 * @author Zhu JL
 *
 */
public class MyApplication extends Application{
	
	private List<Activity> activityList;
	
	private SharedPreferences spFile;

	private CrashHandler crashHandler;
	
	/* 
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		CrashHandler crashHandler =CrashHandler.getInstance();
		crashHandler.init(getApplicationContext());
		if(activityList == null){
			activityList = new ArrayList<Activity>();
		}
		spFile = getSharedPreferences(Const.SP_FILE_NAME, MODE_PRIVATE);
		
	}
	
	
	public void pushActivity(Activity newActivity){
		activityList.add(newActivity);
	}
	
	public void popActivity(){
		if(activityList.size() != 0){
			Activity lastActivity = activityList.get(activityList.size() - 1);
			lastActivity.finish();
			activityList.remove(activityList.size() - 1);
		}
	}
	
	public void removeAllActiivty(){
		if( activityList == null){
			return;
		}
		int size = activityList.size();
		for(int i = size ; i > 0 ; i--){
			activityList.remove(i);
		}
	}
	
	public void finishAllActivity(){
		for(Activity activity:activityList){
			if( !activity.isFinishing()){
				activity.finish();
			}
		}
		removeAllActiivty();
	}
	
	
	public SharedPreferences getSPFile(){
		if(this.spFile == null){
			this.spFile = getSharedPreferences(Const.SP_FILE_NAME, MODE_PRIVATE);
		}
		return this.spFile;
	}
	
	public void setLocalData(String setting, String value){
		
		SharedPreferences.Editor editor = this.getSPFile().edit();
		editor.putString(setting, value);
		editor.commit();
	}
	
	public String getLocalData(String setting,String defValue){
		
		return getSPFile().getString(setting, defValue);
	}
	
	public String getKeyPair(){
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("", "AndroidKeyStore");
			keyPairGenerator.initialize(new AlgorithmParameterSpec() {
			});
			
			KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
			return keyStore.toString();
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * @return
	 */
	public Object getLocalData() {
		// TODO Auto-generated method stub
		return null;
	}
}
