package com.ewell.upload.service;

import com.ewell.upload.bean.FybOutInfo;
import com.ewell.upload.dto.BaseResponse;

import java.util.List;

public interface FybRecordCardService {

    List<BaseResponse<FybOutInfo>> cardEventDeal();

    boolean fyRecordDeal(BaseResponse<FybOutInfo> info);
    List<BaseResponse<FybOutInfo>> test();






}
