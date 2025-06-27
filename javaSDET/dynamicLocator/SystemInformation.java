package dynamicLocator;

import java.io.File;

public class SystemInformation {
    public static void main(String[] args) {
        String osName = System.getProperty("os.name");
        System.out.println(osName);

        String projectPath = System.getProperty("user.dir");
//        System.out.println(projectPath);

        String separator = System.getProperty("file.separator");
        System.out.println(separator);

        String danangImagePath = projectPath + File.separator + "uploadFiles" + File.separator + "DaNang.jpg";
        System.out.println(danangImagePath);

//        String danangImagePath = projectPath + "\\uploadFiles\\" + "DaNang.jpg";
//        System.out.println(danangImagePath);

        // E:\Automation Testing\03-Automation Framework\hybrid-nopcommerce-framework\.idea
        // E:\Automation Testing\03-Automation Framework\hybrid-nopcommerce-framework\ uploadFiles\DaNang.jpg

        // Mac or Linux will be fail if
        //E:/Automation Testing/03-Automation Framework/hybrid-nopcommerce-framework\ uploadFiles\DaNang.jpg
    }
}
