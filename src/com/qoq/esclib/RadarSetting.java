/*
Version 1.0
https://github.com/qoq/esclib.github.io
License: AGPL v3.0
*/

package com.qoq.esclib;

public class RadarSetting {

    public static final int PREF_TYPE_LIST = 0;
    public static final int PREF_TYPE_BOOLEAN = 1;
    public static final int PREF_TYPE_RANGE = 2;

    public static final int[] PREF_IDS = {
            0x00, /* not used, identifies "all prefs" */
            0x01, //prefs_sensitivity,
            0x02, //prefs_brightness,
            0x03, //prefs_darkmode,
            0x04, //prefs_pilot,
            0x05, //prefs_poweronseq,
            0x06, //prefs_meter,
            0x07, //prefs_mute,
            0x08, //prefs_tones,
            0x09, //prefs_zr3mode,
            0x0A, //prefs_voice,
            0x0B, //prefs_speedalert,
            0x0C, //prefs_autovolume,
            0x0D, //prefs_autopower,
            0x0E, //prefs_units,
            0x0F, //prefs_gps,
            0x10, //prefs_autolearn,
            0x11, //prefs_alertlamp,
            0x12, //prefs_displayorientation,
            0x13, //prefs_cruisealert,
            0x14, //prefs_overspeedalert,
            0x15, //prefs_language,
            0x16, //prefs_color,
            0x17, //prefs_speeddisplay,
            0x18, //prefs_reserved1,
            0x19, //prefs_reserved2,
            0x1A, //prefs_usermode,
            0x1B, //prefs_arrowmode
    };

    // Preference types
    private static final byte[] PREFS_BOOLEAN = {0x0a,0x0b,0x0c,0x0f,0x10,0x11,0x17,0x18,0x19};
    private static final  byte[] PREFS_RANGE = {0x13, 0x14};
    // Everything else is of LIST type

    
    public byte id;
    public byte value;
    public byte default_value;
    public byte[] value_options; // for PREF_RANGE type -- [start_val, max_val, increment]

    public RadarSetting(byte pid, byte val) {
        id = pid;
        value = val;
        value_options = null;
        default_value= RadarResponse.UNSUPPORTED_SETTING;
    }

    public int getMin(){
        if (getType() != PREF_TYPE_RANGE || value_options == null || value_options.length != 3) return 0;
        return (int) value_options[0] & 0xFF;
    }
    public int getMax(){
        if (getType() != PREF_TYPE_RANGE || value_options == null || value_options.length != 3) return 0;
        return (int) value_options[1] & 0xFF;
    }
    public int getStep(){
        if (getType() != PREF_TYPE_RANGE || value_options == null || value_options.length != 3) return 0;
        return (int) value_options[2] & 0xFF;
    }
    public int getValue(){
        return (int) value & 0xFF;
    }

    public int getDefaultValue(){
        return (int) default_value & 0xFF;
    }

    public int getType() {
        for (int i=0; i<PREFS_BOOLEAN.length;i++)
            if (id == PREFS_BOOLEAN[i]) return PREF_TYPE_BOOLEAN;
        for (int i=0; i<PREFS_RANGE.length;i++)
            if (id == PREFS_RANGE[i]) return PREF_TYPE_RANGE;
        return PREF_TYPE_LIST;
    }

    public boolean isValid() {
        switch (getType()) {
            case PREF_TYPE_RANGE:
                return getMin() < getMax() && getStep() > 0 && getStep() < getMax();
            case PREF_TYPE_BOOLEAN:
                return true;
            case PREF_TYPE_LIST:
                return true;
        }
        return false;
    }
}
