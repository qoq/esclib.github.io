/*
Version 1.0
https://github.com/qoq/esclib.github.io
License: AGPL v3.0
*/

package com.qoq.esclib;

/* Request packet ( Client -> Device ):
    new byte[] {
        0xF5,          /* packet start marker
        <req_len>,     /* length of the following request message
        <req_code>,    /* request code (below)
        [req_data]     /* request data, length varies (can be 0)
 */


public class RadarRequest {
    public static final byte REQ_START                          = (byte)0xF5; // d == data length:
    public static final byte LOCK_REQUEST                       = (byte)0x80; //d=0 current alert lockout
    public static final byte UNLOCK_REQUEST                     = (byte)0x81; //d=0 current alert unlock
    public static final byte SETTINGS_INFORMATION               = (byte)0x82; //d=1
    public static final byte SETTING_CHANGE                     = (byte)0x83; //d=2 (num,new_val)
    public static final byte SETTINGS_REQUEST                   = (byte)0x84; //d=1 (0x00 ?)
    public static final byte BAND_ENABLES_SET                   = (byte)0x85; //d=5
    public static final byte BAND_ENABLES_REQUEST               = (byte)0x86; //d=0
    public static final byte FLASH_UTILIZATION                  = (byte)0x87;
    public static final byte FLASH_ERASE                        = (byte)0x88; //d=0
    public static final byte VERSION_REQUEST                    = (byte)0x89; //d=0
    public static final byte SHIFT_STATUS_REQUEST               = (byte)0x8A; 
    public static final byte SHIFTER_CONTROL                    = (byte)0x8B;  
    public static final byte BAND_ENABLES_SUPPORTED             = (byte)0x8C; //d=0
    public static final byte BAND_ENABLES_DEFAULT               = (byte)0x8D; //d=0
    public static final byte BRIGHTNESS                         = (byte)0x8E; // (guessed - d = 0 or d = 1)
    public static final byte SETTINGS_DEFAULTS                  = (byte)0x8F; //d=1 byte (setting number)
    public static final byte MARKED_LOCATION                    = (byte)0x90; //d=13
    public static final byte MODEL_INFO_REQUEST                 = (byte)0x91; //d=0
    public static final byte MARKER_ENABLES_REQUEST             = (byte)0x92; //d=0
    public static final byte MARKER_ENABLES_SET                 = (byte)0x93; //d=2
    public static final byte STATUS_REQUEST                     = (byte)0x94; //d=0
    public static final byte MARKER_ENABLES_SUPPORTED           = (byte)0x95; //d=0
    public static final byte MARKER_ENABLES_DEFAULTS            = (byte)0x96; //d=0
    public static final byte GPS_EQUIPPED_REQUEST               = (byte)0x97; //d=0
    public static final byte LOCKOUT_LIST                       = (byte)0x98; //d=5 (127, 127, 127, 127, 11) == when auto lockout enabled
                            //only valid if GPS-equipped DOES NOTHING ON 360  // or (0, 0, 0, 0, 0) == clear all lockout DB 
    public static final byte DISPLAY_LENGTH                     = (byte)0x99; //d=0
    public static final byte DISPLAY_MESSAGE                    = (byte)0x9A; //d=XX (see below) or d=2(0x00,0x00 = clear screen)
                                                                              // F5 9A XX [message bytes]
    public static final byte PLAY_TONE                          = (byte)0x9B; //d=0,1,2
    // 9C -- TBD BOOLEAN, data = 1(0,1)
    public static final byte MODEL_NUMBER_RESPONSE              = (byte)0x9D; //d=1(0x0=acknowledge) or d=4 (0x01,'0',modelnum1,modelnum2)
    public static final byte MUTE                               = (byte)0x9E; //d=1 (01/00 mute/unmute)
    public static final byte POWER_DETECTOR_ON_OFF              = (byte)0x9F;  
    public static final byte MODEL_NUMBER_REQUEST               = (byte)0xA0; //d=0
    public static final byte UPDATE_APPROVAL_RESPONSE           = (byte)0xA1; //d=1 [1=accept, 0=acknowledge, 2=deny]
    public static final byte LIVE_ALERT                         = (byte)0xA2; 
    public static final byte BLUETOOTH_PROTOCOL_UNLOCK_REQUEST  = (byte)0xA3; //d=10
    public static final byte BLUETOOTH_PROTOCOL_UNLOCK_RESPONSE = (byte)0xA4; //d=10
    public static final byte BLUETOOTH_PROTOCOL_UNLOCK_STATUS   = (byte)0xA5; //d=1
    public static final byte BLUETOOTH_CONNECTION_DELAY_REQUEST = (byte)0xA6; //d=0
    public static final byte BLUETOOTH_CONNECTION_DELAY_SET     = (byte)0xA7; //d=2 (delay is 2-byte word, LSB first)
    public static final byte BLUETOOTH_SERIAL_NUMBER_REQUEST    = (byte)0xA8; //d=0
    public static final byte SPEED_LIMIT_UPDATE                 = (byte)0xA9; //d=2
    public static final byte ACTUAL_SPEED_UPDATE                = (byte)0xAA; //d=2
    public static final byte OVERSPEED_LIMIT_DATA               = (byte)0xAB; //d=1 (64 + [0,1,5,7,10,15]) 0 == off, 1 = spd limit, 5-15 = 5-15 over
    public static final byte DISPLAY_CAPABILITIES               = (byte)0xAC; //d=0
    public static final byte DISPLAY_LOCATION                   = (byte)0xAD; //d=5 
    public static final byte DISPLAY_CLEAR_LOCATION             = (byte)0xAE; //d=0
    public static final byte COMM_INIT                          = (byte)0xC0; //d=1 C002XX xx = 16=(bt full on) 05=(bt off + icon) 0A=(bt off) 0D=(bt off+ force disconnect)

/* reserved service routines, to be explored
AF 
B0 
B1 
B2 
B3 
B4 
B5 
B6 
C0 comm init { 0xF5, 2, 0xC0, 0x16 }
 
*/

}

