package com.fengyun.information.main.presenter;

import com.fengyun.information.main.view.IMainView;

/**
 * Created by xfeng on 16/2/19.
 */
public class MainPresenterImpl implements MainPresenter {

    private IMainView iMainView ;

    public MainPresenterImpl(IMainView iMainView){
        this.iMainView = iMainView ;
    }
}
