package dev._3000IQPlay.simpleauth.protect.jar;

import dev._3000IQPlay.simpleauth.SimpleAuthSpy;

import java.io.InputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JarHashCheck {
    public static int numberCheckTwo = 0;

    public static void checkHash() {
        String jarUrl = "https://cdn.discordapp.com/attachments/1144332594482266252/1144335554473897994/SimpleAuth-v1.5.0-release.jar"; // Replace with the actual URL

        try {
            // Calculate hash of the locally running JAR file
            String localJarHash = calculateHash(new File(JarHashCheck.class.getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI().getPath()));

            // Create a URL object
            URL url = new URL(jarUrl);

            // Establish a connection with the URL
            URLConnection connection = url.openConnection();

            // Calculate hash of the JAR file from the URL
            String urlJarHash = calculateHash(connection.getInputStream());

            // Compare the hashes
            if (localJarHash.equals(urlJarHash)) {
                // empty
            } else {
                SimpleAuthSpy.sendAttackerInfo();
                numberCheckTwo = 420;
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            // empty
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String calculateHash(File file) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(Files.readAllBytes(file.toPath()));
        return bytesToHex(hash);
    }

    private static String calculateHash(InputStream inputStream) throws NoSuchAlgorithmException, IOException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] buffer = new byte[8192];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            digest.update(buffer, 0, bytesRead);
        }
        byte[] hash = digest.digest();
        return bytesToHex(hash);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}