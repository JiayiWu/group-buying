package com.mergeorder.presentation.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.mergeorder.R;
import com.mergeorder.model.UserList;

import java.util.List;

/**
 * Created by song on 17-5-1.
 *
 * for MyFansActivity#lv_fansList
 */
public class FansItemAdapter extends BaseAdapter {

    private Context context;

    private List<UserList> userList;

    public FansItemAdapter(Context context, List<UserList> userList) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public UserList getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_fans, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.portrait = (ImageView) convertView.findViewById(R.id.fans_item_portrait);
            viewHolder.name = (TextView) convertView.findViewById(R.id.fans_item_name);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.init(getItem(position));

        return convertView;
    }

    private class ViewHolder {

        private ImageView portrait;

        private TextView name;

        private void init(UserList user) {
            Glide.with(context)
                    .load(Uri.parse(user.getIconUrl()))
                    .into(portrait);

            name.setText(user.getUserName());
        }
    }
}
