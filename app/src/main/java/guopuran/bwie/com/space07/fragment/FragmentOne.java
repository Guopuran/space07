package guopuran.bwie.com.space07.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

import guopuran.bwie.com.space07.R;
import guopuran.bwie.com.space07.adapter.ListAdapter;
import guopuran.bwie.com.space07.bean.ImageBean;
import guopuran.bwie.com.space07.bean.ListBean;
import guopuran.bwie.com.space07.presenter.IpresenterImpl;
import guopuran.bwie.com.space07.view.IView;

public class FragmentOne extends Fragment implements IView  {
    private String imageUrl="http://www.zhaoapi.cn/ad/getAd?token=05329B0DCBE400ED03760D7B27627FC7";
    private String listUrl="http://www.xieast.com/api/news/news.php";
    private FlyBanner flyBanner;
    private IpresenterImpl mIpresenterImpl;
    private ListView listView;
    private ListAdapter listAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPresenter();
        initView(view);
    }
    //互绑
    private void initPresenter() {
        mIpresenterImpl=new IpresenterImpl(this);
    }
    //解绑

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIpresenterImpl.deatch();
    }

    private void initView(View view) {
        //获取资源ID
        flyBanner = view.findViewById(R.id.flybanner);
        mIpresenterImpl.requestpre(imageUrl,null,ImageBean.class);
        listView = view.findViewById(R.id.listview);
        //设置适配器
        listAdapter = new ListAdapter(getActivity());
        listView.setAdapter(listAdapter);
        mIpresenterImpl.requestpre(listUrl,null,ListBean.class);
    }

    @Override
    public void getdata(Object object) {
        if ((object instanceof ImageBean)){
            ImageBean bean= (ImageBean) object;
            List<ImageBean.DataBean> data = bean.getData();
            List<String> list=new ArrayList<>();
            for (int i=0;i<data.size();i++){
                list.add(data.get(i).icon());
            }
            flyBanner.setImagesUrl(list);
        }
        if (object instanceof ListBean){
            ListBean bean= (ListBean) object;
            listAdapter.setList(bean.getData());
        }

    }
}
