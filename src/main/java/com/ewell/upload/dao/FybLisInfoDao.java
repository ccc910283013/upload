package com.ewell.upload.dao;

import com.ewell.upload.bean.FybLisItemInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface FybLisInfoDao {
     List<FybLisItemInfo> selectByOutpId(String outpId);
}
