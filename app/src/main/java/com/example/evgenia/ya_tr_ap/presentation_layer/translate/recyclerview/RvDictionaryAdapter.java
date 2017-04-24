package com.example.evgenia.ya_tr_ap.presentation_layer.translate.recyclerview;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evgenia.ya_tr_ap.R;
import com.example.evgenia.ya_tr_ap.data_layer.retrofit.dicitionary.Def;
import com.example.evgenia.ya_tr_ap.data_layer.retrofit.dicitionary.Tr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 24.04.2017.
 */

public class RvDictionaryAdapter extends RecyclerView.Adapter {



    private List<Def> list;

    public RvDictionaryAdapter(List<Def> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_translate_item_translate, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void updateList(List<Def> list){
        this.list.clear();
        this.list.addAll(list);

        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        RecyclerView recyclerView;
        TextView  mainTranslate;
        TextView num;
        public ViewHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView)itemView.findViewById(R.id.rv2);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL,false){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            recyclerView.setLayoutManager(layoutManager);

            recyclerView.setAdapter(new RvTranslateAdapter(new ArrayList<Tr>()));

            mainTranslate = (TextView)itemView.findViewById(R.id.tv_1);
            num = (TextView)itemView.findViewById(R.id.tv_2);
        }


        public void onBind(Def def){
            mainTranslate.setText(def.getText());
            num.setText(def.getPos());
            ((RvTranslateAdapter)recyclerView.getAdapter()).updateList(def.getTr());
        }
    }

}
