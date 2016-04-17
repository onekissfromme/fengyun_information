package com.fengyun.information.news.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fengyun.information.R;
import com.fengyun.information.news.adapter.NewsListAdapter;
import com.fengyun.information.bean.NewsBean;
import com.fengyun.information.news.presenter.NewsPresenter;
import com.fengyun.information.news.presenter.NewsPresenterImpl;
import com.fengyun.information.util.Urls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xfeng on 16/3/6.
 */
public class NewsListFragment extends Fragment implements INewsView , SwipeRefreshLayout.OnRefreshListener{

    NewsPresenter newsPresenter;
    LinearLayoutManager linearLayoutManager ;

    NewsListAdapter newsAdapter ;

    private int  myType = NewsFragment.NEWS_TYPE_TOP;
    RecyclerView rcv_newlist;
    SwipeRefreshLayout srl_newlist;

    private int pageIndex = 0;

    private List<NewsBean> mData ;

    /**
     * 根据新闻类型初始化新闻列表
     *
     * @param newsType
     * @return 新闻列表的Fragment
     */
    public static NewsListFragment initInstance(int newsType) {

        Bundle bundle = new Bundle();
        NewsListFragment newsListFragment = new NewsListFragment();
        bundle.putInt("myType", newsType);
        newsListFragment.setArguments(bundle);
        return newsListFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsPresenter = new NewsPresenterImpl(getContext(),this) ;
        myType = getArguments().getInt("myType");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_newslistfragment, null);
        initPane(view);
        //设置下拉刷新
        srl_newlist.setColorSchemeColors(R.color.primary,
                R.color.primary_dark, R.color.primary_light,
                R.color.accent);
        srl_newlist.setOnRefreshListener(this);

        //设置RecyclerView 的大小不变，如果
        rcv_newlist.setHasFixedSize(true);

        //初始化linearLayoutManager
        linearLayoutManager = new LinearLayoutManager(getActivity()) ;
        //添加布局管理器
        rcv_newlist.setLayoutManager(linearLayoutManager);

        newsAdapter = new NewsListAdapter(getActivity().getApplicationContext()) ;
        newsAdapter.setItemClickListener(onItemClickListener);
        rcv_newlist.setAdapter(newsAdapter);

        //设置默认 item的添加和移除动画
        rcv_newlist.setItemAnimator(new DefaultItemAnimator());
        rcv_newlist.setOnScrollListener(mOnScrollListener);
        onRefresh();

        return view ;
    }

    private void initPane(View view){
        rcv_newlist = (RecyclerView) view.findViewById(R.id.view_newslistfragment_rcv_newlist);
        srl_newlist = (SwipeRefreshLayout) view.findViewById(R.id.view_newslistfragment_srl) ;
    }

    RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        private int lastVisibleItem ;
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem+1 == newsAdapter.getItemCount()
                    && newsAdapter.isShowFoot()){
                newsPresenter.newsLoading(myType,pageIndex + Urls.PAZE_SIZE);
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            //监听recycleView滑动的位置
            lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition() ;
        }
    };

    /**
     *
     */
    private NewsListAdapter.OnItemClickListener onItemClickListener = new NewsListAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            NewsBean newsBean = mData.get(position) ;
            if (newsBean == null){
                return ;
            }

            Intent intent = new Intent(getContext(),Act_NewsDetail.class) ;
            Bundle bundle = new Bundle() ;
            bundle.putSerializable("NewsBean",newsBean);
            intent.putExtras(bundle) ;
            getActivity().startActivity(intent);

        }
    } ;

    @Override
    public void showProgress() {
        srl_newlist.setRefreshing(true);

    }

    @Override
    public void addViews(List<NewsBean> newList) {
        newsAdapter.isShowFoot(true);
        if (mData == null){
            mData = new ArrayList<NewsBean>() ;
        }
        if (newList != null && newList.size() >= 0){
            mData.addAll(newList) ;
        }

        if (pageIndex == 0){
            newsAdapter.setData(mData);
        }else{
            if (newList == null || newList.size() == 0){
                newsAdapter.isShowFoot(false);
            }
            newsAdapter.notifyDataSetChanged();
        }
        pageIndex += Urls.PAZE_SIZE ;
    }

    @Override
    public void hideProgress() {
        srl_newlist.setRefreshing(false);

    }

    @Override
    public void showLoadingFialMsg(String errorMsg) {
        //TODO 请求失败时提示失败信息
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onRefresh() {
        pageIndex = 0 ;
        if (mData != null){
            mData.clear();
        }
        newsPresenter.newsLoading(myType,pageIndex);
    }
}
