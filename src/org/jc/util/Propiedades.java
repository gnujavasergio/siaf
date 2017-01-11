package org.jc.util;

import java.net.InetAddress;
import java.net.NetworkInterface;

public class Propiedades {

    public static String conseguirMAC() {
        try {
            NetworkInterface a = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
            byte[] mac = a.getHardwareAddress();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", new Object[]{Byte.valueOf(mac[i]), i < mac.length - 1 ? "-" : ""}));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}