package com.s2.common;

public class ArgsValidation {
    /**
     * Validate port argument (must be a string containing only digits)
     */
    public static boolean isValidPortArg(String arg) {
        char[] str = arg.toCharArray();
        for(char c : str)
            if(!Character.isDigit(c)) return false;
        return true;
    }

    /**
     * Validate port number
     */
    public static boolean isValidPort(int port) {
        return (port > 1023);
    }

    /**
     * Validate hostname argument (must be a string containing only digits and '.')
     */
    public static boolean isValidHostnameArg(String hostname) {
        if(hostname.equals("localhost")) return true;
        char[] str = hostname.toCharArray();
        for(char c : str)
            if(!Character.isDigit(c) && c != '.') return false;
        return true;
    }
}
