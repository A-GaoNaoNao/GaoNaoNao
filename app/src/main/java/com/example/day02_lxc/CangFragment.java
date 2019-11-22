package com.example.day02_lxc;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.day02_lxc.Baen.DaoSession;
import com.example.day02_lxc.Baen.Person;
import com.example.day02_lxc.Baen.PersonDao;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CangFragment extends Fragment implements MyadapterRecyc2.ClickLog {


    private RecyclerView re;
    private List<Person> people;
    private MyadapterRecyc2 myadapterRecyc2;
    private PersonDao personDao;
    private boolean LANJIAZAI;

    public CangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cang, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        LANJIAZAI = true;
        initView(getView());
        super.onActivityCreated(savedInstanceState);
    }

    private void initView(View view) {
        re = view.findViewById(R.id.re_cha);
        re.setLayoutManager(new LinearLayoutManager(getContext()));

        DaoSession daoSession = MyAppllcation.daoSession;
        personDao = daoSession.getPersonDao();
        people = personDao.loadAll();

        myadapterRecyc2 = new MyadapterRecyc2(getContext(), people);

        re.setAdapter(myadapterRecyc2);

        myadapterRecyc2.setClickLog(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && LANJIAZAI){
            initView(getView());
        }
    }

    @Override
    public void click(int index) {
        Long id = people.get(index).getId();
        personDao.deleteByKey(id);
        people.remove(index);
        myadapterRecyc2.notifyDataSetChanged();
        Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
    }
}
