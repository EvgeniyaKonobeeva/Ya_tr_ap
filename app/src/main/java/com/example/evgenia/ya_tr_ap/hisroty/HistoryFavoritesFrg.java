package com.example.evgenia.ya_tr_ap.hisroty;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.evgenia.ya_tr_ap.R;
import com.example.evgenia.ya_tr_ap.utils.Utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by User on 12.04.2017.
 */

public class HistoryFavoritesFrg extends Fragment implements HistoryFavorContract.IHistoryFavorView{

    private EditText etSearch;
    private RecyclerView recyclerView;
    private LinearLayout linearLayout;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({HISTORY, FAVORITES})
    public @interface FragmentType {}
    public static final int HISTORY = 0;
    public static final int FAVORITES = 1;
    public static @FragmentType
    int getType(int t){
        switch (t){
            case FAVORITES:
                return FAVORITES;
            case HISTORY:
                return HISTORY;
            default:
                return FAVORITES;
        }
    }


    private final static String TAG = "HistoryFavoritesFrg";


    public static HistoryFavoritesFrg newInstance(String title, @FragmentType int type){
        Bundle bundle = new Bundle();
        bundle.putString(Utils.KEY_TITLE, title);
        bundle.putInt(Utils.KEY_TYPE, type);

        HistoryFavoritesFrg historyFavoritesFrg = new HistoryFavoritesFrg();
        historyFavoritesFrg.setArguments(bundle);



        return historyFavoritesFrg;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d(TAG, "onHiddenChanged: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View root = inflater.inflate(R.layout.fragment_history, container, false);
        initLayout(root);
        return root;

    }

    private void initLayout(View root){
        etSearch = (EditText)root.findViewById(R.id.et_search);
        recyclerView = (RecyclerView) root.findViewById(R.id.rv_history);
        linearLayout = (LinearLayout) root.findViewById(R.id.linear_emmpty_list);

        ImageView emptyIcon = (ImageView)root.findViewById(R.id.empty_icon);
        TextView tvEmptyList = (TextView) root.findViewById(R.id.tv_empty_list);


        if(getArguments() != null){
            Log.d(TAG, "initLayout: ");
            if(getArguments().getInt(Utils.KEY_TYPE) == HISTORY){
                etSearch.setHint(getActivity().getString(R.string.find_in_history));
                tvEmptyList.setText(getActivity().getString(R.string.no_translate_in_history));
            }else {
                etSearch.setHint(getActivity().getString(R.string.find_in_favors));
                tvEmptyList.setText(getActivity().getString(R.string.no_translate_in_favors));
            }
        }
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: ");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
