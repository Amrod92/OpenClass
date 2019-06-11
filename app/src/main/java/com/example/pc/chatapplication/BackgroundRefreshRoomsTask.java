package com.example.pc.chatapplication;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BackgroundRefreshRoomsTask extends BackgroundTask {
    BackgroundRefreshRoomsTask(Context ctx) {
        this.ctx = ctx;
        this.phpFileUrl = SERVER_IP_ADDRESS + "listchatrooms.php";
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(phpFileUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoOutput(true);
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            String result = "", line;
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            String[] rooms = result.split("<br>");
            String allRooms = "";
            for (String room : rooms) allRooms += room + " ";
            return allRooms;
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
