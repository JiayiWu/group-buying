package com.mergeorder.presentation.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.bumptech.glide.Glide;
import com.koushikdutta.ion.Ion;
import com.mergeorder.R;
import com.mergeorder.model.UserList;
import com.mergeorder.util.HttpUtil;

import java.util.List;

/**
 * Created by song on 17-5-1.
 *
 * for MyFansActivity#lv_fansList
 */
public class FollowItemAdapter extends BaseAdapter {

    private Context context;

    private List<UserList> userList;

    public FollowItemAdapter(Context context, List<UserList> userList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_follow, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.portrait = (ImageView) convertView.findViewById(R.id.follow_item_portrait);
            viewHolder.name = (TextView) convertView.findViewById(R.id.follow_item_name);
            viewHolder.follow = (Button) convertView.findViewById(R.id.follow_item_follow);

            viewHolder.follow.setOnClickListener(e -> {
                String url = "/user/relationship/update";

                Ion.with(context)
                        .load(HttpUtil.BASE_URL + url)
                        .setBodyParameter("fansid", String.valueOf(userList.get(position).getId()))
                        .setBodyParameter("model", String.valueOf(!viewHolder.followed))
                        .asJsonObject()
                        .setCallback((e1, result) -> {
                            if (result != null) {
                                if (result.get("result").getAsBoolean()) {
                                    viewHolder.followed = !viewHolder.followed;

                                    viewHolder.showFollow();
                                } else {
                                    Toast.makeText(context, result.get("reason").getAsString(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            });

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

        private Button follow;

        private boolean followed;

        private void init(UserList user) {
            Glide.with(context)
                    .load(Uri.parse(user.getIconUrl()))
                    .into(portrait);

            name.setText(user.getUserName());
        }

        private void showFollow() {
            if (followed) {
                follow.setBackgroundColor(context.getResources().getColor(R.color.greyText));
                follow.setText(R.string.label_followed);
            } else {
                follow.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                follow.setText(R.string.label_follow);
            }
        }
    }
}
