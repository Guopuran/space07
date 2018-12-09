package guopuran.bwie.com.space07.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import guopuran.bwie.com.space07.R;
import guopuran.bwie.com.space07.bean.NextBean;
import guopuran.bwie.com.space07.presenter.Ipresenter;
import guopuran.bwie.com.space07.presenter.IpresenterImpl;
import guopuran.bwie.com.space07.util.RegUtil;
import guopuran.bwie.com.space07.view.IView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IView {
    private String url="http://www.zhaoapi.cn/user/login?mobile=%s&password=%s";
    private IpresenterImpl mIpresenterImpl;
    private EditText main_edit_name;
    private EditText main_edit_pass;
    private Button main_button_next;
    private Button main_button_reg;
    private Button main_button_san;
    private CheckBox main_box_rem;
    private CheckBox main_box_self;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        main_edit_name = findViewById(R.id.main_edit_name);
        main_edit_pass = findViewById(R.id.main_edit_pass);
        main_button_next = findViewById(R.id.main_button_next);
        main_button_reg = findViewById(R.id.main_button_reg);
        main_button_san = findViewById(R.id.main_button_san);
        main_box_rem = findViewById(R.id.main_box_rem);
        main_box_self = findViewById(R.id.main_box_self);
        sharedPreferences=getSharedPreferences("User",MODE_PRIVATE);
        edit = sharedPreferences.edit();
        main_button_next.setOnClickListener(this);
        main_button_reg.setOnClickListener(this);
        main_button_san.setOnClickListener(this);
        main_edit_pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                main_button_next.setEnabled((s.length()==6));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        boolean jizhu_tai = sharedPreferences.getBoolean("jizhu_tai", false);
        if (jizhu_tai){
            String nname = sharedPreferences.getString("name", null);
            String ppass = sharedPreferences.getString("pass", null);
            main_edit_name.setText(nname);
            main_edit_pass.setText(ppass);
            main_box_rem.setChecked(true);
        }
        boolean zidong_tai = sharedPreferences.getBoolean("zidong_tai", false);
        if (zidong_tai){
            String name = main_edit_name.getText().toString();
            String pass = main_edit_pass.getText().toString();
            mIpresenterImpl.requestpre(String.format(url,name,pass),null,NextBean.class);

        }
        main_box_self.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    main_box_rem.setChecked(true);
                }else{
                    edit.clear();
                    edit.commit();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.main_button_next:
                String name = main_edit_name.getText().toString();
                String pass = main_edit_pass.getText().toString();
                if (!(RegUtil.isPhone(name))){
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return ;
                }
                if (!(RegUtil.isPhone(name))){
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return ;
                }
                if (main_box_rem.isChecked()){
                    edit.putString("name",name);
                    edit.putString("pass",pass);
                    edit.putBoolean("jizhu_tai",true);
                    edit.commit();
                }else{
                    edit.clear();
                    edit.commit();
                }
                if (main_box_self.isChecked()){
                    edit.putBoolean("zidong_tai",true);
                    edit.commit();
                }
                mIpresenterImpl.requestpre(String.format(url,name,pass),null,NextBean.class);

                break;
            case R.id.main_button_reg:
                startActivity(new Intent(MainActivity.this,RegActivity.class));
                finish();
                break;
            case R.id.main_button_san:
                UMShareAPI umShareAPI=UMShareAPI.get(MainActivity.this);
                umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        String name1 = map.get("name");
                        Intent intent=new Intent(MainActivity.this, HomeActivity.class);
                        intent.putExtra("name",name1);
                        startActivity(intent);


                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                });
            default:
                break;
        }
    }

    @Override
    public void getdata(Object object) {
        if (!(object instanceof NextBean)){
            return;
        }
        NextBean bean= (NextBean) object;
        if (bean.getCode().equals("0")){
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, bean.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(MainActivity.this).onActivityResult(requestCode,resultCode,data);
    }
}
