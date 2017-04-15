package com.example.evgenia.ya_tr_ap.hisroty.recycler_view_adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evgenia.ya_tr_ap.R;
import com.example.evgenia.ya_tr_ap.hisroty.HistoryFavorModel;

import java.util.ArrayList;

/**
 * Created by User on 14.04.2017.
 */

public class RVHistoryFavorAdapter extends RecyclerView.Adapter<RVHistoryFavorAdapter.ViewHolder> {

    private static final String TAG = "RVHistoryAdapter";
    private ArrayList<HistoryFavorModel> list;

    public RVHistoryFavorAdapter(ArrayList<HistoryFavorModel> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_history_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBindModel(list.get(position));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView text;
        private TextView translate;
        private TextView langs;
        private CheckBox mark;
        private RelativeLayout relativeLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.tv_text);

            translate = (TextView)itemView.findViewById(R.id.tv_translate);

            langs = (TextView)itemView.findViewById(R.id.tv_langs);

            mark = (CheckBox) itemView.findViewById(R.id.chb_mark);
            mark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    list.get(getAdapterPosition()).setMarked(mark.isChecked());
                }
            });

            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl_clickable_layout);
            relativeLayout.setOnClickListener(this);
        }

        public void onBindModel(HistoryFavorModel model){
            text.setText(model.getText());
            translate.setText(model.getTranslateMain());
            langs.setText(model.getLang());
            mark.setChecked(model.isMarked());
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.rl_clickable_layout :

                    Toast.makeText(v.getContext(), "translate", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    }

    public void addItems(ArrayList<HistoryFavorModel> list){
        Log.d(TAG, "addItems: ");
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

}
