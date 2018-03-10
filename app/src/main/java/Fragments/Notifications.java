package Fragments;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhangfengwei.associationonline.Activities.ControlActivity;
import com.example.zhangfengwei.associationonline.Activities.DeclarationActivity;
import com.example.zhangfengwei.associationonline.Activities.ResetPasswordActivity;
import com.example.zhangfengwei.associationonline.R;


import Tools.httpUtilsforLogin.AndroidHttpClientUtils;

import static android.content.Context.MODE_PRIVATE;

public class Notifications extends Fragment {
    private TextView declare;
    private TextView resetpsd;
    private TextView login;
    private CheckBox cb;
    private EditText text_psd,text_usr;
    private String str_psd,str_usr;
    private ImageButton next;
    private String login_result;
    private int login_state=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =null;
        SharedPreferences pref = getActivity().getSharedPreferences("login",MODE_PRIVATE);
        login_state=pref.getInt("login_state",0);
        if(login_state==0){
            view=(inflater.inflate(R.layout.fragment_notifications1, container, false));
            declare=(TextView)view.findViewById(R.id.declaration);
            resetpsd=(TextView)view.findViewById(R.id.forget_psd);
            login=(TextView)view.findViewById(R.id.login);
            cb=(CheckBox)view.findViewById(R.id.check_declare);
            text_psd=(EditText)view.findViewById(R.id.psd);
            text_usr=(EditText)view.findViewById(R.id.usr);
            cb.setChecked(false);
            cb.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(cb.isChecked()){
                        cb.setChecked(true);
                    }
                    else{
                        cb.setChecked(false);
                    }
                }
            });
            declare.setClickable(true);
            resetpsd.setClickable(true);
            login.setClickable(false);
            declare.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mainIntent = new Intent(getActivity(),DeclarationActivity.class);
                    startActivityForResult(mainIntent,1);
                    getActivity().finish();
                }
            });
            resetpsd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mainIntent = new Intent(getActivity(),ResetPasswordActivity.class);
                    startActivity(mainIntent);
                    getActivity().finish();
                }
            });
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        str_psd=text_psd.getText().toString();
                        str_usr=text_usr.getText().toString();
                        Intent mainIntent = new Intent(getActivity(), ControlActivity.class);
                        if(str_psd.equals("")||str_usr.equals("")){
                            Toast.makeText(getActivity(),"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            if (cb.isChecked()) {
//                                new Thread(new AndroidHttpClientUtils(str_usr,str_psd)).start();
//                                login_result=AndroidHttpClientUtils.result_0;
                                login_result="true";
                                Toast.makeText(getActivity(),"请稍候......",Toast.LENGTH_SHORT).show();
                                if(login_result.equals("true")){
                                    SharedPreferences.Editor editor1 = getActivity().getSharedPreferences("login", MODE_PRIVATE).edit();
                                    editor1. putInt("login_state", 1);
                                    editor1.apply();
                                    Toast.makeText(getActivity(),"登录成功",Toast.LENGTH_SHORT).show();
                                    startActivity(mainIntent);
                                    getActivity().finish();
                                }
                                else if(login_result.equals("false")){
                                    SharedPreferences.Editor editor1 = getActivity().getSharedPreferences("login", MODE_PRIVATE).edit();
                                    editor1. putInt("login_state", 0);
                                    editor1.apply();
                                    Toast.makeText(getActivity(),"登录失败",Toast.LENGTH_SHORT).show();

                                }
                            }
                            else{
                                Toast.makeText(getActivity(),"请先阅读并同意《免责声明》！",Toast.LENGTH_SHORT).show();
                            }
                        }
                }
            });
        }
        else if(login_state==1){
            view=(inflater.inflate(R.layout.fragment_notifications2, container, false));
            next=(ImageButton)view.findViewById(R.id.welcome_next);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(),ControlActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            });
        }
        return view;
    }
}
