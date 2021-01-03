package testAutomationSelfEducation.util;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class WorkWithCookie {
    WebDriver driver;


    public WorkWithCookie(WebDriver driver) {
        this.driver = driver;
    }

    public void addCookies(String name, String value) {
        driver.manage().addCookie(new Cookie(name, value));
    }

    public void deleteCookie(String name) {
        driver.manage().deleteCookieNamed(name);
    }

    public Set<Cookie> setCookies() {
        return driver.manage().getCookies();
    }

    public Cookie getNamed(String s) {
        return driver.manage().getCookieNamed(s);
    }
}
