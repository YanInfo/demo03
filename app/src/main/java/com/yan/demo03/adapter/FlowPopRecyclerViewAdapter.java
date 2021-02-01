package com.yan.demo03.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.yan.demo03.R;
import com.yan.demo03.bean.FiltrateBean;
import com.yan.demo03.view.SkuFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyan 2021/01/29
 */
public class FlowPopRecyclerViewAdapter extends RecyclerView.Adapter<FlowPopRecyclerViewAdapter.FlowPopViewHolder> {

    private Context mContext;
    private List<FiltrateBean> mData;

    public FlowPopRecyclerViewAdapter(Context context) {
        mData = new ArrayList<>();
        this.mContext = context;
    }

    @NonNull
    @Override
    public FlowPopViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        FlowPopViewHolder viewHolder = new FlowPopViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_listview_property, viewGroup, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FlowPopRecyclerViewAdapter.FlowPopViewHolder viewHolder, int i) {
        FlowPopViewHolder vh = (FlowPopViewHolder) viewHolder;
        FiltrateBean item = mData.get(i);
        vh.tvTypeName.setText(item.getTypeName());
        // 设置子View
        setFlowLayoutData(item.getChildren(), vh.layoutProperty);

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class FlowPopViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTypeName;
        private SkuFlowLayout layoutProperty;

        public FlowPopViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTypeName = itemView.findViewById(R.id.tv_type_name);
            layoutProperty = itemView.findViewById(R.id.layout_property);
        }
    }

    private void setFlowLayoutData(final List<FiltrateBean.Children> childrenList, final SkuFlowLayout flowLayout) {

        flowLayout.removeAllViews();
        for (int x = 0; x < childrenList.size(); x++) {
            CheckBox checkBox = (CheckBox) View.inflate(mContext, R.layout.item_flowlayout_bill, null);
            checkBox.setText(childrenList.get(x).getValue());

            if (childrenList.get(x).isSelected()) {
                checkBox.setChecked(true);
                childrenList.get(x).setSelected(true);
            } else {
                checkBox.setChecked(false);
                childrenList.get(x).setSelected(false);
            }

            final int finalX = x;
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refreshCheckBox(flowLayout, finalX, childrenList);
                }
            });
            flowLayout.addView(checkBox);
        }
    }

    private void refreshCheckBox(SkuFlowLayout flowLayout, int finalX, List<FiltrateBean.Children> propBeenList) {
        for (int y = 0; y < flowLayout.getChildCount(); y++) {
            CheckBox radio = (CheckBox) flowLayout.getChildAt(y);
            radio.setChecked(false);
            propBeenList.get(y).setSelected(false);
            if (finalX == y) {
                radio.setChecked(true);
                propBeenList.get(y).setSelected(true);
            }
        }
    }

    /**
     * 设置数据
     * @param data
     */
    public void setData(List<FiltrateBean> data){
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clearData(){
        mData.clear();
        notifyDataSetChanged();
    }
}
