package com.ewell.upload.service;


import com.ewell.upload.dto.BaseResponse;
import com.ewell.upload.dto.data.sub.PushPerson;

import java.util.List;

public interface FybPushLabService {
    BaseResponse<List<PushPerson>> queryPersonWcFzjc();
    boolean saveWcFzjc(PushPerson person);
}
