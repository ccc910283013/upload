package com.ewell.upload.service;

import com.ewell.upload.bean.FybInpInfo;
import com.ewell.upload.dto.BaseResponse;


import java.util.List;

public interface FybInpService {

    Boolean fyRecordDeal(BaseResponse<FybInpInfo> fybInpInfo);

    List<BaseResponse<FybInpInfo>> cardEventDeal();
}
