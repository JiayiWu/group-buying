package com.mergeorder.presentation.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.mergeorder.MainActivity;
import com.mergeorder.R;
import com.mergeorder.controller.LoginController;

/**
 * Created by song on 17-4-23.
 * <p>
 * 登录界面
 */
public class LoginFragment extends Fragment {

    private View view;

    private MainActivity mainActivity;

    private LoginController loginController;

    private TextInputEditText et_phone;

    private TextInputEditText et_password;

    private Button btn_login;

    private Button btn_register;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);

        initUIComponents();
        addListeners();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.mainActivity = (MainActivity) getActivity();
        this.loginController = mainActivity.getLoginController();
    }

    private void initUIComponents() {
        et_phone = (TextInputEditText) view.findViewById(R.id.login_et_phoneNum);
        et_password = (TextInputEditText) view.findViewById(R.id.login_et_password);
        btn_login = (Button) view.findViewById(R.id.login_btn_login);
        btn_register = (Button) view.findViewById(R.id.login_btn_register);
    }

    private void addListeners() {
        btn_login.setOnClickListener(e -> onLogin());

        btn_register.setOnClickListener(e -> mainActivity.showFragmentTab(1));
    }

    private void onLogin() {
        String phoneNum = et_phone.getText().toString();
        String password = et_password.getText().toString();
//        String phoneNum = "13289150111";
//        String phoneNum = "18805158852";
//        String password = "test1234";

        loginController.login(phoneNum, password);
    }
}
