package com.ewell.upload.dao;

import com.ewell.upload.bean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface FybOutTotalDao {
    /**
     * 查询初查和复查的孕妇信息列表
     * @return 查询结果
     */
    List<FybOutTotal> select();

    /**
     * 更新fybOutTotal所有病人的身份证号信息
     * @return
     */
    int update();

    /**
     * 更新数据处理状态
     * @param fybOutTotals
     * @return
     */
    int updateDealStatus(List<FybOutTotal> fybOutTotals);
    /**
     * 查询是否存在对应的妇幼保健卡号
     * @param outpNo 门诊号
     * @return 查询结果
     */
    FybOutInfo selectHealthNoByOutpNo(String outpNo);

    /**
     * 获取妇幼建卡信息
     * @param patientId 病人ID
     * @param outpCheckNo 门诊号
     * @return 查询结果
     */
    FybWomanMain selectWomanMain(@Param(value = "patientId") String patientId, @Param(value = "outpCheckNo")String outpCheckNo);

    /**
     * 更新上传异常信息
     * @param patientId 病人ID
     * @param outpCheckNo 门诊号
     * @param exception 异常信息
     * @param dealStatus 处理状态
     * @return 行数
     */
    int updateErrorMessage(@Param(value = "patientId") String patientId,
                           @Param(value = "outpCheckNo")String outpCheckNo,
                           @Param(value = "exception")String exception,
                           @Param(value = "status")String dealStatus);

    /**
     * 插入建档信息
     * @param info 建档信息
     * @return 行数
     */
    int insertOutpInfo(FybOutInfo info);

    /**
     * 删除待上传病人信息
     * @param outp 门诊号
     * @return
     */
    int deleteByOutp(String outp);

    /**
     * 获取病人产检信息
     * @param outp 门诊号
     * @return
     */
    FybWomanCheck selectWomanCheckByOutpId(String outp);



}
