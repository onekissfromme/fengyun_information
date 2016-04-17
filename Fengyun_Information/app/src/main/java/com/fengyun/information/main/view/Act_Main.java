package com.fengyun.information.main.view;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.fengyun.information.R;
import com.fengyun.information.news.view.NewsFragment;
import com.fengyun.information.main.presenter.MainPresenter;
import com.fengyun.information.main.presenter.MainPresenterImpl;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xfeng on 16/2/19.
 */
public class Act_Main extends AppCompatActivity implements IMainView {

    Toolbar toolbar;

    ActionBarDrawerToggle mDrawerToggle;

    @Bind(R.id.frame_content)
    FrameLayout frameContent;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    @Bind(R.id.navigation_view)
    NavigationView navigationView;
    @Bind(R.id.act_main_dl_mian)
    DrawerLayout actMainDlMian;

    MainPresenter mMainPersenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_mian);
        ButterKnife.bind(this);


        //添加titlebar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //添加侧边栏控制按钮，系统默认
        mDrawerToggle = new ActionBarDrawerToggle(this, actMainDlMian, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        actMainDlMian.setDrawerListener(mDrawerToggle);

        //TODO 添加侧边栏
        //setupDrawNavigation

        mMainPersenter = new MainPresenterImpl(this) ;

        switch2News();
    }

    /**
     * 选择新闻列表
     */
    @Override
    public void switch2News() {
        //
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new NewsFragment()).commit();
        toolbar.setTitle("新闻");
    }

    @Override
    public void switch2Images() {

    }

    @Override
    public void switch2Weather() {

    }

    @Override
    public void switch2About() {

    }
}
