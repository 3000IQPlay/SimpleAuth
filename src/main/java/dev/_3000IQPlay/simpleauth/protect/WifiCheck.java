package dev._3000IQPlay.simpleauth.protect;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class WifiCheck {
    public static boolean getConnectionCheck() {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            return address.isReachable(1000);
        } catch (UnknownHostException e) {
            return false;
        } catch (Exception e) {
            return false;
		}
	}
}