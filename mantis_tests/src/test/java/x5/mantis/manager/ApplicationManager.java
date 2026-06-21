package x5.mantis.manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {

    private WebDriver driver;

    private String string;

    private Properties properties;

    private SessionHelper sessionHelper;

    private HttpSessionHelper HttpSessionHelper;

    private JamesCliHelper jameCliHelper;
    
    private MailHelper mailHelper;
    
    private RegistrationHelper registrationHelper;

    private JamesApiHelper jameApiHelper;

    private RestApiHelper restApiHelper;


    public void init(String browser, Properties properties) {
        this.string = browser;
        this.properties = properties;
    }

    public WebDriver driver(){
    if (driver == null){
        if ("firefox".equals(string)) {
            driver = new FirefoxDriver();
        } else if ("chrome".equals(string)) {
            driver = new ChromeDriver();
        } else {
            throw new IllegalArgumentException(String.format("Unknow browser %s", string));
        }
        Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
        driver.get(properties.getProperty("web.baseUrl"));
        driver.manage().window().setSize(new Dimension(1166, 958));
    }
    return driver;
    }

    public SessionHelper session(){
        if(sessionHelper == null){
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }
    public RegistrationHelper registration(){
        if(registrationHelper == null){
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public HttpSessionHelper http() {
        if (HttpSessionHelper == null) {
            HttpSessionHelper = new HttpSessionHelper(this);
        }
        return HttpSessionHelper;
    }

    public JamesCliHelper jamesCli() {
        if (jameCliHelper == null) {
            jameCliHelper = new JamesCliHelper(this);
        }
        return jameCliHelper;
    }

    public JamesApiHelper jamesApi() {
        if (jameApiHelper == null) {
            jameApiHelper = new JamesApiHelper(this);
        }
        return jameApiHelper;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }


    public RestApiHelper rest() {
        if (restApiHelper == null) {
            restApiHelper = new RestApiHelper(this);
        }
        return restApiHelper;
    }

    public String property (String name){
        return properties.getProperty(name);
    }
}
