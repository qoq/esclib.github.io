/*
Version 1.0
https://github.com/qoq/esclib.github.io
License: AGPL v3.0
*/

package com.qoq.esclib;

import java.util.UUID;

public class Crypto {

    public Crypto(boolean isBLE) {
	this.isBLE = isBLE;
    }

    // Crypto keys for BT
    private static final int[] BT_SMARTCORD_KEYS = {0x713A2B5A, 0x49752D5C, 0x7B496D7B, 0x3D5F667C};
    private static final int[] BT_SMARTPHONE_KEYS = {0x622F7B45, 0x312F3C69, 0x36535D67, 0x50677C5F};

    // Crypto keys for BLE
    private static final int[] BLE_SMARTCORD_KEYS = {0xB67423AB, 0x7B7F599E, 0x831E63EB, 0x535C1285};
    private static final int[] BLE_SMARTPHONE_KEYS = {0xEFC62E92, 0xFB676A4B, 0xE29946BD, 0xF9AF55CB};
 
    private boolean isBLE;

    private int[] verifyNonce;

    private int[] getSmartcordKeys(){
        return this.isBLE ?  BLE_SMARTCORD_KEYS : BT_SMARTCORD_KEYS;
    }

    private int[] getSmartphoneKeys(){
        return this.isBLE ?  BLE_SMARTPHONE_KEYS : BT_SMARTPHONE_KEYS;
    }

    private int[] xtea_encrypt(int num_rounds, int v[], int key[]) {
	int v0 = v[0];
	int v1 = v[1];

	int sum = 0; int delta = -245324840;

	for (int i = 0; i < num_rounds; i++) {
            v0 += ((v1 << 4 ^ v1 >>> 5) + v1 ^ key[(sum & 0x3)] + sum);
            sum += delta;
            v1 += ((v0 << 4 ^ v0 >>> 5) + v0 ^ key[(sum >>> 11 & 0x3)] + sum);
        }

	return new int[] {v0,v1};
    }

    private int[] esc_pack_10bytes_to_2ints(byte[] esc_req) {
        int b0 = esc_req[3];
        int b1 = esc_req[4];
        int b2 = esc_req[5];
        int b3 = esc_req[6];
        int b4 = esc_req[7];
        int b5 = esc_req[8];
        int b6 = esc_req[9];
        int b7 = esc_req[10];
        int b8 = esc_req[11];
        int b9 = esc_req[12];

        int v0 = b0 & 0x7F | (b1 & 0x7F) << 7 | (b2 & 0x7F) << 14 | (b3 & 0x7F) << 21 | (b4 & 0xF) << 28;         
        int v1 = (b5 & 0x70) >> 4 | (b5 & 0x7F) << 3 | (b6 & 0x7F) << 10 | (b7 & 0x7F) << 17 | (b8 & 0x7F) << 24 | (b9 & 0x1) << 31;

    	return new int[] {v0,v1};
    }

    private byte[] esc_unpack_2ints_to_10_bytes(int vv[]) {
    	int v0 = vv[0];
    	int v1 = vv[1];

        byte b0 = (byte) (v0 & 0x7F);
        byte b1 = (byte) (v0 >>> 7 & 0x7F);
        byte b2 = (byte) (v0 >>> 14 & 0x7F);
        byte b3 = (byte) (v0 >>> 21 & 0x7F);
        byte b4 = (byte) (v0 >>> 28 & 0x7F | v1 << 4 & 0x70);        
        byte b5 = (byte) (v1 >>> 3 & 0x7F);
        byte b6 = (byte) (v1 >>> 10 & 0x7F);
        byte b7 = (byte) (v1 >>> 17 & 0x7F);
        byte b8 = (byte) (v1 >>> 24 & 0x7F);
        byte b9 = (byte) (v1 >>> 31 & 0x1);

        return new byte[] {b0,b1,b2,b3,b4,b5,b6,b7,b8,b9};
    }

    // Calculates response packet for detector's unlock/auth challenge request
    public byte[] getUnlockResponse(byte[] esc_req) {

        int vv[] = xtea_encrypt(35, esc_pack_10bytes_to_2ints(esc_req), getSmartcordKeys());

        byte[] rr = new byte[3+10];
        rr[0] = RadarRequest.BLUETOOTH_PROTOCOL_UNLOCK_RESPONSE;
        rr[1] = RadarRequest.REQ_START;
        rr[2] = 10;
        System.arraycopy(esc_unpack_2ints_to_10_bytes(vv), 0, rr, 3, 10);
        return rr;
    }

    // Builds packet that sends auth request(challenge) to detector
    public byte[] getUnlockRequest() {
        UUID localUUID = UUID.randomUUID();
        int i8 = 0;
        int vv[] = new int[] { (int)(0x7FFFFFFF & localUUID.getLeastSignificantBits()),(int)(0x7FFFFFFF & localUUID.getMostSignificantBits())};

        vv = xtea_encrypt(35, vv, getSmartcordKeys());

        verifyNonce = vv;

        byte[] rr = new byte[3+10];
        rr[0] = RadarRequest.BLUETOOTH_PROTOCOL_UNLOCK_REQUEST;
        rr[1] = RadarRequest.REQ_START;
        rr[2] = 10;
        System.arraycopy(esc_unpack_2ints_to_10_bytes(vv), 0, rr, 3, 10);
        return rr;
    }

    // Validates if detector properly responded to the auth challenge
    public boolean verifyUnlockResponse(byte[] esc_req) {

 	int vv[] = xtea_encrypt(35, esc_pack_10bytes_to_2ints(esc_req), getSmartcordKeys());        
        int vvx[] = xtea_encrypt(35, verifyNonce, getSmartcordKeys());
   
        return vv[0] == vvx[0] && vv[1] == vvx[1];
    }

}
