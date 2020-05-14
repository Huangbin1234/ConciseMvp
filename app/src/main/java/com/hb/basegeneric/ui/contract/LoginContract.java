package com.hb.basegeneric.ui.contract;

import com.hb.basegeneric.ui.bean.User;

/**
 * Created by Administrator
 * on 2020/5/14
 */
public interface LoginContract {
    interface View  {
        void showLoading();
        void hideLoading();
        void onSuccess(User user);
        void onFail(Throwable throwable);

        void checkUserEmpty();

        void checkPsdEmpty();

        void checkPsdLength();
    }

    interface Presenter  {
        void login(String uName,String uPsd);
    }
}
