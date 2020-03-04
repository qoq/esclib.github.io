/*
Version 1.0
https://github.com/qoq/esclib.github.io
License: AGPL v3.0
*/

package com.qoq.esclib;

/* Response packet (Device -> Client):
new byte[] {
    0xF5,          /* packet start marker
    <req_len>,     /* length of the following request message
    <req_code>,    /* request code (below)
    [req_data]     /* request data, length varies (can be 0)
*/

public class RadarResponse {

    public static final byte RESP_START                         = (byte) 0xF5;

    public static final byte LOCK_RESPONSE_ERROR                = (byte) 0x01;
    public static final byte MUTE_BUTTON_PRESS                  = (byte) 0x80; //d=1 
    public static final byte GPS_EQUIPPED_RESPONSE              = (byte) 0x81; //d=1
    public static final byte ALERT_RESPONSE                     = (byte) 0x82; //d=0 (no alerts) or d=4*n (n chained alerts)
    public static final byte BRIGHTNESS_RESPONSE                = (byte) 0x83; //d=2 
    public static final byte LOCK_RESPONSE                      = (byte) 0x84; //d=1 
    public static final byte DISPLAY_LENGTH_RESPONSE            = (byte) 0x85; //d=1
    public static final byte UNLOCK_RESPONSE                    = (byte) 0x86; //d=1 
    public static final byte MARKED_LOCATION                    = (byte) 0x87; //d=13 
    public static final byte ERROR_RESPONSE                     = (byte) 0x88;
    public static final byte MODEL_NUMBER_RESPONSE              = (byte) 0x89;
    public static final byte SETTINGS_RESPONSE                  = (byte) 0x8A; //d>=2 F5038A 0105  or   F5278A 0105 0204 0400 0608 1B04 0703 0803 0900 0A00 0D04 0E00 0F00 1000 1300 1400 1500 1601 1700 1A01 (setting no + cur value)
    public static final byte SETTINGS_INFORMATION_RESPONSE      = (byte) 0x8B; //d>=2 F5038B nnF4 (unsupported setting) F5068B 16 00010203 (values for setting 16)
    public static final byte BAND_ENABLES_RESPONSE              = (byte) 0x8C; //d=5
    public static final byte BAND_ENABLES_DEFAULTS_RESPONSE     = (byte) 0x8D;
    public static final byte FLASH_USAGE_RESPONSE               = (byte) 0x8E;
    public static final byte BAND_ENABLES_SUPPORTED_RESPONSE    = (byte) 0x8F; //d=5
    public static final byte FLASH_ERASE_RESPONSE               = (byte) 0x90; //d=1 (0x00 = flash erased OK, otherwise error)
    public static final byte SHIFTER_STATUS_RESPONSE            = (byte) 0x91;
    public static final byte VERSION_RESPONSE                   = (byte) 0x92; //d>=7
    public static final byte SETTINGS_DEFAULTS_RESPONSE         = (byte) 0x93; //F50393 0100 (00 is default for setting 01)
    public static final byte FIRMWARE_UPDATE_STATUS             = (byte) 0x94; //d=2
    public static final byte MODEL_INFO_RESPONSE                = (byte) 0x95; //d>=1 f50a95 00 4d6178203336300 (00=escort, 01=Beltronics, 02=Cincinnati Microwave) + Name
    public static final byte AUTOLOCK_EVENT                     = (byte) 0x96; //d=0 F50196
    public static final byte MARKER_ENABLES_RESPONSE            = (byte) 0x97; //d=2
    public static final byte BUTTON_PRESS_REPORT                = (byte) 0x98;
    public static final byte STATUS_RESPONSE                    = (byte) 0x99; //d>=1 f50299 0B | 0BA9 | 03A9 | 0BA92F52000008 | 0BA9073C090200 | 0BA9073C292300
    public static final byte MARKER_ENABLES_DEFAULTS_RESPONSE   = (byte) 0x9A;
    public static final byte MARKER_ENABLES_SUPPORTED_RESPONSE  = (byte) 0x9B; //d=2
    public static final byte REPORT_BUTTON_PRESS                = (byte) 0x9C; //d=1
    public static final byte MODEL_NUMBER_REQUEST               = (byte) 0x9D; 
    public static final byte MUTE_RESPONSE                      = (byte) 0x9E;
    public static final byte POWER_DETECTOR_ON_OFF_RESPONSE     = (byte) 0x9F;
    public static final byte UPDATE_APPROVAL_REQUEST            = (byte) 0xA0; //d=3
    public static final byte BLUETOOTH_PROTOCOL_UNLOCK_REQUEST  = (byte) 0xA1; //d=10
    public static final byte BLUETOOTH_PROTOCOL_UNLOCK_RESPONSE = (byte) 0xA2; //d=10
    public static final byte BLUETOOTH_PROTOCOL_UNLOCK_STATUS   = (byte) 0xA3; //d=1 
    public static final byte BLUETOOTH_CONNECTION_DELAY_RESPONSE= (byte) 0xA4; //d=2 
    public static final byte BLUETOOTH_SERIAL_NUMBER_RESPONSE   = (byte) 0xA5; //d=8 
    public static final byte SPEED_INFORMATION_REQUEST          = (byte) 0xA6; //d=1 (data: 01=speed limit, 02=current speed)
    public static final byte OVERSPEED_WARNING_REQUEST          = (byte) 0xA7; //d=1
    public static final byte DISPLAY_CAPABILITIES_RESPONSE      = (byte) 0xA8; //d=1 (bits set: 0=beep supported, 1=display msg supported, 2=display location supported
    public static final byte ALERT_RESPONSE_FRONT_REAR          = (byte) 0xA9; //d=0 (no alets) or d=5*n (n chained alerts)
    public static final byte BAND_DIRECTION_RESPONSE            = (byte) 0xAA; 
    public static final byte RESP_SHIFTER_TBD                   = (byte) 0xB6; //d=1
    
    public static final byte UNSUPPORTED_REQUEST                = (byte) 0xF0; //d=1
    public static final byte UNSUPPORTED_SETTING                = (byte) 0xF4; // response to SETTINGS_INFORMATION_RESPONSE
    public static final byte UNKNOWN_SETTING                    = (byte) 0xF3; // response to SETTINGS_CHANGE request for invalid setting code
    public static final byte INVALID_RESPONSE_ERROR             = (byte) 0xFF;

/* service requests. to be explored
B0 
B1 
B2 
B3 
B4
*/

}
