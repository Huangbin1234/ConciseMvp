package com.hb.basegeneric.ui.presenter;

import android.text.TextUtils;

import com.hb.basegeneric.base.BasePresenterImpl;
import com.hb.basegeneric.ui.bean.User;
import com.hb.basegeneric.ui.contract.LoginContract;

/**
 * Created by Administrator
 * on 2020/5/14
 */
public class LoginPresenterImpl extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter {

    @Override
    public void login(String uName, String uPsd) {
        checkViewAttached();
        //TODO 具体的逻辑业务
        if (TextUtils.isEmpty(uName)) {
            getView().checkUserEmpty();
            return;
        }
        if (TextUtils.isEmpty(uPsd)) {
            getView().checkPsdEmpty();
            return;
        }
        if (uPsd.length() > 18 || uPsd.length() < 6) {
            getView().checkPsdLength();
            return;
        }
        getView().showLoading();
        if (uName.equals("james") && uPsd.equals("111111")) {
            if (isViewAttached()) {
                User user = new User();
                user.setId("001");
                user.setRealName("张三");
                user.setNickName("如风");
                user.setAge(18);
                getView().hideLoading();
                getView().onSuccess(user);
            }
        } else {
            if (isViewAttached()) {
                getView().hideLoading();
                getView().onFail(new Throwable("fail"));
            }
        }
    }

}
