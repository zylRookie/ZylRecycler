package com.zyl.mlibrary;

import android.content.Context;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

/**
 * RecyclerView通用的ViewHodler
 */
public class ZylRViewHolder extends ZylViewHolder {

    public ZylRViewHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<>();

        mConvertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(ZylRViewHolder.this, null, v, getPosition());
                }
            }
        });

        mConvertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null) {
                    return mOnItemLongClickListener.onItemLongClick(ZylRViewHolder.this, null, v, getPosition());
                }
                return false;
            }
        });

        mConvertView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mOnItemTouchListener != null) {
                    return mOnItemTouchListener.onItemTouch(ZylRViewHolder.this, v, event, getPosition());
                }
                return false;
            }
        });

    }
}
