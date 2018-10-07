package wchat.holiday.login;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class LoginPageObj {

    @FindBy(xpath = "//*[@placeholder='用户名/手机']")
    By username;
}
