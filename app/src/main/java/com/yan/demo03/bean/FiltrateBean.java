package com.yan.demo03.bean;

import java.util.List;

/**
 * Created by zhangyan 2021/01/29
 */
public class FiltrateBean {

    // 模块名字
    private String typeName;

    // child
    private List<Children> children;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<Children> getChildren() {
        return children;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }

    public static class Children {
        // 文本
        private String value;
        // child的id
        private int id;
        // child是否选择
        private boolean isSelected = false;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }

}
