package com.mergeorder.presentation.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.koushikdutta.ion.Ion;
import com.mergeorder.MainActivity;
import com.mergeorder.R;
import com.mergeorder.util.HttpUtil;
import com.mergeorder.util.PhoneUtil;
import org.json.JSONObject;

/**
 * Created by song on 17-4-23.
 * <p>
 * 注册界面
 */
public class RegisterFragment extends Fragment {

    private View view;

    private MainActivity mainActivity;

    private EditText et_phone, et_password, et_password_repeat;

    private Button btn_login, btn_register;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register, container, false);

        initUIComponents();
        addListeners();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.mainActivity = ((MainActivity) getActivity());
    }

    private void initUIComponents() {
        et_phone = (EditText) view.findViewById(R.id.register_et_phoneNum);
        et_password = (EditText) view.findViewById(R.id.register_et_password);
        et_password_repeat = (EditText) view.findViewById(R.id.register_et_password_repeat);

        btn_login = (Button) view.findViewById(R.id.register_btn_login);
        btn_register = (Button) view.findViewById(R.id.register_btn_register);
    }

    private void addListeners() {
        btn_login.setOnClickListener(e -> mainActivity.showFragmentTab(0));

        btn_register.setOnClickListener(e -> onRegister());
    }

    private void onRegister() {
        String phoneNum = et_phone.getText().toString();
        String password = et_password.getText().toString();
        String passwordRepeat = et_password_repeat.getText().toString();

        if (phoneNum.length() != 11 || !PhoneUtil.isLegal(phoneNum)) {
            Toast.makeText(mainActivity, "手机号错误", Toast.LENGTH_LONG).show();
            return;
        }

        if (!password.equals(passwordRepeat)) {
            Toast.makeText(mainActivity, "密码不一致", Toast.LENGTH_LONG).show();
            return;
        }

        String url = "/register";

        Ion.with(mainActivity)
                .load(HttpUtil.BASE_URL + url)
                .setBodyParameter("phoneNumber", phoneNum)
                .setBodyParameter("password", password)
                .asString()
                .setCallback((e, result) -> {
                    if (result != null) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);

                            if (jsonObject.getBoolean("result")) {
                                // 跳至登录
                                mainActivity.showFragmentTab(0);
                            } else {
                                Toast.makeText(mainActivity, jsonObject.getString("reason"), Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                });
    }
}
