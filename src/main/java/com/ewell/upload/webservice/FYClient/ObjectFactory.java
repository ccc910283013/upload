
package com.ewell.upload.webservice.FYClient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ewell.common.webservice.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetDataResponseReturn_QNAME = new QName("http://webservice.zhending.com", "return");
    private final static QName _SaveDataJson_QNAME = new QName("http://webservice.zhending.com", "json");
    private final static QName _SaveDataToken_QNAME = new QName("http://webservice.zhending.com", "token");
    private final static QName _LoginPassword_QNAME = new QName("http://webservice.zhending.com", "password");
    private final static QName _LoginLoginCode_QNAME = new QName("http://webservice.zhending.com", "loginCode");
    private final static QName _AddCachesKeyChild_QNAME = new QName("http://webservice.zhending.com", "keyChild");
    private final static QName _AddCachesRedisCacheManager_QNAME = new QName("http://webservice.zhending.com", "redisCacheManager");
    private final static QName _AddCacheKey_QNAME = new QName("http://webservice.zhending.com", "key");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ewell.common.webservice.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddCache }
     * 
     */
    public AddCache createAddCache() {
        return new AddCache();
    }

    /**
     * Create an instance of {@link RedisCacheManager }
     * 
     */
    public RedisCacheManager createRedisCacheManager() {
        return new RedisCacheManager();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link DeleteData }
     * 
     */
    public DeleteData createDeleteData() {
        return new DeleteData();
    }

    /**
     * Create an instance of {@link GetDataResponse }
     * 
     */
    public GetDataResponse createGetDataResponse() {
        return new GetDataResponse();
    }

    /**
     * Create an instance of {@link SaveData }
     * 
     */
    public SaveData createSaveData() {
        return new SaveData();
    }

    /**
     * Create an instance of {@link SaveDataResponse }
     * 
     */
    public SaveDataResponse createSaveDataResponse() {
        return new SaveDataResponse();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link DeleteDataResponse }
     * 
     */
    public DeleteDataResponse createDeleteDataResponse() {
        return new DeleteDataResponse();
    }

    /**
     * Create an instance of {@link GetData }
     * 
     */
    public GetData createGetData() {
        return new GetData();
    }

    /**
     * Create an instance of {@link AddCaches }
     * 
     */
    public AddCaches createAddCaches() {
        return new AddCaches();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.zhending.com", name = "return", scope = GetDataResponse.class)
    public JAXBElement<String> createGetDataResponseReturn(String value) {
        return new JAXBElement<String>(_GetDataResponseReturn_QNAME, String.class, GetDataResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.zhending.com", name = "json", scope = SaveData.class)
    public JAXBElement<String> createSaveDataJson(String value) {
        return new JAXBElement<String>(_SaveDataJson_QNAME, String.class, SaveData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.zhending.com", name = "token", scope = SaveData.class)
    public JAXBElement<String> createSaveDataToken(String value) {
        return new JAXBElement<String>(_SaveDataToken_QNAME, String.class, SaveData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.zhending.com", name = "password", scope = Login.class)
    public JAXBElement<String> createLoginPassword(String value) {
        return new JAXBElement<String>(_LoginPassword_QNAME, String.class, Login.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.zhending.com", name = "loginCode", scope = Login.class)
    public JAXBElement<String> createLoginLoginCode(String value) {
        return new JAXBElement<String>(_LoginLoginCode_QNAME, String.class, Login.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.zhending.com", name = "return", scope = LoginResponse.class)
    public JAXBElement<String> createLoginResponseReturn(String value) {
        return new JAXBElement<String>(_GetDataResponseReturn_QNAME, String.class, LoginResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.zhending.com", name = "json", scope = DeleteData.class)
    public JAXBElement<String> createDeleteDataJson(String value) {
        return new JAXBElement<String>(_SaveDataJson_QNAME, String.class, DeleteData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.zhending.com", name = "token", scope = DeleteData.class)
    public JAXBElement<String> createDeleteDataToken(String value) {
        return new JAXBElement<String>(_SaveDataToken_QNAME, String.class, DeleteData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.zhending.com", name = "keyChild", scope = AddCaches.class)
    public JAXBElement<String> createAddCachesKeyChild(String value) {
        return new JAXBElement<String>(_AddCachesKeyChild_QNAME, String.class, AddCaches.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RedisCacheManager }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.zhending.com", name = "redisCacheManager", scope = AddCaches.class)
    public JAXBElement<RedisCacheManager> createAddCachesRedisCacheManager(RedisCacheManager value) {
        return new JAXBElement<RedisCacheManager>(_AddCachesRedisCacheManager_QNAME, RedisCacheManager.class, AddCaches.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.zhending.com", name = "return", scope = DeleteDataResponse.class)
    public JAXBElement<String> createDeleteDataResponseReturn(String value) {
        return new JAXBElement<String>(_GetDataResponseReturn_QNAME, String.class, DeleteDataResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.zhending.com", name = "keyChild", scope = AddCache.class)
    public JAXBElement<String> createAddCacheKeyChild(String value) {
        return new JAXBElement<String>(_AddCachesKeyChild_QNAME, String.class, AddCache.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RedisCacheManager }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.zhending.com", name = "redisCacheManager", scope = AddCache.class)
    public JAXBElement<RedisCacheManager> createAddCacheRedisCacheManager(RedisCacheManager value) {
        return new JAXBElement<RedisCacheManager>(_AddCachesRedisCacheManager_QNAME, RedisCacheManager.class, AddCache.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.zhending.com", name = "key", scope = AddCache.class)
    public JAXBElement<String> createAddCacheKey(String value) {
        return new JAXBElement<String>(_AddCacheKey_QNAME, String.class, AddCache.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.zhending.com", name = "return", scope = SaveDataResponse.class)
    public JAXBElement<String> createSaveDataResponseReturn(String value) {
        return new JAXBElement<String>(_GetDataResponseReturn_QNAME, String.class, SaveDataResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.zhending.com", name = "json", scope = GetData.class)
    public JAXBElement<String> createGetDataJson(String value) {
        return new JAXBElement<String>(_SaveDataJson_QNAME, String.class, GetData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.zhending.com", name = "token", scope = GetData.class)
    public JAXBElement<String> createGetDataToken(String value) {
        return new JAXBElement<String>(_SaveDataToken_QNAME, String.class, GetData.class, value);
    }

}
