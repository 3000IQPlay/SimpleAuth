package dev._3000IQPlay.simpleauth.protect;

import dev._3000IQPlay.simpleauth.SimpleAuth;
import dev._3000IQPlay.simpleauth.protect.jar.*;
import dev._3000IQPlay.simpleauth.protect.antivm.VMDetector;
import dev._3000IQPlay.simpleauth.SimpleAuthSpy;

public class RunRefuser {
	public static boolean result = true;
	public static boolean resultTwo = false;
	public static boolean vmResult = true;
	
    public static void getResult() {
		if (SimpleAuthSpy.numberCheck != 69 && SimpleAuth.lauchSent != true) {
			result = false;
		} else {
			result = true;
		}
    }
	
	public static void getResutlTwo() {
		if (JarSizeCheck.numberCheckTwo != JarHashCheck.numberCheckTwo) {
			resultTwo = true;
		} else {
			resultTwo = false;
		}
	}
	
	public static void vmCheck() {
		if (VMDetector.booleanCheck == false) {
			vmResult = false;
		} else {
			vmResult = true;
		}
	}
}