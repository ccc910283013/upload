
package com.ewell.upload.webservice.FYClient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


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
    "keys",
    "keyChild"
})
@XmlRootElement(name = "addCaches")
public class AddCaches {

    @XmlElementRef(name = "redisCacheManager", namespace = "http://webservice.zhending.com", type = JAXBElement.class, required = false)
    protected JAXBElement<RedisCacheManager> redisCacheManager;
    @XmlElement(nillable = true)
    protected List<String> keys;
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
