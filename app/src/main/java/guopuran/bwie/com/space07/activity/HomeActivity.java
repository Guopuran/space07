package guopuran.bwie.com.space07.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import guopuran.bwie.com.space07.R;
import guopuran.bwie.com.space07.adapter.FragAdapter;

public class HomeActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        //获取资源ID
        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewpager);
        FragAdapter fragAdapter=new FragAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragAdapter);
        //根据viewPager来设置tabLayout
        tabLayout.setupWithViewPager(viewPager);
        Intent intent=getIntent();
        name = intent.getStringExtra("name");
    }
    public String QRCname(){
        return name;
    }
}
