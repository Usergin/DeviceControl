package com.shiz;

/**
 * Created by oldman on 05.04.17.
 */
public class Constants {
    public final static int DIAGNOSTIC_PACKAGE = 1;
    public final static int INCOMING_CALL = 2;
    public final static int OUTGOING_CALL = 3;
    public final static int MISSED_CALL = 4;
    public final static int INCOMING_SMS = 5;
    public final static int OUTGOING_SMS = 6;
    public final static int SERVICE_EVENT = 7;
    public final static int PHONE_BOOK = 8;
    public final static int INSTALL_APP = 9;

    /*
    for periodical request
     */
    public final static int CONTINUE_TO_WORK_RESPONSE = 1;
    public final static int NEW_SETTINGS_RESPONSE = 2;
    public final static int REMOVED_RESPONSE = 3;

    /*
    if error
     */
    public final static int DEVICE_NOT_FOUND = 0;
    public final static int BAD_IMEI = 1;
    public final static int BAD_TYPE = 2;
    public final static int BAD_MODE_FOR_DELETE = 3;
    public final static int OTHER_ERROR = 4;


    /*
    for device state
     */
    enum EVENT {Device, Battery_state, Network}
}
