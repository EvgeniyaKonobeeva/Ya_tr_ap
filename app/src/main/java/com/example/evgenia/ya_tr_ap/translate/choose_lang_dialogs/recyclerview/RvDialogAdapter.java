package com.example.evgenia.ya_tr_ap.translate.choose_lang_dialogs.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evgenia.ya_tr_ap.R;
import com.example.evgenia.ya_tr_ap.translate.choose_lang_dialogs.DialogModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Evgenia on 09.04.2017.
 */

public class RvDialogAdapter extends RecyclerView.Adapter {
    private List<Object> itemList;
    /**
     * позиция с которой начинается список "все языки" и заканчивается список "недавно использованные"*/
    private int allLangsPosition = 0;

    public RvDialogAdapter(List<DialogModel> itemList) {
        this.itemList = new ArrayList<>();
        this.itemList.addAll(itemList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_frg_select_lang_item, parent, false);
        return new VHLangs(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((VHLangs)holder).onBind(position);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void updateItems(List<DialogModel> list){
        this.itemList.clear();
        this.itemList.addAll(list);
        getAllLangPosition(list);
        notifyDataSetChanged();
    }

    public void getAllLangPosition(List<DialogModel> list){
        for(DialogModel mod : list){
            if(mod.isLatestSelected()){
                allLangsPosition++;
            }else {
                break;
            }
        }

        this.itemList.add(0,new Object());
        this.itemList.add(allLangsPosition+1, new Object());

    }


//    ============= HOLDER ==========

    public class VHLangs extends RecyclerView.ViewHolder{
        TextView text;
        View marker;
        public VHLangs(View itemView) {
            super(itemView);
            text = (TextView)itemView.findViewById(R.id.tv_language);
            marker = itemView.findViewById(R.id.v_marker);
        }

        public void onBind(int position){

            if(position == 0 && allLangsPosition > 0) {
                text.setText(text.getContext().getString(R.string.recently_used));

            }else if(position == allLangsPosition + 1 || allLangsPosition == 0){
                text.setText(text.getContext().getString(R.string.all_languages));
            }else {
                DialogModel mod = (DialogModel)itemList.get(position);
                text.setText(mod.getLanguage());
                if(mod.isSelected()){
                    marker.setVisibility(View.VISIBLE);
                }else marker.setVisibility(View.GONE);
            }

        }
    }

//    сформировать запросом бд список, где в начале идут элесменты из недавно выбраных языков
//    найти позицию последнего языка из списка недавно используемыых
//    и отталкиваться от нее
}
