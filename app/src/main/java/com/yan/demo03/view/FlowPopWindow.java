package com.yan.demo03.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yan.demo03.R;
import com.yan.demo03.adapter.FlowPopRecyclerViewAdapter;
import com.yan.demo03.bean.FiltrateBean;

import java.util.List;


/**
 * Created by zhangyan 2021/01/27
 */

public class FlowPopWindow extends PopupWindow {

    private Context mContext;
    private List<FiltrateBean> mList;
    private CustomHeightRecyclerView mRecyclerView;
    private TextView mTvReset;
    private TextView mTvConfirm;
    private View mNullView;
    private FlowPopRecyclerViewAdapter mAdapter;
    private OnButtonClickListener onClickListener;

    public FlowPopWindow(Activity context, List<FiltrateBean> mList ) {
        this.mContext = context;
        this.mList = mList;
        initPop();
    }

    private void initPop() {
        View popView = View.inflate(mContext, R.layout.flow_pop_listview, null);
        //设置view
        this.setContentView(popView);
        //设置宽高（也可设置为LinearLayout.LayoutParams.MATCH_PARENT或者LinearLayout.LayoutParams.MATCH_PARENT）
        this.setWidth(-1);
        this.setHeight(-2);
        //设置PopupWindow的焦点
        this.setFocusable(true);
        //设置窗口以外的地方点击可关闭pop
        this.setOutsideTouchable(true);
        //设置背景透明
        this.setBackgroundDrawable(new ColorDrawable(0x33000000));

        mRecyclerView = popView.findViewById(R.id.listview);
        mTvReset = popView.findViewById(R.id.tv_reset);
        mTvConfirm = popView.findViewById(R.id.tv_confirm);
        mNullView = popView.findViewById(R.id.view_null);

        // 初始化adapter
        mAdapter = new FlowPopRecyclerViewAdapter(mContext);
        // 设置adapter数据
        mAdapter.setData(mList);
        // 设置LayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        // view填充
        mRecyclerView.setAdapter(mAdapter);

        mTvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onResetClick();
                mAdapter.notifyDataSetChanged();
            }
        });

        mTvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //自定义监听第三步
                onClickListener.onConfirmClick();
                dismiss();
            }
        });

        mNullView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    /**
     * 暴露的接口
     * @param onConfirmClickListener
     */
    public void setOnConfirmClickListener(OnButtonClickListener onConfirmClickListener){
        this.onClickListener=onConfirmClickListener;
    }

    public interface OnButtonClickListener{
        void onConfirmClick();
        void onResetClick();
    }

}
