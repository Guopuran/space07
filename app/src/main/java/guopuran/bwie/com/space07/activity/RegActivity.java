package guopuran.bwie.com.space07.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import guopuran.bwie.com.space07.R;
import guopuran.bwie.com.space07.bean.RegisterBean;
import guopuran.bwie.com.space07.presenter.IpresenterImpl;
import guopuran.bwie.com.space07.util.RegUtil;
import guopuran.bwie.com.space07.view.IView;

public class RegActivity extends AppCompatActivity implements IView {
    private String url="http://www.zhaoapi.cn/user/reg?mobile=%s&password=%s";
    private IpresenterImpl mIpresenterImpl;
    private EditText reg_edit_name;
    private EditText reg_edit_pass;
    private Button reg_button_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initPresenter();
        intiView();
    }


    //互绑
    private void initPresenter() {
        mIpresenterImpl=new IpresenterImpl(this);
    }
    //解绑

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIpresenterImpl.deatch();
    }
    private void intiView() {
        reg_edit_name = findViewById(R.id.reg_edit_name);
        reg_edit_pass = findViewById(R.id.reg_edit_pass);
        reg_button_next = findViewById(R.id.reg_button_next);
        reg_edit_pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                reg_button_next.setEnabled((s.length()==6));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //点击按钮进行注册
        reg_button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = reg_edit_name.getText().toString();
                String pass = reg_edit_pass.getText().toString();
                if (!(RegUtil.isPhone(name))){
                    Toast.makeText(RegActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }
                mIpresenterImpl.requestpre(String.format(url,name,pass),null,RegisterBean.class);
            }
        });
    }

    @Override
    public void getdata(Object object) {
//判断是否当前的bean类
        if(!(object instanceof RegisterBean)){
            return ;
        }
        RegisterBean bean= (RegisterBean) object;
        //判断是否注册成功
        if (bean.getCode().equals("0")){
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegActivity.this,MainActivity.class));
            finish();
        }else {
            Toast.makeText(this, bean.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }
}
