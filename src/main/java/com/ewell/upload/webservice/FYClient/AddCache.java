
package com.ewell.upload.webservice.FYClient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="redisCacheManager" type="{http://utils.zhending.com/xsd}RedisCacheManager" minOccurs="0"/>
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="keyChild" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "redisCacheManager",
    "key",
    "keyChild"
})
@XmlRootElement(name = "addCache")
public class AddCache {

    @XmlElementRef(name = "redisCacheManager", namespace = "http://webservice.zhending.com", type = JAXBElement.class, required = false)
    protected JAXBElement<RedisCacheManager> redisCacheManager;
    @XmlElementRef(name = "key", namespace = "http://webservice.zhending.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> key;
    @XmlElementRef(name = "keyChild", namespace = "http://webservice.zhending.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> keyChild;

    /**
     * 获取redisCacheManager属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link RedisCacheManager }{@code >}
     *     
     */
    public JAXBElement<RedisCacheManager> getRedisCacheManager() {
        return redisCacheManager;
    }

    /**
     * 设置redisCacheManager属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link RedisCacheManager }{@code >}
     *     
     */
    public void setRedisCacheManager(JAXBElement<RedisCacheManager> value) {
        this.redisCacheManager = value;
    }

    /**
     * 获取key属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getKey() {
        return key;
    }

    /**
     * 设置key属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setKey(JAXBElement<String> value) {
        this.key = value;
    }

    /**
     * 获取keyChild属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getKeyChild() {
        return keyChild;
    }

    /**
     * 设置keyChild属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setKeyChild(JAXBElement<String> value) {
        this.keyChild = value;
    }

}
