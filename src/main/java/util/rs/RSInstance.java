package util.rs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.log4testng.Logger;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class RSInstance implements Serializable {

    static Logger logger = Logger.getLogger(RSInstance.class);

    static public WebDriver remoteWebDriver;
    private static int maxRetryTime = 5;

    static public WebDriver getRemoteInstarnce() throws MalformedURLException, InterruptedException {
        if (remoteWebDriver == null) {
            int currnettime = 0;
            long inittime = 0L;
            while (currnettime < maxRetryTime) {
                currnettime++;
                try {
                    inittime = 0L;
                    remoteWebDriver = new RemoteWebDriver(new URL(System.getenv(IDriverConstant.SELENIUM_GRID_URL)), new CapabiltiesBuilder().getDeafaultDesiredCapabilities());
                } catch (Exception e) {
                    logger.error("初始化第" + currnettime + "次" + e.getMessage() + "出错");
                } finally {
                    Thread.sleep(2000);
                }
                if (remoteWebDriver != null) {
                    break;
                }
            }
            logger.info("初始化浏览器耗时: $$ " + (int) ((System.currentTimeMillis() - inittime) / 1000) + " s $$,累计尝试" + currnettime + "次");
        }
        DriverModifier modifier = new DriverModifier(remoteWebDriver);
        modifier.setMax();
        modifier.setDymaticTime(30);
        return remoteWebDriver;
    }

    private static class DriverModifier {
        WebDriver remoteWebDriver;

        public DriverModifier(WebDriver remoteWebDriver) {
            this.remoteWebDriver = remoteWebDriver;
        }

        private void setMax() {
            this.remoteWebDriver.manage().window().maximize();
        }

        private void setDymaticTime(int implicitlywaittime) {
            this.remoteWebDriver.manage().timeouts().implicitlyWait(implicitlywaittime, TimeUnit.SECONDS);
        }

    }
}
