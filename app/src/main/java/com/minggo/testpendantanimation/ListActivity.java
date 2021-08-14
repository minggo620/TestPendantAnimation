package com.minggo.testpendantanimation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    private List<EnterRoom> roomList = new ArrayList<>();
    private RecyclerView recyclerView;
    private EnterRoomAdapter enterRoomAdapter;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();
    }

    private void init() {
        handler = new Handler(getMainLooper());
        recyclerView = findViewById(R.id.rv_list);
        for (int i = 0; i < 1; i++) {
            EnterRoom enterRoom = new EnterRoom();
            enterRoom.roomId = i;
            roomList.add(enterRoom);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);  //设置方向
        recyclerView.setLayoutManager(layoutManager);
        enterRoomAdapter = new EnterRoomAdapter();
        recyclerView.setAdapter(enterRoomAdapter);

        recyclerView.scrollToPosition(roomList.size() - 1);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<1000;i++) {
                    EnterRoom enterRoom = new EnterRoom();
                    enterRoom.roomId = new Random().nextInt(100);
                    roomList.add(enterRoom);
                }
                enterRoomAdapter.notifyItemInserted(roomList.size()-1);
                recyclerView.scrollToPosition(roomList.size() - 1);
                handler.postDelayed(this, 1000);
            }
        }, 1000);


    }

    private class EnterRoomAdapter extends RecyclerView.Adapter {
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(ListActivity.this).inflate(R.layout.item_list, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            EnterRoom enterRoom = getItem(position);
            ((ViewHolder) holder).bindData(enterRoom);
        }

        @Override
        public int getItemCount() {
            return roomList.size();
        }

        public EnterRoom getItem(int position) {
            for (int i = 0; i < roomList.size(); i++) {
                if (position == i) {
                    return roomList.get(i);
                }
            }
            return null;
        }


    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        EnterPendantView enterPendantView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            enterPendantView = itemView.findViewById(R.id.lo_user_pendant);
        }

        public void bindData(EnterRoom item) {
            if (!item.isShowAnimate) {
                enterPendantView.setVisibility(View.VISIBLE);
                enterPendantView.cancelAnimator();
                item.isShowAnimate = true;
                enterPendantView.canAnimate = true;
                enterPendantView.init(true);
                enterPendantView.setPendants(null);
                enterPendantView.startAnimations();
            }else{
                enterPendantView.canAnimate = false;
            }
        }
    }


}