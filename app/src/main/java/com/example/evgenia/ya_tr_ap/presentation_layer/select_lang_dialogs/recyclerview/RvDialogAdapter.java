package com.example.evgenia.ya_tr_ap.presentation_layer.select_lang_dialogs.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evgenia.ya_tr_ap.R;
import com.example.evgenia.ya_tr_ap.presentation_layer.select_lang_dialogs.DialogModel;
import com.example.evgenia.ya_tr_ap.presentation_layer.utils.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Evgenia on 09.04.2017.
 */
// TODO: 12.04.2017 сделать два вьюхолдера с некликабельными ячейками, или настроить заголовки как не кликабельные
public class RvDialogAdapter extends RecyclerView.Adapter {

    private static final int TYPE_LANG = 0;
    private static final int TYPE_HEADER = 1;

    /**
     * листенер, который вызываем при выборе языка, и передаем ему тот язык, который выбрали*/
    public interface OnSelectLangListener{
        void languageSelected(String language);
    }

    private List<Object> itemList;
    private OnSelectLangListener listener;
    /**
     * позиция с которой начинается список "все языки" и заканчивается список "недавно использованные"*/
    private int allLangsPosition = 0;

    public RvDialogAdapter(ArrayList<DialogModel> itemList) {
        this.itemList = new ArrayList<>();
        updateListByInsertingHeaders(itemList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_LANG) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_frg_select_lang_item, parent, false);
            return new VHLangs(view);

        }else if(viewType == TYPE_HEADER){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_frg_select_lang_item_title, parent, false);
            return new VHeaders(view);
        }else
            return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder != null){
            if(holder instanceof VHeaders){
                ((VHeaders)holder).textView.setText(itemList.get(position).toString().toUpperCase());
            }else {
                ((VHLangs)holder).onBind(position);
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(itemList.get(position) instanceof DialogModel){
            return TYPE_LANG;
        }else if(itemList.get(position) instanceof String){
            return TYPE_HEADER;
        }else return -1;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void updateItems(List<DialogModel> list){
        this.itemList.clear();
        this.itemList.addAll(list);
        notifyDataSetChanged();
    }
    /**
     * получаем из бд языки и недавние до 3 штук {@link Utils#NUMBER_RECENT_LANGS} и все вместе
     * чтоб понять какие из прияланях - недавние языки, и где нужен заголовок
     * отсортируем скписок
     * если какая-то модель встречается два раза, то она есть в списке недавних языков
     * считаем сколько таких "удвоенных можелей и вставляем в список заголовок на нужное место"*/
    public void updateListByInsertingHeaders(ArrayList<DialogModel> list){
        ArrayList<DialogModel> nList = new ArrayList<>();
        nList.addAll(list.subList(0, Utils.NUMBER_RECENT_LANGS));
        int countRecent = 0;
        for(DialogModel dm : nList){
            if(list.contains(dm)) countRecent++;
        }

        itemList.addAll(list);
        itemList.add(countRecent, "Все языки");
        if(countRecent > 0){
            itemList.add(0, "Недавние языки");
        }
    }

    public void setListener(OnSelectLangListener listener) {
        this.listener = listener;
    }



//    ============= HOLDER ==========

    public class VHLangs extends RecyclerView.ViewHolder{
        TextView text;
        View marker;
        public VHLangs(final View itemView) {
            super(itemView);
            text = (TextView)itemView.findViewById(R.id.tv_language);
            marker = itemView.findViewById(R.id.iv_checkmark);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    DialogModel dialogModel = (DialogModel) itemList.get(position);

                    if (listener != null) {
                        listener.languageSelected(dialogModel.getLanguageCode());
                    }



                    /**
                     * ИЗМЕНЕНИЯ В БД ЧЕРЕЗ ПРЕЗЕНТЕР
                     * при изменении выбранного языка в одной таблице, убедиться, что язык в другой таблице не одинаков
                     * иначе - сменить язык другого диалога
                     * УНИЧТОЖИТЬ ДИАЛОГ
                     * отправка сигнала к листенеру, где оттуда - ЗАПРОС К БД, и к серверу*/
                }
            });
        }

        public void onBind(int position){

            DialogModel mod = (DialogModel)itemList.get(position);
            text.setText(mod.getLanguage());
            if(mod.isSelected()){
                marker.setVisibility(View.VISIBLE);
            }else
                marker.setVisibility(View.GONE);


        }
    }


    public class VHeaders extends RecyclerView.ViewHolder{
        private TextView textView;
        public VHeaders(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.tv_title);
        }
    }

//    сформировать запросом бд список, где в начале идут элесменты из недавно выбраных языков
//    найти позицию последнего языка из списка недавно используемыых
//    и отталкиваться от нее
}
