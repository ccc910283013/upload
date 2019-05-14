package com.ewell.upload.util;



import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlUtil {

    /**
     *
     * @param t 类对象
     * @param xmlStr 需要转换的字符串
     * @param <T> 类类型
     * @return
     * @throws Exception
     */
    public static <T> T xmlToJavaBean(Class<T> t,String xmlStr)throws Exception {
        JAXBContext context = JAXBContext.newInstance(t);
        Unmarshaller um = context.createUnmarshaller();
        return (T)um.unmarshal(new StringReader(xmlStr));
    }

    /**
     * @param obj 需要转换的对象
     * @return 转换后的字符串
     * @throws Exception
     */
    public static String javaBeanToXmlString(Object obj)throws Exception {
        StringWriter sw = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
        m.setProperty(Marshaller.JAXB_ENCODING,"UTF-8");
        m.marshal(obj,sw);
        return sw.toString();
    }
}
