package com.ewell.upload;

import com.ewell.upload.service.FybPushLabService;
import com.ewell.upload.webservice.FYClient.Mchis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UploadApplicationTests {
    @Autowired
    private Mchis mchis;
    @Autowired
    FybPushLabService service;
    @Test
    public void contextLoads() {
        //PushPerson per = new PushPerson();
        //per.setPatientNo("2004125449");
        //service.saveWcFzjc(per);
        //String s = "{\"source\":\"comLis\",\"remark\":\"saveWcFzjc\",\"operate\":\"围产辅助检查\",\"data\":{\"person\":{\"organCode\":\"320000014000204\",\"organ\":\"江苏省卫生和计划生育委员会\",\"refType\":null,\"ext\":null,\"checkDate\":null,\"healthNo\":null,\"name\":null,\"idcard\":null,\"birthday\":null,\"sex\":null,\"cardType\":null,\"cardNo\":null,\"patientNo\":\"2004125449\",\"outpatientNo\":null},\"result\":[{\"itemCode\":\"#MID\",\"itemResult\":\"0.31\"},{\"itemCode\":\"MCHC\",\"itemResult\":\"338\"},{\"itemCode\":\"#LYM\",\"itemResult\":\"0.91\"},{\"itemCode\":\"RDW\",\"itemResult\":\"13.9\"},{\"itemCode\":\"HGB\",\"itemResult\":\"127\"},{\"itemCode\":\"MCH\",\"itemResult\":\"29.2\"},{\"itemCode\":\"WBC\",\"itemResult\":\"3.40\"},{\"itemCode\":\"MCV\",\"itemResult\":\"86.3\"},{\"itemCode\":\"HCT\",\"itemResult\":\"0.37\"},{\"itemCode\":\"#BASO\",\"itemResult\":\"0.04\"},{\"itemCode\":\"PLT\",\"itemResult\":\"127\"},{\"itemCode\":\"#EOS\",\"itemResult\":\"0.10\"},{\"itemCode\":\"_GRAN\",\"itemResult\":\"0.601\"},{\"itemCode\":\"_MID\",\"itemResult\":\"0.090\"},{\"itemCode\":\"_EOS\",\"itemResult\":\"0.028\"},{\"itemCode\":\"#GRAN\",\"itemResult\":\"2.04\"},{\"itemCode\":\"MPV\",\"itemResult\":\"8.5\"},{\"itemCode\":\"RBC\",\"itemResult\":\"4.33\"},{\"itemCode\":\"PDW\",\"itemResult\":\"15.9\"},{\"itemCode\":\"_LYM\",\"itemResult\":\"0.268\"},{\"itemCode\":\"PCT\",\"itemResult\":\"0.108\"},{\"itemCode\":\"_BASO\",\"itemResult\":\"0.013\"},{\"itemCode\":\"PRO\",\"itemResult\":\"阴性\"},{\"itemCode\":\"GLU\",\"itemResult\":\"阴性\"},{\"itemCode\":\"CLR\",\"itemResult\":\"透明\"},{\"itemCode\":\"COL\",\"itemResult\":\"黄色\"},{\"itemCode\":\"NIT\",\"itemResult\":\"阴性\"},{\"itemCode\":\"BIL\",\"itemResult\":\"阴性\"},{\"itemCode\":\"URO\",\"itemResult\":\"阴性\"},{\"itemCode\":\"KET\",\"itemResult\":\"阴性\"},{\"itemCode\":\"#BASO\",\"itemResult\":\"0.08\"},{\"itemCode\":\"#EOS\",\"itemResult\":\"0.13\"},{\"itemCode\":\"_MID\",\"itemResult\":\"0.029\"},{\"itemCode\":\"#MID\",\"itemResult\":\"0.28\"},{\"itemCode\":\"MPV\",\"itemResult\":\"8.0\"},{\"itemCode\":\"PDW\",\"itemResult\":\"16.1\"},{\"itemCode\":\"PCT\",\"itemResult\":\"0.159\"},{\"itemCode\":\"MCHC\",\"itemResult\":\"341\"},{\"itemCode\":\"RDW\",\"itemResult\":\"14.1\"},{\"itemCode\":\"MCH\",\"itemResult\":\"29.7\"},{\"itemCode\":\"MCV\",\"itemResult\":\"87.1\"},{\"itemCode\":\"HCT\",\"itemResult\":\"0.37\"},{\"itemCode\":\"_GRAN\",\"itemResult\":\"0.767\"},{\"itemCode\":\"#GRAN\",\"itemResult\":\"7.44\"},{\"itemCode\":\"_LYM\",\"itemResult\":\"0.183\"},{\"itemCode\":\"#LYM\",\"itemResult\":\"1.78\"},{\"itemCode\":\"WBC\",\"itemResult\":\"9.70\"},{\"itemCode\":\"PLT\",\"itemResult\":\"200\"},{\"itemCode\":\"RBC\",\"itemResult\":\"4.27\"},{\"itemCode\":\"HGB\",\"itemResult\":\"127\"},{\"itemCode\":\"_EOS\",\"itemResult\":\"0.013\"},{\"itemCode\":\"_BASO\",\"itemResult\":\"0.008\"},{\"itemCode\":\"TSH\",\"itemResult\":\"1.29\"},{\"itemCode\":\"FT4\",\"itemResult\":\"11.09\"},{\"itemCode\":\"YSGXTIGG\",\"itemResult\":\"阴性\"},{\"itemCode\":\"YSGXTIGM\",\"itemResult\":\"阴性\"},{\"itemCode\":\"YSFZIGG\",\"itemResult\":\"弱阳性\"},{\"itemCode\":\"YSJXBIGM\",\"itemResult\":\"阴性\"},{\"itemCode\":\"_BASO\",\"itemResult\":\"0.000\"},{\"itemCode\":\"#BASO\",\"itemResult\":\"0.00\"},{\"itemCode\":\"HGB\",\"itemResult\":\"122\"},{\"itemCode\":\"RBC\",\"itemResult\":\"4.11\"},{\"itemCode\":\"PLT\",\"itemResult\":\"178\"},{\"itemCode\":\"WBC\",\"itemResult\":\"9.10\"},{\"itemCode\":\"#LYM\",\"itemResult\":\"1.36\"},{\"itemCode\":\"_LYM\",\"itemResult\":\"0.149\"},{\"itemCode\":\"#GRAN\",\"itemResult\":\"7.37\"},{\"itemCode\":\"_GRAN\",\"itemResult\":\"0.810\"},{\"itemCode\":\"HCT\",\"itemResult\":\"0.36\"},{\"itemCode\":\"MCV\",\"itemResult\":\"88.4\"},{\"itemCode\":\"MCH\",\"itemResult\":\"29.7\"},{\"itemCode\":\"RDW\",\"itemResult\":\"12.5\"},{\"itemCode\":\"MCHC\",\"itemResult\":\"336\"},{\"itemCode\":\"PCT\",\"itemResult\":\"0.147\"},{\"itemCode\":\"PDW\",\"itemResult\":\"16.2\"},{\"itemCode\":\"MPV\",\"itemResult\":\"8.3\"},{\"itemCode\":\"#MID\",\"itemResult\":\"0.32\"},{\"itemCode\":\"_MID\",\"itemResult\":\"0.035\"},{\"itemCode\":\"#EOS\",\"itemResult\":\"0.05\"},{\"itemCode\":\"_EOS\",\"itemResult\":\"0.006\"}]}}";
        //String resStr = mchis.getMchisHttpSoap11Endpoint().saveData("0553d8a40af24b8d86c190393c88b9d6",s);
        //BaseResponse res = JacksonUtil.json2Bean(resStr, new TypeReference<BaseResponse>() {});
        //System.out.println(res);
    }

}
