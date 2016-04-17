package com.fengyun.information.news.view;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fengyun.information.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xfeng on 16/2/22.
 */
public class NewsFragment extends Fragment {
    public static final int NEWS_TYPE_TOP = 0 ;
    public static final int NEWS_TYPE_NBA = 1 ;
    public static final int NEWS_TYPE_CARS = 2 ;
    public static final int NEWS_TYPE_JOKES = 3 ;

    AppBarLayout apl_appbar ;
    TabLayout tb_title ;
    ViewPager vp_content ;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);
        initPane(view);

        vp_content.setOffscreenPageLimit(3);
        setupViewPager(vp_content);
        tb_title.addTab(tb_title.newTab().setText(R.string.top));
        tb_title.addTab(tb_title.newTab().setText(R.string.nba));
        tb_title.addTab(tb_title.newTab().setText(R.string.cars));
        tb_title.addTab(tb_title.newTab().setText(R.string.jokes));
        tb_title.setupWithViewPager(vp_content);

        return view;
    }

    private void initPane(View view){
        apl_appbar = (AppBarLayout) view.findViewById(R.id.fragment_news_abl_appbar);
        tb_title = (TabLayout) view.findViewById(R.id.fragment_news_tab_title);
        vp_content = (ViewPager) view.findViewById(R.id.fragment_news_vp_contents);
    }


    private void setupViewPager(ViewPager mViewPager) {
        //Fragment中嵌套使用Fragment一定要使用getChildFragmentManager(),否则会有问题
        MyFragmentAdapter fragmentAdapter = new MyFragmentAdapter(this.getChildFragmentManager()) ;
        fragmentAdapter.addFragment(NewsListFragment.initInstance(NEWS_TYPE_TOP), getString(R.string.top));
        fragmentAdapter.addFragment(NewsListFragment.initInstance(NEWS_TYPE_NBA), getString(R.string.nba));
        fragmentAdapter.addFragment(NewsListFragment.initInstance(NEWS_TYPE_CARS), getString(R.string.cars));
        fragmentAdapter.addFragment(NewsListFragment.initInstance(NEWS_TYPE_JOKES), getString(R.string.jokes));
        mViewPager.setAdapter(fragmentAdapter);
    }

    private class MyFragmentAdapter extends FragmentPagerAdapter{

        private final List<android.support.v4.app.Fragment> fragmentList = new ArrayList<android.support.v4.app.Fragment>() ;
        private final List<String> fragmentTitles = new ArrayList<String>() ;

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(android.support.v4.app.Fragment fragment,String fragmentTitle){
            this.fragmentList.add(fragment) ;
            this.fragmentTitles.add(fragmentTitle) ;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitles.get(position) ;
        }
    }
}
