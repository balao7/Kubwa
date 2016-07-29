package com.wesleyjasonelliott.kubwa;

import android.content.Context;
import android.databinding.BaseObservable;
import android.util.Log;
import android.view.View;

import com.wesleyelliott.kubwa.R;
import com.wesleyjasonelliott.kubwa.annotation.Email;
import com.wesleyjasonelliott.kubwa.annotation.Password;


/**
 * Created by wesley on 2016/07/28.
 */
@Email(name = "emailError", errorMessage = R.string.email_error)
@Password(name = "passwordError", errorMessage = R.string.password_error)
public class LoginViewModel extends BaseObservable {

    private String email;
    private String password;
    LoginViewModelValidator validator;

    public LoginViewModel(Context context) {
        validator = new LoginViewModelValidator(context);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public View.OnClickListener onLoginClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    login();
                } else {
                    Log.d("TEST", "ERRORS!");
                    notifyChange();
                }
            }
        };
    }

    private void login() {
        Log.d("TEST", "NO ERRORS!");
    }

    public String getEmailError() {
        return validator.getEmailErrorMessage();
    }

    public String getPasswordError() {
        return validator.getPasswordErrorMessage();
    }

    private boolean validate() {
        validator.validateAll(getEmail(), getPassword());

        return validator.isValid();
    }
}
