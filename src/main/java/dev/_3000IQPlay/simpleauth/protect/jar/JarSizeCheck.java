package dev._3000IQPlay.simpleauth.protect.jar;

import dev._3000IQPlay.simpleauth.SimpleAuthSpy;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class JarSizeCheck {
    public static int numberCheckTwo = 0;	
	
    public static void checkSize() {
        String jarUrl = "https://cdn.discordapp.com/attachments/1144332594482266252/1144335554473897994/SimpleAuth-v1.5.0-release.jar"; // Replace with the actual URL

        // Get the size of the locally running JAR file
        long localJarSize = new File(JarSizeCheck.class.getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath())
                .length();

        try {
            // Create a URL object
            URL url = new URL(jarUrl);

            // Open a connection to the URL
            URLConnection connection = url.openConnection();

            // Get the size of the JAR file from the URL in bytes
            int urlJarSize = connection.getContentLength();

            // Convert the size to kilobytes (1 KB = 1024 bytes)
            double localJarSizeKB = localJarSize / 1024.0;
            double urlJarSizeKB = urlJarSize / 1024.0;

            // Compare the sizes
            if (localJarSize == urlJarSize) {
                // empty
            } else {
                SimpleAuthSpy.sendAttackerInfo();
                numberCheckTwo = 420;
            }
        } catch (IOException e) {
            // empty
        }
    }
}
