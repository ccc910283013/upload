package com.ewell.upload.dto.data;

import com.ewell.upload.dto.data.push.PushPerson;
import lombok.Data;

import java.util.List;
@Data
public class ComPush<T> {
    private PushPerson person;
    private List<T> result;
}
