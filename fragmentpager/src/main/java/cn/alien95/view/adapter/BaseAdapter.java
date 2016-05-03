package cn.alien95.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by llxal on 2015/12/21.
 */
public class BaseAdapter extends FragmentStatePagerAdapter {

    private boolean isNotifyChangeData = false;
    private List<Fragment> fragmentList;
    private List<String> titles;

    public BaseAdapter(FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null) {
            return titles.get(position);
        } else
            return super.getPageTitle(position);
    }

    public void setTitles(String... titles) {
        this.setTitles(Arrays.asList(titles));
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
        if (isNotifyChangeData) { //说明先调用了add或者addAll方法，adapter更新了数据
            notifyDataSetChanged();
        }
    }

    public void add(Fragment fragment) {
        fragmentList.add(fragment);
        notifyDataSetChanged();
        isNotifyChangeData = true;
    }

    public void insert(Fragment fragment, int position) {
        fragmentList.add(position, fragment);
        notifyDataSetChanged();
        isNotifyChangeData = true;
    }

    public void addAll(Fragment... fragments) {
        this.addAll(Arrays.asList(fragments));
    }

    public void addAll(List<Fragment> list) {
        fragmentList.addAll(list);
        notifyDataSetChanged();
        isNotifyChangeData = true;
    }

    public void replace(Fragment fragment, int position) {
        fragmentList.set(position, fragment);
        notifyDataSetChanged();
        isNotifyChangeData = true;
    }

    public void delete(Fragment fragment) {
        fragmentList.remove(fragment);
        notifyDataSetChanged();
        isNotifyChangeData = true;
    }

    public void delete(int position) {
        fragmentList.remove(position);
        notifyDataSetChanged();
        isNotifyChangeData = true;
    }

}
