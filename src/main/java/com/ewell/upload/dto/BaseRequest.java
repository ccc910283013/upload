package com.ewell.upload.dto;

public class BaseRequest<T> {
    private String source;
    private String remark;
    private String operate;
    private T data;

    @Override
    public String toString() {
        return "BaseRequest{" +
                "source='" + source + '\'' +
                ", remark='" + remark + '\'' +
                ", operate='" + operate + '\'' +
                ", data=" + data +
                '}';
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
