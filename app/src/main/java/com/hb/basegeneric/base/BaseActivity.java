package com.hb.basegeneric.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Administrator
 * on 2020/5/14
 */
public abstract class BaseActivity<V, T extends BasePresenterImpl<V>> extends AppCompatActivity {
    public T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        setAppBar();
        initIntent();
        initView();
        setPresenter();
    }

    protected abstract int getContentViewId();

    protected abstract void setAppBar();

    protected abstract void initIntent();

    protected abstract void initView();

    protected abstract T createPresenter();

    protected void setPresenter() {
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    @Override
    protected void onDestroy() {
        if (null != mPresenter) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }
}
