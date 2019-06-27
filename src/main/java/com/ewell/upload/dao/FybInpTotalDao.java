package com.ewell.upload.dao;

import com.ewell.upload.bean.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface FybInpTotalDao {
    /**
     * 查询所有待处理病历
     * @return  list结果集
     */
    @Select("SELECT * FROM IHD.FYB_INP_TODAT WHERE DEAL_STATUS IS NULL AND ROWNUM < 1000")
    List<FybInpTotal> select();

    /**
     * 删除待上传病历
     * @param inpNo 病案号
     * @return 更新行数
     */
    @Delete("DELETE FROM IHD.FYB_INP_TODAT WHERE INP_NO = #{_parameter}")
    int deleteByInpNo(String inpNo);

    /**
     * 将事件改为待处理
     * @param fybInpTotals 待处理事件
     * @return 更新行数
     */
    int updateDealStatus(List<FybInpTotal> fybInpTotals);

    /**
     * 更新异常信息
     * @param inpNo 住院号
     * @param exception 异常信息
     * @return 更新行数
     */
    @Update("UPDATE IHD.FYB_INP_INFO T SET T.EXCEPTION = #{exception},T.DEAL_STATUS = #{dealStatus} WHERE T.INP_NO = #{inpNo}")
    int updateException(@Param(value = "inpNo") String inpNo,
                        @Param(value = "exception") String exception,
                        @Param(value = "dealStatus") String dealStatus);

    /**
     * 处理完的事件转移
     * @param fybInpInfo 待转移事件
     * @return 更新行数
     */
    @Insert("INSERT INTO IHD.FYB_INP_INFO(INP_NO,SAVE_TIME,HEALTHCARE_NO,SYS_ID,ID_NO,EXCEPTION,DEAL_STATUS) VALUES(#{inpNo,jdbcType=VARCHAR}," +
            "TO_DATE(#{saveTime,jdbcType=VARCHAR},'yyyy-mm-dd hh24:mi:ss'),#{healthcareNo,jdbcType=NUMERIC}," +
            "#{sysId,jdbcType=VARCHAR},#{idNo,jdbcType=VARCHAR},#{exception,jdbcType=VARCHAR},#{dealStatus,jdbcType=NUMERIC})")
    int insertInpInfo(FybInpInfo fybInpInfo);

    /**
     * 获取孕妇分娩信息
     * @return list结果集
     */
    @Select("SELECT * FROM IHD.FYB_MATERNAL_DELIVERY T WHERE T.INP_NO = #{_parameter}")
    FybMaternalDelivery selectWomanDelivery(String inpNo);

    /**
     * 获取新生儿信息
     * @param inpNo 住院号
     * @return list结果集
     */
    @Select("SELECT * FROM IHD.FYB_NEW_BORN WHERE INP_NO = #{_parameter}")
    List<FybNewBorn> selectAllNewBorn(String inpNo);

    /**
     * 查询是否存在对应的妇幼保健卡号
     * @param inpNo 住院号
     * @return 查询结果
     */
    @Select("SELECT * FROM IHD.FYB_INP_INFO WHERE INP_NO = #{_parameter}")
    FybInpInfo selectHealthNoByInpNo(String inpNo);

}
