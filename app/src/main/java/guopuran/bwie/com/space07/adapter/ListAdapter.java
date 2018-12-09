package guopuran.bwie.com.space07.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import guopuran.bwie.com.space07.R;
import guopuran.bwie.com.space07.bean.ListBean;

public class ListAdapter extends BaseAdapter {
    private List<ListBean.Data> list;
    private Context context;

    public ListAdapter(Context context) {
        this.context = context;
        //初始化
        list=new ArrayList<>();
    }

    public void setList(List<ListBean.Data> list) {
        this.list = list;
        //刷新适配器
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ListBean.Data getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.listitem,parent,false);
            holder=new ViewHolder(convertView);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.getdata(getItem(position));
        return convertView;
    }
    class ViewHolder{
        private TextView item_textview;
        private ImageView item_imageview;

        public ViewHolder(View convertView) {
            item_textview=convertView.findViewById(R.id.item_textview);
            item_imageview=convertView.findViewById(R.id.item_image);
            convertView.setTag(this);
        }

        public void getdata(ListBean.Data item) {
            item_textview.setText(item.getTitle());
            ImageLoader.getInstance().displayImage(item.getThumbnail_pic_s(),item_imageview);
        }
    }
}
