package com.ewell.upload.dto.data.sub;

public class PushLabItem {
    private String itemCode;
    private String itemResult;

    @Override
    public String toString() {
        return "PushLabItem{" +
                "itemCode='" + itemCode + '\'' +
                ", itemResult='" + itemResult + '\'' +
                '}';
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemResult() {
        return itemResult;
    }

    public void setItemResult(String itemResult) {
        this.itemResult = itemResult;
    }
}
