package com.example.pc.chatapplication;

import android.content.Context;
import android.os.AsyncTask;

/*
 * We only need the doInBackground method for this class.
 * (and onPostExecute on some occasions)
 * (may write additional methods for networking and other tasks)
 * That's why the other methods are defined as empty in this class.
 */
abstract class BackgroundTask extends AsyncTask<String, Void, String> {
    Context ctx;

    // String variables that internally define where the server is.
    final String SERVER_IP_ADDRESS = "http://172.31.82.93/";
    String phpFileUrl;

    @Override
    protected void onPreExecute() {}

    @Override
    protected abstract String doInBackground(String...params);

    @Override
    protected void onProgressUpdate(Void... values) {}

    @Override
    protected abstract void onPostExecute(String result);
}
