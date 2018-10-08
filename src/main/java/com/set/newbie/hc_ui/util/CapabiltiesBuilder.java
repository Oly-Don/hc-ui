package com.set.newbie.hc_ui.util;

import org.openqa.selenium.remote.DesiredCapabilities;

public class CapabiltiesBuilder {

    private final String user_Agent = "User-Agent";
    private final String user_Agent_Value = "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36";

    static private DesiredCapabilities capability;

    public DesiredCapabilities getDeafaultDesiredCapabilities() {
        return getSpecialDesiredCapabilities();
    }

    private DesiredCapabilities getSpecialDesiredCapabilities() {
        if(capability!=null){
            return capability;
        }
        String getenv = System.getenv("driver.style");
        String getproperty = System.getProperty("driver.style");
        if (IDriverConstant.IE_DIVER_STYLE.equals(getenv) || IDriverConstant.IE_DIVER_STYLE.equals(getproperty)) {
            capability = DesiredCapabilities.internetExplorer();
        } else if (IDriverConstant.CHROME_DIVER_STYLE.equals(getenv) || IDriverConstant.CHROME_DIVER_STYLE.equals(getproperty)) {
            capability = DesiredCapabilities.chrome();
        } else { // 0
            capability = DesiredCapabilities.firefox();
        }
        capability.setJavascriptEnabled(true);
//        capability.setCapability(user_Agent, user_Agent_Value);
        return capability;
    }
}
