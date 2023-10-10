package com.example.gallery;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gallery.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupFragment extends Fragment {
    private EditText loginField;
    private EditText emailField;
    private EditText passwordField;
    private EditText passwordConfirmField;

    private static boolean isIsFacebook = false;
    private final String EMAIL_PATTERN =
            "^(?=.{1,27}@)[A-Za-z]+[A-Za-z0-9_]+@[A-Za-z0-9_]*(\\.[A-Za-z]{2,})$";

    private final String PASSWORD_PATTERN = "(?=.{8,}).*[0-9].*[A-Z].*";
    private final String SPECIAL_SYMBOLS = "@#$%^&*()_+-*/~№:!?.";

    private static boolean isFacebook = false;

    public SignupFragment(){
        super(R.layout.fragment_signup);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        setFields(view);

        return view;
    }

    private void setFields(View view){

        loginField = view.findViewById(R.id.loginField);
        emailField = view.findViewById(R.id.emailField);
        passwordField = view.findViewById(R.id.passwordField);
        passwordConfirmField = view.findViewById(R.id.passwordConfirmField);

    }

    public void clickConfirm(View view) {
        String login = loginField.getText().toString();
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        String passwordConfirm = passwordConfirmField.getText().toString();

        if (checkInputData(email, password, passwordConfirm)) {
            User user = new User(login, email, password);
            returnToLogin(user);

        }
    }


    private void returnToLogin(User user) {

        Toast toast = Toast.makeText(getContext(),
                "Signup is success!", Toast.LENGTH_SHORT);
        toast.show();

        Fragment fragment = new LoginFragment();
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment, fragment);
        ft.commit();
    }

    private boolean checkInputData(String email,
                                   String password, String passwordConfirm) {

        boolean result = true;

        if (!isValidMail(email)) {
            emailField.setError("Incorrect email");
            result = false;
        }
        if (!isValidPassword(password)) {
            passwordField.setError("Incorrect password");
            result = false;
        }
        if (!passwordsComparison(password, passwordConfirm)) {
            passwordConfirmField.setError("Passwords don't match");
            result = false;
        }

        return result;
    }

    private boolean isValidMail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean passwordsComparison(String password, String passwordConfirm) {
        return password.equals(passwordConfirm);
    }

    private boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

        Matcher matcher = pattern.matcher(password);

        return matcher.matches()
                && specialSmlContains(password);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private boolean specialSmlContains(String password) {
        long count = 0l;
        for (char ch : SPECIAL_SYMBOLS.toCharArray()) {
            count += password.chars().filter(x -> ch == x).count();
        }
        if (count == 1) return true;
        return false;
    }

}
