package com.yan.demo03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yan.demo03.bean.FiltrateBean;
import com.yan.demo03.view.FlowPopWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyan 2021/01/26
 */
public class MainActivity extends AppCompatActivity {

    private ImageView mIvBack;
    private TextView mTvFlow;

    // 弹框
    private FlowPopWindow mPopWindow;
    // 弹框数据bean
    private List<FiltrateBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initParam();
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mIvBack = findViewById(R.id.iv_back);
        mTvFlow = findViewById(R.id.tv_flow);
        mTvFlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPopWindow == null) {
                    mPopWindow = new FlowPopWindow(MainActivity.this, mList);
                }
                mPopWindow.showAsDropDown(mIvBack);
                mPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnButtonClickListener() {

                    // 确定
                    @Override
                    public void onConfirmClick() {
                        StringBuilder sb = new StringBuilder();
                        for (FiltrateBean fb : mList) {
                            List<FiltrateBean.Children> cdList = fb.getChildren();
                            for (int x = 0; x < cdList.size(); x++) {
                                FiltrateBean.Children children = cdList.get(x);
                                if (children.isSelected())
                                    sb.append(fb.getTypeName() + ":" + children.getValue() + "；");
                            }
                        }
                        if (!TextUtils.isEmpty(sb.toString()))
                            Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_LONG).show();
                    }

                    // 重置
                    @Override
                    public void onResetClick() {
                        for (int x = 0; x < mList.size(); x++) {
                            List<FiltrateBean.Children> childrenBeen = mList.get(x).getChildren();
                            for (int y=0;y<childrenBeen.size();y++){
                                if (childrenBeen.get(y).isSelected())
                                    childrenBeen.get(y).setSelected(false);
                            }
                        }
                    }
                });

            }
        });

    }

    /**
     * 模拟数据，真实项目中直接接口获取添加进来，FiltrateBean对象可根据自己需求更改
     */
    private void initParam() {
        String[] sexs = {"男", "女"};
        String[] colors = {"红色", "浅黄色", "橙子色", "鲜绿色", "青色", "天蓝色", "紫色", "黑曜石色", "白色", "很多很多颜色很多颜色"};
        String[] company = {"贵州茅台", "五粮液", "泸州老窖", "郎酒", "山西汾酒", "白兰地"};

        FiltrateBean fb1 = new FiltrateBean();
        fb1.setTypeName("性别");
        List<FiltrateBean.Children> childrenList = new ArrayList<>();
        for (int x = 0; x < sexs.length; x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(sexs[x]);
            childrenList.add(cd);
        }
        fb1.setChildren(childrenList);

        FiltrateBean fb2 = new FiltrateBean();
        fb2.setTypeName("颜色");
        List<FiltrateBean.Children> childrenList2 = new ArrayList<>();
        for (int x = 0; x < colors.length; x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(colors[x]);
            childrenList2.add(cd);
        }
        fb2.setChildren(childrenList2);

        FiltrateBean fb3 = new FiltrateBean();
        fb3.setTypeName("品牌");
        List<FiltrateBean.Children> childrenList3 = new ArrayList<>();
        for (int x = 0; x < company.length; x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(company[x]);
            childrenList3.add(cd);
        }
        fb3.setChildren(childrenList3);

        mList.add(fb1);
        mList.add(fb2);
        mList.add(fb3);
    }
}
