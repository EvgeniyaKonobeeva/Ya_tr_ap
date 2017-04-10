package com.example.evgenia.ya_tr_ap;

import java.lang.ref.WeakReference;

/**
 * Created by Evgenia on 09.04.2017.
 */

abstract public class Presenter<IView>      {
    private WeakReference<IView> view;

    public Presenter( ) {

    }


    public void attachView(IView view) {
        this.view = new WeakReference<>(view);
    }


    public void detachView() {
        if (view != null) {
            view.clear();
            view = null;
        }
    }

    protected IView getView() {
        if (view != null) return view.get();
        else return null;
    }
}
