package com.example.evgenia.ya_tr_ap.presentation_layer.select_lang_dialogs.recyclerview;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.evgenia.ya_tr_ap.R;
import com.example.evgenia.ya_tr_ap.presentation_layer.preferences.Preferences;
import com.example.evgenia.ya_tr_ap.presentation_layer.select_lang_dialogs.models.Language;
import com.example.evgenia.ya_tr_ap.presentation_layer.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Evgenia on 09.04.2017.
 */
public class RvDialogAdapter extends RecyclerView.Adapter {

    private static final int TYPE_LANG = 0;
    private static final int TYPE_HEADER = 1;
    private Language selectedLanguage;

    /**
     * листенер, который вызываем при выборе языка, и передаем ему тот язык, который выбрали*/
    public interface OnSelectLangListener{
        void languageSelected(String code, String lang);
    }

    private List<Object> itemList;
    private OnSelectLangListener listener;

    public RvDialogAdapter(ArrayList<Language> itemList) {
        this.itemList = new ArrayList<>();
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
        if(itemList.get(position) instanceof Language){
            return TYPE_LANG;
        }else if(itemList.get(position) instanceof String){
            return TYPE_HEADER;
        }else return -1;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void updateItems(ArrayList<Language> list){
        updateListByInsertingHeaders(list);
        notifyDataSetChanged();
    }
    /**
     * получаем из бд языки и недавние до 3 штук {@link Utils#NUMBER_RECENT_LANGS} и все вместе
     * чтоб понять какие из прияланях - недавние языки, и где нужен заголовок
     * отсортируем скписок
     * если какая-то модель встречается два раза, то она есть в списке недавних языков
     * считаем сколько таких "удвоенных можелей и вставляем в список заголовок на нужное место"*/
    public void updateListByInsertingHeaders(ArrayList<Language> list){
        // помечаем самый недавний язык как выбранный
        selectedLanguage = Collections.max(list);
        selectedLanguage.setSelected(true);

        itemList.clear();
        itemList.addAll(list);



        // сортируем по убыванию
        Collections.sort(list);
        Collections.reverse(list);

        int countRecentPos = 0;

        if(list.size() > Utils.NUMBER_RECENT_LANGS){
            List<Language> resentLangs = new ArrayList<>();
            resentLangs.addAll(list.subList(0, Utils.NUMBER_RECENT_LANGS));
            for(int i = Utils.NUMBER_RECENT_LANGS-1; i >=0 ; i--){
                if(resentLangs.get(i).getSynkTime() > 0){
                    resentLangs.get(i).setRecent(true);
                    itemList.add(0,resentLangs.get(i));
                    countRecentPos++;
                }
            }
        }
        if(countRecentPos > 0){
            itemList.add(0, "Недавние языки");
            itemList.add(countRecentPos+1, "Все языки");
        }else {
            itemList.add(0, "Все языки");
        }
    }

    public void setListener(OnSelectLangListener listener) {
        this.listener = listener;
    }



//    ============= HOLDER ==========

    public class VHLangs extends RecyclerView.ViewHolder{
        private static final String TAG = "VHLangs";
        TextView text;
        View marker;
        RelativeLayout relativeLayout;

        public VHLangs(final View itemView) {
            super(itemView);
            text = (TextView)itemView.findViewById(R.id.tv_language);
            marker = itemView.findViewById(R.id.iv_checkmark);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.rl_layout);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Language dialogModel = (Language) itemList.get(position);

                    if (listener != null) {
                        listener.languageSelected(dialogModel.getCode(),dialogModel.getLanguage());
                    }
                }
            });
        }

        public void onBind(int position){
            Log.d(TAG, "onBind: ");

            Language mod = (Language)itemList.get(position);
            text.setText(mod.getLanguage());
            if(mod.isSelected()){
                if(!mod.isRecent() || (mod.isRecent() && position > Utils.NUMBER_RECENT_LANGS)){
                    marker.setVisibility(View.VISIBLE);
                    relativeLayout.setBackgroundColor(relativeLayout.getContext().getResources().getColor(R.color.background2));
                }else {
                    marker.setVisibility(View.GONE);
                    relativeLayout.setBackgroundColor(Color.argb(0, 0, 0, 0));
                }
            }else {
                marker.setVisibility(View.GONE);
                relativeLayout.setBackgroundColor(Color.argb(0, 0, 0, 0));
            }


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
