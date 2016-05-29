package com.ringov.testandroid.view.friends_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.ringov.testandroid.R;
import com.ringov.testandroid.model.User;

import java.util.ArrayList;
import java.util.List;

public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.FriendVH>{

    private List<User> friends;

    public FriendsListAdapter(){
        friends = new ArrayList<>();
    }

    @Override
    public FriendsListAdapter.FriendVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_list_item_view,parent,false);
        return new FriendVH(view);
    }

    @Override
    public void onBindViewHolder(FriendsListAdapter.FriendVH holder, int position) {
        User friend = friends.get(position);
        holder.setFirstName(friend.getFirstName());
        holder.setLastName(friend.getLastName());
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public void addAll(List<User> friends){
        this.friends.addAll(friends);
        notifyDataSetChanged();
    }

    public class FriendVH extends RecyclerView.ViewHolder{

        TextView firstName;
        TextView lastName;

        public FriendVH(View itemView) {
            super(itemView);
            firstName = (TextView) itemView.findViewById(R.id.friends_list_item_first_name_TV);
            lastName = (TextView) itemView.findViewById(R.id.friends_list_item_last_name_TV);
        }

        public void setFirstName(String firstName) {
            this.firstName.setText(firstName);
        }

        public void setLastName(String lastName){
            this.lastName.setText(lastName);
        }
    }
}
