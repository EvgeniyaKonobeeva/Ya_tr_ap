package com.example.evgenia.ya_tr_ap.presentation_layer.translate.recyclerview;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.evgenia.ya_tr_ap.R;
import com.example.evgenia.ya_tr_ap.presentation_layer.hisroty.HistoryFavorModel;

import java.util.ArrayList;


/**
 * Created by User on 17.04.2017.
 */

public class RvEnterTextAdapter extends RecyclerView.Adapter<RvEnterTextAdapter.VhEnterText> {

    public interface RvEnterTextAdapterListener{
        void onClickTranslate(String textToTranslate);
    }


    private ArrayList<String> history;
    private RvEnterTextAdapterListener listener;

    public RvEnterTextAdapter(ArrayList<String> history, RvEnterTextAdapterListener listener) {
        this.history = new ArrayList<>();
        this.history.add("");
        this.history.addAll(history);
        this.listener = listener;
    }

    @Override
    public VhEnterText onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_translate_text_item, parent, false);
        return new VhEnterText(root);
    }

    @Override
    public void onBindViewHolder(VhEnterText holder, int position) {
        holder.onBind(history.get(position));
    }

    @Override
    public int getItemCount() {
        return history.size();
    }


    public void addItem(String str) {
        this.history.add(1, "");
        notifyDataSetChanged();
    }

    public void removeItem(int position){
        this.history.remove(position);
        notifyItemRemoved(position);
    }



    /*=========== VIEW HOLDER =================*/

    public class VhEnterText extends RecyclerView.ViewHolder implements TextWatcher, View.OnClickListener{
        private static final String TAG = "VhEnterText";
        private final RelativeLayout rlButtons;
        private final ImageView ivClear;
        private final ImageView ivTranslate;
        public EditText editText;

        public VhEnterText(View v) {
            super(v);

            editText = (EditText)v.findViewById(R.id.et_text);
            editText.addTextChangedListener(this);
//            editText.setOnTouchListener(touch);

            rlButtons = (RelativeLayout)v.findViewById(R.id.rl_buttons);

            ivClear = (ImageView)v.findViewById(R.id.iv_clear);
            ivClear.setOnClickListener(this);
//            ivClear.setOnTouchListener(touch);

            ivTranslate = (ImageView)v.findViewById(R.id.iv_translate);
            ivTranslate.setOnClickListener(this);
//            ivTranslate.setOnTouchListener(touch);

        }

        public void onBind(String text){
            if(text == null || text.length() == 0){
                Log.d("tag", "onBind: empty");
                editText.setText("");
            }else {
                editText.setText(text);
            }
        }


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Log.d(TAG, "beforeTextChanged: ");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.d(TAG, "onTextChanged: ");
            if(s == null || s.length() == 0){
                rlButtons.setVisibility(View.GONE);
            }else {
                rlButtons.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.d(TAG, "afterTextChanged: ");
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_clear:
                    editText.setText("");
                    break;
                case R.id.iv_translate:
                    if(listener != null){
                        listener.onClickTranslate(editText.getText().toString());
                    }
                    break;
            }
        }


        public View.OnTouchListener touch = new View.OnTouchListener() {
            private static final String TAG = "VhEnterText";
            float py1 = 0;
            float py2 = 0;
            float px1 = 0;
            float px2 = 0;
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                Log.d(TAG, "onTouch: ");
                v.getParent().requestDisallowInterceptTouchEvent(true);

//                    if(v.getId() == R.id.et_text || v.getId() == R.id.rl_buttons){
                Log.d(TAG, "onTouch: v.getId() " + v.getId());
                switch (motionEvent.getAction() & MotionEvent.ACTION_MASK){

                    case MotionEvent.ACTION_UP:
                        Log.d(TAG, "onTouch: ACTION_UP");
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        py1 = py2;
                        px1 = px2;
                        py2 = motionEvent.getY();
                        px2 = motionEvent.getX();
                        Log.d(TAG, "onTouch: ACTION_UP  py2 = " +  py2 + "  py1= " +  py1 );
                        Log.d(TAG, "onTouch: ACTION_UP  px2 = " +  px2 + "  px1= " +  px1 );
                        if(Math.abs(px1-px2) <= Math.abs(py2 - py1)) {
                            v.getParent().requestDisallowInterceptTouchEvent(true);
                        }else {
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        break;
                    case MotionEvent.ACTION_DOWN:
                        Log.d(TAG, "onTouch: ACTION_DOWN");
                        break;
                    case MotionEvent.AXIS_HSCROLL:
                        Log.d(TAG, "onTouch: AXIS_HSCROLL");
                        break;
                    case MotionEvent.ACTION_SCROLL:
                        Log.d(TAG, "onTouch: ACTION_SCROLL");
                        break;
                    case MotionEvent.AXIS_VSCROLL:
                        Log.d(TAG, "onTouch: AXIS_VSCROLL");
                        break;
                    case MotionEvent.ACTION_OUTSIDE:
                        Log.d(TAG, "onTouch: ACTION_OUTSIDE");
                        break;


                }
//                    }
                return false;
            }
        };
    }
}
