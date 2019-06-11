package com.example.pc.chatapplication;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundReceiveMessageTask extends BackgroundTask {
    BackgroundReceiveMessageTask(Context ctx) {
        this.ctx = ctx;
        this.phpFileUrl = SERVER_IP_ADDRESS + "receivemessage.php";
    }

    @Override
    protected String doInBackground(String... params) {
        String chatRoomId = params[1];
        try {
            URL url = new URL(phpFileUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
            String data = URLEncoder.encode("chatroomID", "UTF-8") + "=" + URLEncoder.encode(chatRoomId,"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            String result = "", line;
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            String[] messages = result.split("<br>");
            String allmessages = "";
            for (String message : messages) {
                allmessages += message + "\n";
            }
            return allmessages;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
    }
}
