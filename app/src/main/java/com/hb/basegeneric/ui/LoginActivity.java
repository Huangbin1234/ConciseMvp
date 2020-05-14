package com.hb.basegeneric.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.hb.basegeneric.MainActivity;
import com.hb.basegeneric.R;
import com.hb.basegeneric.base.BaseActivity;
import com.hb.basegeneric.ui.bean.User;
import com.hb.basegeneric.ui.contract.LoginContract;
import com.hb.basegeneric.ui.presenter.LoginPresenterImpl;

public class LoginActivity extends BaseActivity<LoginContract.View, LoginPresenterImpl> implements LoginContract.View, View.OnClickListener {
    private LoginPresenterImpl mPresenter;
    private EditText etName, etPsd;
    private Button button;
    public ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDialog();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    protected void initDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
    }

    @Override
    protected void setAppBar() {

    }

    @Override
    protected void initIntent() {

    }


    @Override
    protected void initView() {
        etName = findViewById(R.id.et_uname);
        etPsd = findViewById(R.id.et_upsd);
        button = findViewById(R.id.button);

        button.setOnClickListener(this);
    }

    @Override
    protected LoginPresenterImpl createPresenter() {
        mPresenter = new LoginPresenterImpl();
        return mPresenter;
    }


    @Override
    public void showLoading() {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onSuccess(User user) {
        Toast.makeText(this, user.getRealName(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onFail(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkUserEmpty() {
        Toast.makeText(this, getResources().getString(R.string.str_user_empty), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkPsdEmpty() {
        Toast.makeText(this, getResources().getString(R.string.str_psd_empty), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkPsdLength() {
        Toast.makeText(this, getResources().getString(R.string.str_psd_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                String uName = etName.getText().toString().trim();
                String uPsd = etPsd.getText().toString().trim();
                mPresenter.login(uName, uPsd);
                break;
        }
    }
}
