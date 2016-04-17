package com.fengyun.information.news.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fengyun.information.R;
import com.fengyun.information.bean.NewsBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;


public class NewsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_ITEM = 0 ;
    public static final int TYPE_FOOT = 1 ;

    private Context mContext ;
    private List<NewsBean> mNewsData ;
    private boolean mShowFoot = true ;
    private OnItemClickListener itemClickListener ;

    private DisplayImageOptions iconOptions ;

    public NewsListAdapter(Context context) {
        this.mContext = context ;
    }

    public void setData(List<NewsBean> newsData){
        this.mNewsData = newsData ;
        this.notifyDataSetChanged();
    }

    public void initImageLoaderOptions(){
        iconOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(0)
                .showImageOnFail(0)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(mContext));
    }

    public boolean isShowFoot(){
        return this.mShowFoot ;
    }

    public void isShowFoot(boolean isShowFoot){
        this.mShowFoot = isShowFoot ;
    }

    public void setItemClickListener(OnItemClickListener onItemClickListener){
        this.itemClickListener = onItemClickListener ;
    }

    /**
     * 设置itmeview的type 类型，设置header ， item，和 foot
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (!mShowFoot){
            return TYPE_ITEM ;
        }
        if (position + 1 == getItemCount()){
            return TYPE_FOOT ;
        }else{
            return TYPE_ITEM ;
        }
    }


    /**
     * 初始化itme的view和viewholder
     *
     * @param parent
     * @param viewType
     * @return itmeViewHolder
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM){ //返回item
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_newsitem,null) ;
            return new ItemViewHolder(v) ;

        }else {  //返回foot
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.footer, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ItemViewHolder) {
            NewsBean newsBean = mNewsData.get(position);

            if (newsBean == null) {
                return;
            }

            ((ItemViewHolder) holder).mTitle.setText(newsBean.title);
            ((ItemViewHolder) holder).mDesc.setText(newsBean.digest);

            if (iconOptions == null) {
                initImageLoaderOptions();
            }
            ImageLoader.getInstance().displayImage(newsBean.imgsrc, ((ItemViewHolder) holder).mNewsImg, iconOptions);
        }
    }

    @Override
    public int getItemCount() {
        int begin = mShowFoot?1:0 ;
        if (mNewsData == null){
            return begin ;
        }
        return mNewsData.size() + begin;
    }

    public interface OnItemClickListener{
        public void onItemClick(View view , int position) ;
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View view) {
            super(view);
        }

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView mTitle;
        public TextView mDesc;
        public ImageView mNewsImg;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.view_newitem_iv_title);
            mDesc = (TextView) itemView.findViewById(R.id.view_newitem_iv_content);
            mNewsImg = (ImageView) itemView.findViewById(R.id.view_newitem_iv_icon);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (itemClickListener != null){
                itemClickListener.onItemClick(v,this.getPosition());
            }
        }
    }
}
