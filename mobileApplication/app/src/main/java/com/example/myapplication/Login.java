package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends Fragment implements View.OnClickListener{

    private String loginUrl = "http://10.0.2.2:8000/login";
    private EditText emailEditText;
    private EditText passwordEditText;

    private ProgressDialog load;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.login, container, false);

        Button btnLogin = (Button) rootView.findViewById(R.id.btnLogin);
        emailEditText = (EditText) rootView.findViewById(R.id.editEmail);
        passwordEditText = (EditText) rootView.findViewById(R.id.editPassword);

        btnLogin.setOnClickListener(this);

        return rootView;
    }

    private class LoginTask extends AsyncTask<Void, Void, JSONObject> {

        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(getContext(),
                    "Por favor Aguarde ...", "Recuperando Informações do Servidor...");
        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            ConnectionUtil connectionUtil = new ConnectionUtil();
            JSONObject jsonRequest = new JSONObject();

            try {
                jsonRequest.put("email", emailEditText.getText().toString());
                jsonRequest.put("pass", passwordEditText.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return connectionUtil.getInformacao(loginUrl, jsonRequest);
        }

        @Override
        protected void onPostExecute(JSONObject response){
            try {
                if(response.get("result").equals("ok")){
                    load.dismiss();
                    loginSuccessPage(response);
                }else{
                    Toast.makeText(getContext(), response.getString("erro"), Toast.LENGTH_LONG).show();
                    load.dismiss();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private Boolean isEmptyEditTexts(){
        String email_str = emailEditText.getText().toString();
        String password_str = passwordEditText.getText().toString();

        if(email_str.isEmpty() || password_str.isEmpty()){
            return true;
        }

        return false;
    }

    public void LogButtonClick()
    {
        if (!isEmptyEditTexts()){
            LoginTask loginTask = new LoginTask();
            loginTask.execute();
        }
    }

    public void loginSuccessPage(JSONObject personJson)
    {
        Bundle bundle = new Bundle();
        bundle.putString("jsonStr", personJson.toString());
        Fragment fragment = null;
        fragment = new LoginSuccessPage();
        fragment.setArguments(bundle);
        replaceFragment(fragment);
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogin) {
            LogButtonClick();
        }
    }
}
