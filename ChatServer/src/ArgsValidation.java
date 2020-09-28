class ArgsValidation {
     /**
     * Validate port argument (must be a string containing only digits)
     */
    static boolean isValidPortArg(String arg) {
    	char str[] = arg.toCharArray();
    	for(char c : str)
    		if(c < 48 || c > 57) return false;
    	return true;
    }
    
    /**
     * Validate port number
     */
    static boolean isValidPort(int port) {
    	return (port>1023);
    }
    
    /**
     * Validate hostname argument (must be a string containing only digits and '.')
     */
    static boolean isValidHostnameArg(String hostname) {
        if(hostname.equals("localhost")) return true;
    	char str[] = hostname.toCharArray();
    	for(char c : str)
    		if(c < 48 || c > 57 || c != '.') return false;
    	return true;
    }
}
