
package com.xcelenergy.registration.wsdl.serviceplan;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.1.7
 * 2016-08-16T23:46:45.211-05:00
 * Generated source version: 3.1.7
 */

@WebFault(name = "Exception", targetNamespace = "http://soap.ws.registration.utility.com/")
public class Exception_Exception extends java.lang.Exception {
    
    private com.xcelenergy.registration.wsdl.serviceplan.Exception exception;

    public Exception_Exception() {
        super();
    }
    
    public Exception_Exception(String message) {
        super(message);
    }
    
    public Exception_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Exception_Exception(String message, com.xcelenergy.registration.wsdl.serviceplan.Exception exception) {
        super(message);
        this.exception = exception;
    }

    public Exception_Exception(String message, com.xcelenergy.registration.wsdl.serviceplan.Exception exception, Throwable cause) {
        super(message, cause);
        this.exception = exception;
    }

    public com.xcelenergy.registration.wsdl.serviceplan.Exception getFaultInfo() {
        return this.exception;
    }
}