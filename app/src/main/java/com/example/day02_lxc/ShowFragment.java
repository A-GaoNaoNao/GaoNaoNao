package com.example.day02_lxc;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.day02_lxc.Baen.DaoSession;
import com.example.day02_lxc.Baen.ItemList;
import com.example.day02_lxc.Baen.Person;
import com.example.day02_lxc.Baen.PersonDao;
import com.example.day02_lxc.Pertent.MinaPertent;
import com.example.day02_lxc.Pertent.MinaView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowFragment extends Fragment implements MinaView, MyadapterRecyc.ClickLong, View.OnClickListener {


    private RecyclerView re;
    private ArrayList<ItemList.RecentBean> recentBeans;
    private MyadapterRecyc myadapterRecyc;
    private PopupWindow popupWindow;
    private View bt_que;
    private View bt_qu;
    private int mPosition;

    public ShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initView(getView());
        initPopw();
        super.onActivityCreated(savedInstanceState);
    }

    private void initPopw() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_popw, null);
        popupWindow = new PopupWindow(view, GridLayout.LayoutParams.MATCH_PARENT, GridLayout.LayoutParams.WRAP_CONTENT);
        bt_que = view.findViewById(R.id.bt_que);
        bt_qu = view.findViewById(R.id.bt_qu);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        bt_que.setOnClickListener(this);
        bt_qu.setOnClickListener(this);
    }

    private void initView(View view) {
        re = view.findViewById(R.id.re);
        re.setLayoutManager(new LinearLayoutManager(getContext()));

        recentBeans = new ArrayList<>();

        myadapterRecyc = new MyadapterRecyc(getContext(), recentBeans);

        re.setAdapter(myadapterRecyc);

        MinaPertent pertent = new MinaPertent();
        pertent.Retrofit(this);

        myadapterRecyc.setClickLong(this);
    }

    @Override
    public void Cheng(ItemList itemList) {
        ArrayList<ItemList.RecentBean> recent = (ArrayList<ItemList.RecentBean>) itemList.getRecent();
        recentBeans.addAll(recent);
        myadapterRecyc.notifyDataSetChanged();
    }

    @Override
    public void Shi(Throwable e) {
        Log.i("Gao", "Shi: "+e);
    }

    @Override
    public void click(int index) {
        mPosition = index;
       popupWindow.showAtLocation(re,Gravity.CENTER,0,0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_que:
                DaoSession daoSession = MyAppllcation.daoSession;
                PersonDao personDao = daoSession.getPersonDao();
                String title = recentBeans.get(mPosition).getTitle();
                String img = recentBeans.get(mPosition).getThumbnail();
                Person person = new Person();
                person.setImg(img);
                person.setTitle(title);
                personDao.insert(person);

                popupWindow.dismiss();

                Toast.makeText(getContext(),"收藏成功",Toast.LENGTH_SHORT).show();

                break;
            case R.id.bt_qu:

                popupWindow.dismiss();
                    break;
        }
    }
}
