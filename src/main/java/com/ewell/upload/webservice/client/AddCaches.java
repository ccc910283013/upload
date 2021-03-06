
package com.ewell.upload.webservice.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="keys" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="keyChilds" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "keys",
    "keyChilds"
})
@XmlRootElement(name = "addCaches")
public class AddCaches {

    @XmlElementRef(name = "redisCacheManager", namespace = "http://webservice.zhending.com", type = JAXBElement.class, required = false)
    protected JAXBElement<RedisCacheManager> redisCacheManager;
    @XmlElement(nillable = true)
    protected List<String> keys;
    @XmlElementRef(name = "keyChilds", namespace = "http://webservice.zhending.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> keyChilds;

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
     * Gets the value of the keys property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the keys property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeys().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getKeys() {
        if (keys == null) {
            keys = new ArrayList<String>();
        }
        return this.keys;
    }

    /**
     * 获取keyChilds属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getKeyChilds() {
        return keyChilds;
    }

    /**
     * 设置keyChilds属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setKeyChilds(JAXBElement<String> value) {
        this.keyChilds = value;
    }

}
