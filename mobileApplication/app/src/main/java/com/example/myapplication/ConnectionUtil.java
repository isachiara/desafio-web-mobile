package com.example.myapplication;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionUtil {

    public static String sendRequest(String url, JSONObject jsonRequest){
        String retorno = "";
        try {
            URL apiEnd = null;
            try {
                apiEnd = new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            int codigoResposta;
            HttpURLConnection conexao;
            InputStream is;

            conexao = (HttpURLConnection) apiEnd.openConnection();
            conexao.setRequestMethod("POST");
            conexao.setReadTimeout(15000);
            conexao.setConnectTimeout(15000);
            OutputStream os = conexao.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(jsonRequest.toString());
            osw.flush();
            osw.close();
            os.close();  //don't forget to close the OutputStream
            conexao.connect();

            codigoResposta = conexao.getResponseCode();
            if(codigoResposta < HttpURLConnection.HTTP_BAD_REQUEST){
                is = conexao.getInputStream();
            }else{
                is = conexao.getErrorStream();
            }

            retorno = converterInputStreamToString(is);
            is.close();
            conexao.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return retorno;
    }

    private static String converterInputStreamToString(InputStream is){
        StringBuffer buffer = new StringBuffer();
        try{
            BufferedReader br;
            String linha;

            br = new BufferedReader(new InputStreamReader(is));
            while((linha = br.readLine())!=null){
                buffer.append(linha);
            }

            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return buffer.toString();
    }

    public static JSONObject getInformacao(String end, JSONObject json){

        String response = sendRequest(end, json);

        JSONObject personJsonResponse = parsePersonJson(response);

        return personJsonResponse;
    }

    private static JSONObject parsePersonJson(String json){
        try {
            JSONObject jsonObj = new JSONObject(json);

            return jsonObj;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }


}