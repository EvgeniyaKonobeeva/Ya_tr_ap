package com.example.evgenia.ya_tr_ap.translate.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.evgenia.ya_tr_ap.R;
import com.example.evgenia.ya_tr_ap.hisroty.HistoryFavorModel;

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
        private final RelativeLayout rlButtons;
        private final ImageView ivClear;
        private final ImageView ivTranslate;
        public EditText editText;

        public VhEnterText(View v) {
            super(v);

            editText = (EditText)v.findViewById(R.id.et_text);
            editText.addTextChangedListener(this);
            editText.setMovementMethod(new ScrollingMovementMethod());

            editText.setOnTouchListener(new View.OnTouchListener() {
                private static final String TAG = "VhEnterText";
                private float currXPos = 0;
                private float prevXPos = 0;
                @Override
                public boolean onTouch(View v, MotionEvent motionEvent) {
                    Log.d(TAG, "onTouch: ");
                    if(v.getId() == R.id.et_text ){
                        Log.d(TAG, "onTouch: editText.hasFocus()" + editText.hasFocus());
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK){
                            case MotionEvent.ACTION_UP:
                                Log.d(TAG, "onTouch: ACTION_UP");
                                v.getParent().requestDisallowInterceptTouchEvent(false);
                                break;
                            case MotionEvent.ACTION_DOWN:

                                prevXPos = motionEvent.getX();
                                Log.d(TAG, "onTouch: ACTION_DOWN " + prevXPos);

                                break;
                            case MotionEvent.ACTION_MOVE:

                                currXPos = motionEvent.getX();
                                Log.d(TAG, "onTouch: ACTION_MOVE " + currXPos);

                                if(Math.abs(currXPos - prevXPos) > 3f){
                                    v.getParent().requestDisallowInterceptTouchEvent(false);
                                }
                                break;
                        }
                    }
                    return false;
                }
            });

            rlButtons = (RelativeLayout)v.findViewById(R.id.rl_buttons);

            ivClear = (ImageView)v.findViewById(R.id.iv_clear);
            ivClear.setOnClickListener(this);

            ivTranslate = (ImageView)v.findViewById(R.id.iv_translate);
            ivTranslate.setOnClickListener(this);

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
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(s == null || s.length() == 0){
                rlButtons.setVisibility(View.GONE);
            }else {
                rlButtons.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
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
    }
}
