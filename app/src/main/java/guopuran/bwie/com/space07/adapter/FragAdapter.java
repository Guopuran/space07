package guopuran.bwie.com.space07.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import guopuran.bwie.com.space07.fragment.FragmentMe;
import guopuran.bwie.com.space07.fragment.FragmentOne;

public class FragAdapter extends FragmentPagerAdapter {
    private String[] strings=new String[]{
            "首页","我的"
    };
    public FragAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new FragmentOne();
            case 1:
                return new FragmentMe();
            default:break;
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strings[position];
    }

    @Override
    public int getCount() {
        return strings.length;
    }
}
