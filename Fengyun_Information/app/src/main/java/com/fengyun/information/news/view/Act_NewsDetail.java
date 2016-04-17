package com.fengyun.information.news.view;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.fengyun.information.R;
import com.fengyun.information.bean.NewsBean;
import com.fengyun.information.util.FengKits;
import com.fengyun.information.util.ImageLoaderUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import org.sufficientlysecure.html


/**
 * ProjectName: Fengyun_Information
 * ClassName: Act_NewsDetail
 * User: xfeng
 * <p/>
 * Date: 2016-03-27 08:27
 * <p/>
 * Version : V 1.0.0
 * throws:
 * <p/>
 * 新闻详情类
 */
public class Act_NewsDetail extends SwipeBackActivity implements INewsDetailView {


    @Bind(R.id.act_newsdetail_ivBar)
    ImageView iv_bar;
    @Bind(R.id.act_newsdetail_tb)
    Toolbar tb_detailtitle;
    @Bind(R.id.act_newsdetail_ctlToolbar)
    CollapsingToolbarLayout ctlToolbar;
    @Bind(R.id.act_newsdetail_appbar)
    AppBarLayout appbar;
    @Bind(R.id.act_newsdetail_pbLoadingbar)
    ProgressBar pbLoadingbar;
    @Bind(R.id.act_newsdetail_htNewsContent)
    org.sufficientlysecure.htmltextview.HtmlTextView htNewsContent;

    private SwipeBackLayout mSwipeBackLayout;
    private NewsBean mNewsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_newsdetail);
        ButterKnife.bind(this);

        //把toolbar设置到view中，替换原来的actionBar
        setSupportActionBar(tb_detailtitle);
        // 给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //给toolbar添加点击事件
        tb_detailtitle.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 添加返回操作
            }
        });

        //初始化全屏左滑控件
        mSwipeBackLayout = new SwipeBackLayout(this);
        //设置屏幕滑动区域
        mSwipeBackLayout.setEdgeSize(FengKits.getWidthInPx(this));
        //设置滑动方式为从左边到右边滑动
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        //获取新闻实体对象
        mNewsBean = (NewsBean) getIntent().getExtras().getSerializable("NewsBean");

        //为CollapsingToolbarLayout 设置标题
        ctlToolbar.setTitle(mNewsBean.title);

        ImageLoaderUtils.loadingImage(ImageLoaderUtils.NEWSDETAIL_TITLE, mNewsBean.imgsrc, iv_bar);


    }

    @Override
    public void showNewsDetail() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showLoadingFialMsg(String errorMsg) {

    }
}
