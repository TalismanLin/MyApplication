package com.asiainfo.myapplication.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

public class GenericTask extends AsyncTask<Object, Object, Object> {

    private GenericTaskListener taskListener;
    private Context ctx;
    private ProgressDialog mProgressDialog;
    private Boolean showProgress;
    
    public GenericTask(Context context, Boolean showProgress) {
        this.ctx = context;
        this.showProgress = showProgress;
    }

    public void setBaseAsyncTaskListener(GenericTaskListener taskListener) {
        this.taskListener = taskListener;
    }

    public void initLoadingDialog(String loadingMsg) {
        mProgressDialog = new ProgressDialog(ctx);
        mProgressDialog.setMessage(loadingMsg);
        mProgressDialog.setCancelable(true);
        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface arg0) {
                cancel(true);
            }
        });
    }
    
    public void execute() {
        super.execute(null, null, null);
    }

    @Override
    protected Object doInBackground(Object... params) {
        taskListener.doInBackground();
        return null;
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        if (showProgress) {
            mProgressDialog.dismiss();
        }
        taskListener.onPostExecute();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (showProgress) {
            mProgressDialog.show();
        }
        taskListener.onPreExecute();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        taskListener.onCancelled();
    }
}