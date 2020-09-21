package com.ghtk.base.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 4/6/2017.
 */

public class ContainerPagerAdapter extends FragmentPagerAdapter {
  protected final List<Fragment> mFragmentList = new ArrayList<>();
  protected List<String> mFragmentTitleList = new ArrayList<>();

  public ContainerPagerAdapter(FragmentManager manager) {
    super(manager);
  }

  @Override
  public Fragment getItem(int position) {
    return mFragmentList.get(position);
  }

  @Override
  public int getCount() {
    return mFragmentList.size();
  }

  public void addFragment(Fragment fragment, String title) {
    if (title == null) title = "";
    mFragmentList.add(fragment);
    mFragmentTitleList.add(title);
  }

  public void setTitle(String title, int position) {
    mFragmentTitleList.set(position - 1, title);
    notifyDataSetChanged();
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return mFragmentTitleList.get(position);
  }

  public void setFragment(int i, Fragment tabFragment, String title) {
    if (title == null) title = "";
    mFragmentList.set(i, tabFragment);
    mFragmentTitleList.set(i, title);
    notifyDataSetChanged();
  }
}
