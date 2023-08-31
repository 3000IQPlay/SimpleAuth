package org.mapleir.dot4j.systems.auth;

import org.mapleir.dot4j.systems.auth.MacUtil;
import java.io.File;
import java.io.IOException;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class AntiVM {
	public static void checkForVM() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("nux") || osName.contains("nix") || osName.contains("mac")) {
            String cpuInfo = executeCommand("cat /proc/cpuinfo");
            if (cpuInfo.contains("hypervisor")) {
                // System.out.println("Running on a Virtual Machine");
            } else {
                // System.out.println("Not running on a Virtual Machine");
            }
        } else if (osName.contains("win")) {
            String systemInfo = executeCommand("systeminfo");
            if (systemInfo.contains("Virtual Machine")) {
                // System.out.println("Running on a Virtual Machine");
            } else {
                // System.out.println("Not running on a Virtual Machine");
            }
        } else {
            // System.out.println("Cannot determine the operating system");
        }
    }

    private static String executeCommand(String command) {
        StringBuilder output = new StringBuilder();
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
	
    public static boolean isRunningOnVM() {
        boolean isVM = false;

        String vendor = System.getProperty("java.vendor");
        String name = System.getProperty("java.vm.name");
        String version = System.getProperty("java.vm.version");
        String classPath = System.getProperty("java.class.path");

        if (vendor != null && vendor.toLowerCase().contains("vmware")) {
            isVM = true;
        } else if (name != null && name.toLowerCase().contains("virtualbox")) {
            isVM = true;
        } else if (version != null && version.toLowerCase().contains("virtual")) {
            isVM = true;
        } else if (classPath != null && classPath.toLowerCase().contains("android")) {
            isVM = true;
        }

        File[] filesToCheck = {new File("C:\\WINDOWS\\system32\\drivers\\vmmouse.sys"),
                new File("/usr/share/virtualbox")};
        for (File file : filesToCheck) {
            if (file.exists()) {
                isVM = true;
                break;
            }
        }

        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            try {
                Process process = Runtime.getRuntime().exec("reg query HKLM\\HARDWARE\\ACPI\\DSDT\\VBOX__");
                process.waitFor();
                if (process.exitValue() == 0) {
                    isVM = true;
                }
            } catch (IOException | InterruptedException e) {
            }
        }

        int availableProcessors = Runtime.getRuntime().availableProcessors();
        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long availableMemory = maxMemory - totalMemory + freeMemory;
        if (availableProcessors <= 2 || availableMemory <= 1024 * 1024 * 512) {
            isVM = true;
        }

        return isVM;
    }
	
	public static boolean isMacVM() {
        try {
            Enumeration<NetworkInterface> net = null;
            net = NetworkInterface.getNetworkInterfaces();
            if (net.hasMoreElements()) {
                NetworkInterface element = net.nextElement();
                return MacUtil.isVMMac(element.getHardwareAddress());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
