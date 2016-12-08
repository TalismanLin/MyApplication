package com.asiainfo.myapplication.common;

public interface GenericTaskListener {

	public void doInBackground();

	public void onPostExecute();

	public void onPreExecute();

	public void onCancelled();
}