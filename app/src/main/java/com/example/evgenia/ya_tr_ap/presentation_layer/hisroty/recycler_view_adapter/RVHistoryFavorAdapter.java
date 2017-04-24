package com.example.evgenia.ya_tr_ap.presentation_layer.hisroty.recycler_view_adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evgenia.ya_tr_ap.R;
import com.example.evgenia.ya_tr_ap.presentation_layer.hisroty.HistoryFavorModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 14.04.2017.
 */

public class RVHistoryFavorAdapter extends RecyclerView.Adapter<RVHistoryFavorAdapter.ViewHolder> implements Filterable{

    public interface OnRecyclerViewListener{
        void onListUpdated(int listSize);
        void onItemClicked();
        void onFavorChanged(HistoryFavorModel model);
    }

    private static final String TAG = "RVHistoryAdapter";
    private ArrayList<HistoryFavorModel> list;
    private Filter filter;
    private OnRecyclerViewListener listener;


    public RVHistoryFavorAdapter(ArrayList<HistoryFavorModel> list, OnRecyclerViewListener listener) {
        this.list = list;
        this.listener = listener;
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


    public void updateItems(ArrayList<HistoryFavorModel> list){
        Log.d(TAG, "updateItems: ");
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();

        if(listener != null){
            listener.onListUpdated(this.list.size());
        }

        ((RVFilter)getFilter()).setDisplayedList(this.list);
    }

    @Override
    public Filter getFilter() {
        if(filter == null){
            filter = new RVFilter(this, list);
        }
        return filter;
    }


    /*=============== CLASSES ========================*/
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
                    list.get(getAdapterPosition()).setFavorites(mark.isChecked()==true?1:0);
                    if(listener != null){
                        listener.onFavorChanged(list.get(getAdapterPosition()));
                    }

                }
            });

            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl_clickable_layout);
            relativeLayout.setOnClickListener(this);
        }

        public void onBindModel(HistoryFavorModel model){
            text.setText(model.getText());
            translate.setText(model.getTranslate());
            langs.setText(model.getLang());
            mark.setChecked(model.getFavorites()==1);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.rl_clickable_layout :
                    if(listener != null){
                        listener.onItemClicked();
                    }
//                    Toast.makeText(v.getContext(), "translate", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    }


    public class RVFilter extends Filter{

        private RVHistoryFavorAdapter adapter;

        private List<HistoryFavorModel> displayedList;

        private List<HistoryFavorModel> filteredList;

        private CharSequence prevChar = "";

        public RVFilter(RVHistoryFavorAdapter adapter, List<HistoryFavorModel> displayedList) {
            this.adapter = adapter;
            this.displayedList = new ArrayList<>(displayedList);
            this.filteredList = new ArrayList<>();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            Log.d(TAG, "performFiltering: ");
            filteredList.clear();
            prevChar = constraint;

            FilterResults results = new FilterResults();

            if (constraint.length() == 0) {
                filteredList.addAll(displayedList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (HistoryFavorModel model  : displayedList) {
                    String text = model.getText().toLowerCase().trim();
                    String translate = model.getTranslate().toLowerCase().trim();

                    if (text.contains(filterPattern) || translate.contains(filterPattern)) {
                        filteredList.add(model);
                    }
                }
            }
            results.values = filteredList;
            results.count = filteredList.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            adapter.list.clear();
            adapter.list.addAll((ArrayList)results.values);
            notifyDataSetChanged();
            if(listener != null){
                listener.onListUpdated(results.count);
            }
        }

        public void setDisplayedList(List<HistoryFavorModel> displayedList) {
            this.displayedList.clear();
            this.displayedList.addAll(displayedList);
            this.filter(prevChar);

        }
    }
}
