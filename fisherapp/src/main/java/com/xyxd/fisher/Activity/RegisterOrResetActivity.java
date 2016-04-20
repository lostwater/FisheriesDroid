package com.xyxd.fisher.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xyxd.fisher.Http.AccessToken;
import com.xyxd.fisher.Http.Client;
import com.xyxd.fisher.R;
import com.xyxd.fisher.model.LoginError;
import com.xyxd.fisher.model.LoginErrorUtils;
import com.xyxd.fisher.model.ServerError;
import com.xyxd.fisher.model.ServerErrorUtils;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterOrResetActivity extends AppCompatActivity  {
    // 0 for register, 1 for reset
    int mode = 0;
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;
    private RegisterOrResetTask mRoRTask = null;

    Handler toastHandler = new Handler();
    String errorMessage;

    private final Handler msgHandler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.arg1) {
                case R.string.wrong_password:
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.wrong_password), Toast.LENGTH_SHORT).show();
                    break;
                case R.string.network_error:
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    private static String APPKEY = "10141f2a0a77c";
    private static String APPSECRET = "e9d0487f02a44d28b842aaaf54bbe24f";

    // UI references.
    private EditText mCodeView;
    private EditText mPhoneView;
    private EditText mPasswordView;
    private EditText mConfirmPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    Button mSendCodeButton;
    Button mRegisterOrResetButton;

    EventHandler eh=new EventHandler(){
        @Override
        public void afterEvent(int event, int result, Object data) {
            toastHandler.post(new Runnable() {
                @Override
                public void run() {
                    mSendCodeButton.setEnabled(true);
                }
            });

            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    toastHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "发送成功", Toast.LENGTH_SHORT).show();
                            mSendCodeButton.setText("重新发送");
                        }
                    });

                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                    //返回支持发送验证码的国家列表
                }
            }else{
                ((Throwable)data).printStackTrace();
                toastHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "请求失败", Toast.LENGTH_SHORT).show();
                        mSendCodeButton.setText("重新发送");
                    }
                });
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_or_reset);
        SMSSDK.initSDK(this, APPKEY, APPSECRET);
        SMSSDK.registerEventHandler(eh);
        mode = (int)(getIntent().getExtras().get("mode"));
        // Set up the login form.
        mPhoneView = (EditText) findViewById(R.id.phone);
        mPasswordView = (EditText) findViewById(R.id.password);
        mConfirmPasswordView  = (EditText) findViewById(R.id.cofirmPassword);
        mCodeView = (EditText) findViewById(R.id.validCode);
        mRegisterOrResetButton = (Button) findViewById(R.id.registerOrReset);
        mRegisterOrResetButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegisterOrReset();
            }
        });

        mSendCodeButton = (Button)findViewById(R.id.sendCode);
        mSendCodeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = mPhoneView.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    mPhoneView.setError("请输入手机号");
                    mPhoneView.requestFocus();
                }else if (!isPhoneValid(phone)) {
                    mPhoneView.setError("手机号不正确");
                    mPhoneView.requestFocus();
                }
                else {
                    mSendCodeButton.setEnabled(false);
                    SMSSDK.getVerificationCode("86", phone);
                }

            }
        });
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SMSSDK.unregisterAllEventHandler();
    }

    void attemptRegisterOrReset()
    {
        mRegisterOrResetButton.setEnabled(false);
        // Reset errors.
        mPhoneView.setError(null);
        mPasswordView.setError(null);
        mConfirmPasswordView.setError(null);
        mCodeView.setError(null);

        // Store values at the time of the login attempt.
        String phone = mPhoneView.getText().toString();
        String password = mPasswordView.getText().toString();
        String confirmPassword = mConfirmPasswordView.getText().toString();
        String code = mCodeView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(code)) {
            mCodeView.setError("请输验证码");
            focusView = mCodeView;
            cancel = true;
        }
        else if (TextUtils.isEmpty(phone)) {
            mPhoneView.setError("请输入手机号");
            focusView = mPhoneView;
            cancel = true;
        }
        else if (!isPhoneValid(phone)) {
            mPhoneView.setError("手机号不正确");
            focusView = mPhoneView;
            cancel = true;
        }

        if (!confirmPassword.equals(password)) {
            mConfirmPasswordView.setError("密码不匹配");
            focusView = mConfirmPasswordView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            mRegisterOrResetButton.setEnabled(true);
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mRoRTask = new RegisterOrResetTask(code,phone,password);
            mRoRTask.execute((Void) null);
        }
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mPhoneView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String phone = mPhoneView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(phone)) {
            mPhoneView.setError("请输入手机号");
            focusView = mPhoneView;
            cancel = true;
        } else if (!isPhoneValid(phone)) {
            // mPhoneView.setError("手机号不正确");
            //focusView = mPhoneView;
            //cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(phone, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isPhoneValid(String phone) {
        //TODO: Replace this with your own logic
        return phone.length() == 11;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 0;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    void   doRegisterOrReset(String code, String phone, String password) {
        final String mCode = code;
        final String mPhone = phone;
        final String mPassword = password;
        boolean result = false;
        result = false;
        showProgress(true);
        Call<Object> call;
        if(mode == 0)
            call = Client.LoginService().register(mPhone, mPassword, mPassword, mCode);
        else
            call = Client.LoginService().resetPassword(mPhone, mPassword, mPassword, mCode);
        call.enqueue(new Callback<Object>()
        {
            @Override
            public void onResponse(Response<Object> response) {
                if(response.isSuccess())
                {
                    String FILE = "saveUserNamePwd";
                    SharedPreferences sp = getSharedPreferences(FILE, MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("phone", mPhone);
                    edit.putString("password", mPassword);
                    edit.putString("isMemory", "yes");
                    edit.apply();
                }
                else
                {
                    ServerError error = ServerErrorUtils.parseError(response);
                    errorMessage = error.message;
                    toastHandler.post(new Runnable() {
                        @Override
                        public void run() {Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();}
                    });
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Message msg = msgHandler.obtainMessage();
                msg.arg1 = R.string.network_error;
                msgHandler.sendMessage(msg);
            }
        });
    }

    public class RegisterOrResetTask extends AsyncTask<Void, Void, Boolean> {
        private final String mCode;
        private final String mPhone;
        private final String mPassword;
        boolean result = false;

        RegisterOrResetTask(String code, String phone, String password) {
            mCode = code;
            mPhone = phone;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            result = false;
            final String strSuccessed;
            Response response;
            Call<Object> call;
            if(mode == 0) {
                strSuccessed = "注册成功";
                call = Client.LoginService().register(mPhone, mPassword, mPassword, mCode);
            }
            else {
                strSuccessed = "重置成功";
                call = Client.LoginService().resetPassword(mPhone, mPassword, mPassword, mCode);
            }

            try{
                response = call.execute();
                result = response.isSuccess();
                if(result)
                {
                    toastHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), strSuccessed, Toast.LENGTH_SHORT).show();
                        }});

                     String FILE = "saveUserNamePwd";
                     SharedPreferences sp = getSharedPreferences(FILE, MODE_PRIVATE);
                     SharedPreferences.Editor edit = sp.edit();
                     edit.putString("phone", mPhone);
                     edit.putString("password", mPassword);
                     edit.putString("isMemory", "yes");
                     edit.apply();
                  }
                else
                {
                    final String message = ServerErrorUtils.parseError(response).message;
                     toastHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                         }});
                 };
            }
            catch (final Exception ex) {
                toastHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "网络错误" + ex.toString(), Toast.LENGTH_SHORT).show();
                    }});
                return false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);
            mRegisterOrResetButton.setEnabled(true);
            if (success) {
                attemptLogin();
            } else {
                mPhoneView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
            mRegisterOrResetButton.setEnabled(true);
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mPhone;
        private final String mPassword;

        UserLoginTask(String phone, String password) {
            mPhone = phone;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            Boolean result = false;
            Response response;
            try {
                response = Client.LoginService().getAccessToken(mPhone, mPassword, "password").execute();
                result = response.isSuccess();
                if(result)
                {
                    Client.accessToken = (AccessToken)response.body();
                    Client.TryGetUserDetail();
                    String FILE = "saveUserNamePwd";
                    SharedPreferences sp = getSharedPreferences(FILE, MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("phone", mPhone);
                    edit.putString("password", mPassword);
                    edit.putString("isMemory", "yes");
                    edit.apply();
                }
                else
                {
                    LoginError error = LoginErrorUtils.parseError(response);
                    errorMessage = error.error_description;
                    toastHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    });
                };
            }
            catch (Exception ex) {
                Message msg = msgHandler.obtainMessage();
                msg.arg1 = R.string.network_error;
                msgHandler.sendMessage(msg);
                return false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);
            mRegisterOrResetButton.setEnabled(true);
            if (success) {
                finish();
            } else {
                mPasswordView.setError(errorMessage==null ? "账户或密码错误" :errorMessage );
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
            mRegisterOrResetButton.setEnabled(true);
        }
    }
}

