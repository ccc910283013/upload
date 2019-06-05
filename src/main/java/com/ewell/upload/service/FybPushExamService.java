package com.ewell.upload.service;


import com.ewell.upload.dto.BaseResponse;
import com.ewell.upload.dto.data.sub.PushPerson;

import java.util.List;

public interface FybPushExamService {
    BaseResponse<List<PushPerson>> queryPersonWcQtjc();
    boolean saveWcQtjc(PushPerson person);
}
