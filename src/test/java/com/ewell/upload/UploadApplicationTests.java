package com.ewell.upload;

import com.ewell.upload.bean.FybInpTotal;
import com.ewell.upload.bean.FybMaternalDelivery;
import com.ewell.upload.bean.FybWomanCheck;
import com.ewell.upload.dao.FybInpTotalDao;
import com.ewell.upload.dto.BaseRequest;
import com.ewell.upload.dto.BaseResponse;
import com.ewell.upload.dto.data.LoginToken;
import com.ewell.upload.dto.data.WomanDelivery;
import com.ewell.upload.dto.data.push.PushPerson;
import com.ewell.upload.quartz.task.PushExamTask;
import com.ewell.upload.quartz.util.QuartzJobListener;
import com.ewell.upload.service.FybPushExamService;
import com.ewell.upload.service.FybPushLabService;
import com.ewell.upload.service.FybRecordCardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UploadApplicationTests {
    @Resource
    private FybPushLabService service;
    @Resource
    private FybRecordCardService recordService;
    @Resource
    private ThreadPoolTaskExecutor executer;
    @Resource
    private FybPushExamService examService;
    @Resource
    PushExamTask pushExamTask;
    @Resource
    private FybInpTotalDao fybInpTotalDao;
    @Test
    public void inpUpload(){

    }
    @Test
    public void test05(){
        BaseResponse<List<PushPerson>> examObject = examService.queryPersonWcQtjc();
        examObject.getData().forEach(obj->{
            try {
                pushExamTask.taskMonitorEvent(obj);
            }catch (Exception e){
                e.printStackTrace();
            }

        });
    }
    private void getToken(){
        if (QuartzJobListener.token == null) {
            //String loginStr = mchis.getMchisHttpSoap11Endpoint().login("330327198908300222", "123");
            //BaseResponse<LoginToken> res = JacksonUtil.json2Bean(loginStr, new TypeReference<BaseResponse<LoginToken>>() {
            QuartzJobListener.token = new LoginToken();
            QuartzJobListener.token.setToken("320211196412053427");
            QuartzJobListener.token.setInputOrganCode("320000014000204");
        }
    }

    private BaseRequest<FybWomanCheck> getWomanCheck(String healthNo,String idNo,String outp,String patId,String sysId,String date){
        BaseRequest<FybWomanCheck> req = new BaseRequest<>();
        FybWomanCheck womanCheck = new FybWomanCheck();
        womanCheck.setPatientNo(patId);
        womanCheck.setOutpatientNo(outp);
        womanCheck.setSysId("");
        womanCheck.setDbKey("0");
        womanCheck.setTbKey("0");
        womanCheck.setSrc("无锡人民医院");
        womanCheck.setExt("");
        womanCheck.setUploadTime("2019-05-15");
        womanCheck.setOrgan("江苏省卫生和计划生育委员会");
        womanCheck.setOrganCode("320000014000204");
        womanCheck.setDoctor("管理员");
        womanCheck.setDoctorCode("110101199003074258");
        womanCheck.setCheckDate(date);
        womanCheck.setInputOrgan("江苏省卫生和计划生育委员会");
        womanCheck.setInputOrganCode("320000014000204");
        womanCheck.setInputDoctor("管理员");
        womanCheck.setInputDoctorCode("110101199003074258");
        womanCheck.setInputDate("2019-05-15");
        womanCheck.setHealthNo(healthNo);
        womanCheck.setName("郑琛琛");
        womanCheck.setIdcard(idNo);
        womanCheck.setBirthday("1989-08-30");
        womanCheck.setCardType("1");
        womanCheck.setCardNo("330327198908300222");
        womanCheck.setCheckSeq("1");
        womanCheck.setFirstCheck("0");
        womanCheck.setLmp("2019-04-01");
        womanCheck.setExpectedDate("2019-08-08");
        womanCheck.setGestWeeks("10");
        womanCheck.setGestDays("1");
        womanCheck.setHeight("172");
        womanCheck.setWeight("50");
        womanCheck.setSbp("100");
        womanCheck.setDbp("70");
        womanCheck.setFu("11");
        womanCheck.setAc("123");
        womanCheck.setMainSuit("sfasfsd");
        womanCheck.setSelfStatus("健康");
        womanCheck.setHeart("1");
        womanCheck.setLung("1");
        womanCheck.setCunnus("1");
        womanCheck.setCunnusCont("1");
        womanCheck.setVagina("1");
        womanCheck.setVaginaCont("1");
        womanCheck.setCervix("1");
        womanCheck.setCervixCont("1");
        womanCheck.setUterus("1");
        womanCheck.setAcco("1");
        womanCheck.setAccoCont("1");
        womanCheck.setFetalPosition("01");
        womanCheck.setFetalHr("999");
        womanCheck.setAdvise("体能好");
        womanCheck.setGuideLive("0");
        womanCheck.setGuidePsycho("0");
        womanCheck.setGuideNutri("0");
        womanCheck.setGuideFood("0");
        womanCheck.setGuideSports("0");
        womanCheck.setGuideMonitor("0");
        womanCheck.setGuidePrepare("0");
        womanCheck.setGuideFeed("0");
        womanCheck.setGuideDeformity("0");
        womanCheck.setGuideScreening("0");
        womanCheck.setGuideOther("0");
        womanCheck.setTran("0");
        womanCheck.setTranDate("");
        womanCheck.setTranDateHour("");
        womanCheck.setTranDateMinute("");
        womanCheck.setTranReason("");
        womanCheck.setTranOrgan("");
        womanCheck.setTranOrganCode("");
        womanCheck.setNextCheck("0");
        womanCheck.setNextDate("");
        womanCheck.setNextCares("");
        womanCheck.setNextScreen("0");
        womanCheck.setNextScreenDate("");
        req.setData(womanCheck);
        req.setSource("womanCheck");
        req.setOperate("save");
        req.setRemark("孕妇产检");
        return req;
    }
    //@Test
    public void test04(){System.out.println("11111");}
    //@Test
    public void contextLoads() {
        getToken();
        BaseResponse<List<PushPerson>> personList = service.queryPersonWcFzjc();
        List<PushPerson> perList = new ArrayList<>();

        if ("success".equals(personList.getResult())){
            for(int i=0;i<personList.getData().size();i++){
                if ("3202131934091".equals(personList.getData().get(i).getHealthNo())){
                    PushPerson per1 =personList.getData().get(i);
                    perList.add(per1);
                }
            }
        }else{
            /*            per = new PushPerson();
            per.setPatientNo("2000137183");
            per.setBirthday("1994-06-30");
            per.setCardNo("330327198908300222");
            per.setCardType("1");
            per.setCheckDate("2019-05-14");
            per.setExt("");
            per.setHealthNo("3202131934091");
            per.setIdcard("330327198908300222");
            per.setName("陆泉云");
            per.setOrgan(QuartzJobListener.token.getInputOrganName());
            per.setOrganCode(QuartzJobListener.token.getInputOrganCode());
            */
        }
        perList.forEach(obj->{
            Boolean b = service.saveWcFzjc(obj);
        });
        //String s = "{\"source\":\"comLis\",\"remark\":\"saveWcFzjc\",\"operate\":\"围产辅助检查\",\"data\":{\"person\":{\"organCode\":\"320000014000204\",\"organ\":\"江苏省卫生和计划生育委员会\",\"refType\":null,\"ext\":null,\"checkDate\":null,\"healthNo\":null,\"name\":null,\"idcard\":null,\"birthday\":null,\"sex\":null,\"cardType\":null,\"cardNo\":null,\"patientNo\":\"2004125449\",\"outpatientNo\":null},\"result\":[{\"itemCode\":\"#MID\",\"itemResult\":\"0.31\"},{\"itemCode\":\"MCHC\",\"itemResult\":\"338\"},{\"itemCode\":\"#LYM\",\"itemResult\":\"0.91\"},{\"itemCode\":\"RDW\",\"itemResult\":\"13.9\"},{\"itemCode\":\"HGB\",\"itemResult\":\"127\"},{\"itemCode\":\"MCH\",\"itemResult\":\"29.2\"},{\"itemCode\":\"WBC\",\"itemResult\":\"3.40\"},{\"itemCode\":\"MCV\",\"itemResult\":\"86.3\"},{\"itemCode\":\"HCT\",\"itemResult\":\"0.37\"},{\"itemCode\":\"#BASO\",\"itemResult\":\"0.04\"},{\"itemCode\":\"PLT\",\"itemResult\":\"127\"},{\"itemCode\":\"#EOS\",\"itemResult\":\"0.10\"},{\"itemCode\":\"_GRAN\",\"itemResult\":\"0.601\"},{\"itemCode\":\"_MID\",\"itemResult\":\"0.090\"},{\"itemCode\":\"_EOS\",\"itemResult\":\"0.028\"},{\"itemCode\":\"#GRAN\",\"itemResult\":\"2.04\"},{\"itemCode\":\"MPV\",\"itemResult\":\"8.5\"},{\"itemCode\":\"RBC\",\"itemResult\":\"4.33\"},{\"itemCode\":\"PDW\",\"itemResult\":\"15.9\"},{\"itemCode\":\"_LYM\",\"itemResult\":\"0.268\"},{\"itemCode\":\"PCT\",\"itemResult\":\"0.108\"},{\"itemCode\":\"_BASO\",\"itemResult\":\"0.013\"},{\"itemCode\":\"PRO\",\"itemResult\":\"阴性\"},{\"itemCode\":\"GLU\",\"itemResult\":\"阴性\"},{\"itemCode\":\"CLR\",\"itemResult\":\"透明\"},{\"itemCode\":\"COL\",\"itemResult\":\"黄色\"},{\"itemCode\":\"NIT\",\"itemResult\":\"阴性\"},{\"itemCode\":\"BIL\",\"itemResult\":\"阴性\"},{\"itemCode\":\"URO\",\"itemResult\":\"阴性\"},{\"itemCode\":\"KET\",\"itemResult\":\"阴性\"},{\"itemCode\":\"#BASO\",\"itemResult\":\"0.08\"},{\"itemCode\":\"#EOS\",\"itemResult\":\"0.13\"},{\"itemCode\":\"_MID\",\"itemResult\":\"0.029\"},{\"itemCode\":\"#MID\",\"itemResult\":\"0.28\"},{\"itemCode\":\"MPV\",\"itemResult\":\"8.0\"},{\"itemCode\":\"PDW\",\"itemResult\":\"16.1\"},{\"itemCode\":\"PCT\",\"itemResult\":\"0.159\"},{\"itemCode\":\"MCHC\",\"itemResult\":\"341\"},{\"itemCode\":\"RDW\",\"itemResult\":\"14.1\"},{\"itemCode\":\"MCH\",\"itemResult\":\"29.7\"},{\"itemCode\":\"MCV\",\"itemResult\":\"87.1\"},{\"itemCode\":\"HCT\",\"itemResult\":\"0.37\"},{\"itemCode\":\"_GRAN\",\"itemResult\":\"0.767\"},{\"itemCode\":\"#GRAN\",\"itemResult\":\"7.44\"},{\"itemCode\":\"_LYM\",\"itemResult\":\"0.183\"},{\"itemCode\":\"#LYM\",\"itemResult\":\"1.78\"},{\"itemCode\":\"WBC\",\"itemResult\":\"9.70\"},{\"itemCode\":\"PLT\",\"itemResult\":\"200\"},{\"itemCode\":\"RBC\",\"itemResult\":\"4.27\"},{\"itemCode\":\"HGB\",\"itemResult\":\"127\"},{\"itemCode\":\"_EOS\",\"itemResult\":\"0.013\"},{\"itemCode\":\"_BASO\",\"itemResult\":\"0.008\"},{\"itemCode\":\"TSH\",\"itemResult\":\"1.29\"},{\"itemCode\":\"FT4\",\"itemResult\":\"11.09\"},{\"itemCode\":\"YSGXTIGG\",\"itemResult\":\"阴性\"},{\"itemCode\":\"YSGXTIGM\",\"itemResult\":\"阴性\"},{\"itemCode\":\"YSFZIGG\",\"itemResult\":\"弱阳性\"},{\"itemCode\":\"YSJXBIGM\",\"itemResult\":\"阴性\"},{\"itemCode\":\"_BASO\",\"itemResult\":\"0.000\"},{\"itemCode\":\"#BASO\",\"itemResult\":\"0.00\"},{\"itemCode\":\"HGB\",\"itemResult\":\"122\"},{\"itemCode\":\"RBC\",\"itemResult\":\"4.11\"},{\"itemCode\":\"PLT\",\"itemResult\":\"178\"},{\"itemCode\":\"WBC\",\"itemResult\":\"9.10\"},{\"itemCode\":\"#LYM\",\"itemResult\":\"1.36\"},{\"itemCode\":\"_LYM\",\"itemResult\":\"0.149\"},{\"itemCode\":\"#GRAN\",\"itemResult\":\"7.37\"},{\"itemCode\":\"_GRAN\",\"itemResult\":\"0.810\"},{\"itemCode\":\"HCT\",\"itemResult\":\"0.36\"},{\"itemCode\":\"MCV\",\"itemResult\":\"88.4\"},{\"itemCode\":\"MCH\",\"itemResult\":\"29.7\"},{\"itemCode\":\"RDW\",\"itemResult\":\"12.5\"},{\"itemCode\":\"MCHC\",\"itemResult\":\"336\"},{\"itemCode\":\"PCT\",\"itemResult\":\"0.147\"},{\"itemCode\":\"PDW\",\"itemResult\":\"16.2\"},{\"itemCode\":\"MPV\",\"itemResult\":\"8.3\"},{\"itemCode\":\"#MID\",\"itemResult\":\"0.32\"},{\"itemCode\":\"_MID\",\"itemResult\":\"0.035\"},{\"itemCode\":\"#EOS\",\"itemResult\":\"0.05\"},{\"itemCode\":\"_EOS\",\"itemResult\":\"0.006\"}]}}";
        //String resStr = mchis.getMchisHttpSoap11Endpoint().saveData("0553d8a40af24b8d86c190393c88b9d6",s);
        //BaseResponse res = JacksonUtil.json2Bean(resStr, new TypeReference<BaseResponse>() {});
        //System.out.println(res);
    }




}

