package commons;

import java.io.File;
import java.lang.reflect.Field;

public class GlobalConstants {
    // JIRA Infor
    public static final String JIRA_SITE_URL = "https://autofc.atlassian.net/";
    public static final String JIRA_USERNAME = "automationfc.vn@gmail.com";
    public static final String JIRA_API_KEY = "ATATT3xFfGF0GFE8-JcRj9CZ5NiwpX1cozi5P6Tnl0DAW9G5VZ8y1cwq79ZcbPtZ5aXOhNRBko2f1GNN43FFsZK6e5U47RZh9padlYsU2x1lkBftw5r-pD1IG3LIPiMFecqGdim_o8vE7o_uHvH6oVlISJlsbiK7ktKsJjnIEzpPY2zJHugOVGo=CDB7AD89";
    public static final String JIRA_PROJECT_KEY = "AUTO";

    // System Infor
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String JAVA_VERSION = System.getProperty("java.version");
    public static final String SEPARATOR = System.getProperty("file.separator");

    // App Infor User
    public static final String DEV_USER_URL = "https://demo.nopcommerce.com/";
    public static final String STAGING_USER_URL = "https://stating.nopcommerce.com/";
    public static final String LIVE_USER_URL = "https://live.nopcommerce.com/";

    // App Infor Admin
    public static final String DEV_ADMIN_URL = "https://admin-demo.nopcommerce.com";
    public static final String STAGING_ADMIN_URL = "https://admin-staging.nopcommerce.com";
    public static final String LIVE_ADMIN_URL = "https://admin-live.nopcommerce.com";

    public static final String ADMIN_USERNAME = "admin@yourstore.com";
    public static final String ADMIN_PASSWORD = "admin";

    // Wait Infor
    public static final long SHORT_TIMEOUT = 5;
    public static final long LONG_TIMEOUT = 30;

    // Download/ Upload file
    public static final String UPLOAD_PATH = PROJECT_PATH + SEPARATOR + "uploadFiles" + SEPARATOR;
    public static final String DOWNLOAD_PATH = PROJECT_PATH + SEPARATOR + "downloadFiles" + SEPARATOR;

    // Retry Case Failed
    public static final int RETRY_NUMBER = 3;

    // Browser Logs/ Extension
    public static final String BROWSER_LOG = PROJECT_PATH + SEPARATOR + "browserLogs" + SEPARATOR;
    public static final String BROWSER_EXTENSION_PATH = PROJECT_PATH + SEPARATOR + "browserExtensions" + SEPARATOR;

    // HTML Report Folder
    public static final String REPORTNG_PATH = PROJECT_PATH + SEPARATOR + "htmlReportNG" + SEPARATOR;
    public static final String EXTENT_PATH = PROJECT_PATH + SEPARATOR + "htmlExtent" + SEPARATOR;
    public static final String ALLURE_PATH = PROJECT_PATH + SEPARATOR + "htmlAllure" + SEPARATOR;

    // Data Test/ Environment
    public static final String DATA_TEST_PATH = PROJECT_PATH + SEPARATOR + "dataTest" + SEPARATOR;
    public static final String ENVIRONMENT_CONFIG_PATH = PROJECT_PATH + SEPARATOR + "environmentConfig" + SEPARATOR;
}