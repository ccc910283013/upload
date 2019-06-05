package com.ewell.upload.dao;

import com.ewell.upload.bean.FybRisItemInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface FybRisInfoDao {
     List<FybRisItemInfo> selectByOutpId(String outpId);
}
