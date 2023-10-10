package com.example.gallery;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gallery.model.User;


public class LoginFragment extends Fragment {
    private User user=new User("user","root","1111");
    private EditText loginField;
    private EditText passwordField;

    private Button buttonLogin;
    private Button buttonSignup;
    public LoginFragment(){
        super(R.layout.fragment_login);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        setFields(view);
        setButtons();
        return view;
    }
    private void setFields(View view){
        loginField = view.findViewById(R.id.loginField);
        passwordField = view.findViewById(R.id.passwordField);

        buttonLogin = view.findViewById(R.id.buttonLogin);
        buttonSignup = view.findViewById(R.id.buttonSignup);
    }

    private void setButtons(){
        setLoginButton();
        setSignupButton();
    }
    private void setLoginButton() {

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputLogin = loginField.getText().toString();
                String inputPassword = passwordField.getText().toString();

                String errorMessage = checkInputData(inputLogin, inputPassword);

                if (errorMessage != null) {
                    Toast toast = Toast.makeText(getContext(),
                            errorMessage, Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Fragment gallery_fragment = GalleryFragment.getGalleryFragment();
                    Fragment selectImage_fragment = new SelectImageFragment();
                    FragmentManager fm = getParentFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment, gallery_fragment);
                    ft.replace(R.id.selectImage,selectImage_fragment);
                    ft.commit();
                }
            }
        });

    }

    private void setSignupButton() {
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SignupFragment();
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment, fragment);
                ft.commit();
            }});
    }
    private String checkInputData(String inputLogin, String inputPassword) {
        String text = null;

        if(!isEmptyField(inputLogin,inputPassword)) {
            if (!user.getLogin().equals(inputLogin)) {
                text = "Incorrect login!";
            } else if (!user.getPassword().equals(inputPassword)) {
                text = "Incorrect password!";
            }
        }
        else{
            text = "Enter data!";
        }

        return text;
    }
    private boolean isEmptyField(String inputLogin, String inputPassword){
        boolean result=false;
        if(inputLogin==null || inputLogin.isEmpty()){
            loginField.setError("Enter a login");
            result=true;
        }
        if(inputPassword==null || inputPassword.isEmpty()){
            passwordField.setError("Enter a password");
            result=true;
        }
        return result;
    }

    public void clickLogin(View view) {
        String inputLogin = loginField.getText().toString();
        String inputPassword = passwordField.getText().toString();

        String errorMessage = checkInputData(inputLogin, inputPassword);

        if (errorMessage != null) {
            Toast toast = Toast.makeText(getContext(),
                    errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Fragment fragment=GalleryFragment.getGalleryFragment();
            FragmentManager fm=getParentFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.frgmCont,fragment);
            ft.commit();
        }
    }


}