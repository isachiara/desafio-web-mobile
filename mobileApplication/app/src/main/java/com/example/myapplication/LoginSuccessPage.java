package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginSuccessPage extends Fragment {

    private String namePerson;
    private String lastNamePerson;
    TextView pageTitle;

    public LoginSuccessPage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.login_success_page, container, false);
        String jsonStr = this.getArguments().getString("jsonStr");
        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj = new JSONObject(jsonStr);

            namePerson = jsonObj.getString("name_person");
            lastNamePerson = jsonObj.getString("lastname_person");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        pageTitle = (TextView) rootView.findViewById(R.id.titlePageLoginSucces);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        pageTitle.setText("Bem Vindo " + namePerson + " " + lastNamePerson + "!");
        //TODO: imprimir lista de pessoas do banco
    }
}