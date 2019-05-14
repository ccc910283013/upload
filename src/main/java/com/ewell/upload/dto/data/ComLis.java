package com.ewell.upload.dto.data;


import com.ewell.upload.dto.data.sub.PushLabItem;
import com.ewell.upload.dto.data.sub.PushPerson;

import java.util.List;

public class ComLis {
    private PushPerson person;
    private List<PushLabItem> result;

    @Override
    public String toString() {
        return "ComLis{" +
                "person=" + person +
                ", result=" + result +
                '}';
    }

    public PushPerson getPerson() {
        return person;
    }

    public void setPerson(PushPerson person) {
        this.person = person;
    }

    public List<PushLabItem> getResult() {
        return result;
    }

    public void setResult(List<PushLabItem> result) {
        this.result = result;
    }
}
