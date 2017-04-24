package com.example.evgenia.ya_tr_ap.presentation_layer.translate.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evgenia.ya_tr_ap.R;
import com.example.evgenia.ya_tr_ap.data_layer.retrofit.dicitionary.Ex;
import com.example.evgenia.ya_tr_ap.data_layer.retrofit.dicitionary.Mean;
import com.example.evgenia.ya_tr_ap.data_layer.retrofit.dicitionary.Syn;
import com.example.evgenia.ya_tr_ap.data_layer.retrofit.dicitionary.Tr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 24.04.2017.
 */

public class RvTranslateAdapter extends RecyclerView.Adapter {

    List<Tr> list;

    public RvTranslateAdapter(List<Tr> list) {
        this.list = list;
    }

    public void updateList(List<Tr> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_translate_item_translate2, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ItemViewHolder)holder).onBind(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView position;
        TextView syn;
        TextView means;
        TextView ex;


        public ItemViewHolder(View v) {
            super(v);

            position = (TextView)v.findViewById(R.id.tv_3);
            syn = (TextView)v.findViewById(R.id.tv_4);
            means = (TextView)v.findViewById(R.id.tv_5);
            ex = (TextView)v.findViewById(R.id.tv_6);
        }


        public void onBind(Tr tr, int pos){
            position.setText("");
            syn.setText("");
            means.setText("");
            ex.setText("");


            position.setText((pos +1 )+"");
            if(tr.getText() != null && tr.getText().length() > 0)
                syn.setText(tr.getText());
            setSyn(syn, tr.getSyn());
            setMeans(means, tr.getMean());
            setEx(ex, tr.getEx());

        }


        void setSyn(TextView tv, List<Syn> list){

//            tv.setText("");
            if(list != null) {
                tv.setVisibility(View.VISIBLE);
                if(tv.getText().length() > 0)
                    tv.append(", ");
                for (int i = 0; i < list.size() - 1; i++) {
                    tv.append(list.get(i) + ", ");
                }
                tv.append(list.get(list.size() - 1).getText());
            }else if(tv.getText().length() == 0)
                tv.setVisibility(View.GONE);


        }
        void setMeans(TextView tv, List<Mean> list){
//            tv.setText("");
            if(list != null) {
                tv.setVisibility(View.VISIBLE);
                if (list.size() == 1) {
                    tv.setText("(" + list.get(0).getText() + ")");
                } else {
                    tv.setText("(" + list.get(0).getText() + ", ");
                    for (int i = 1; i < list.size() - 1; i++) {
                        tv.append(list.get(i) + ", ");
                    }
                    tv.append(list.get(list.size() - 1).getText() + ")");
                }
            }else
                tv.setVisibility(View.GONE);
        }

        void setEx(TextView tv, List<Ex> list){

//            tv.setText(list.get(0).getText() + " - ");
//
//            for(int k = 0; k < list.get(0).getTr().size()-1; k++){
//                tv.append(list.get(0).getTr().get(k).getText() + ", ");
//            }
//            tv.append(list.get(0).getTr().get(list.get(0).getTr().size()-1).getText() + "\\n");
//            tv.setText("");
            if(list != null) {
                tv.setVisibility(View.VISIBLE);
                for (int i = 0; i < list.size(); i++) {
                    tv.append(list.get(i).getText() + " - ");

                    for (int k = 0; k < list.get(i).getTr().size() - 1; k++) {
                        tv.append(list.get(i).getTr().get(k).getText() + ", ");
                    }
                    tv.append(list.get(i).getTr().get(list.get(i).getTr().size() - 1).getText() + "\n");
                }
            }else
                tv.setVisibility(View.GONE);

        }
    }

}
