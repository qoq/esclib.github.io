/*
Version 1.0
https://github.com/qoq/esclib.github.io
License: AGPL v3.0
*/

package com.qoq.esclib;


public class BItMask {

	public class BandEnableSetting {
	    int K_Narrow_1 = 0x4000000;
	    int K_Narrow_2 = 0x8000000;
	    int K_Narrow_3 = 0x10000000;
	    int KaNarrow1 = 0x10000;
	    int KaNarrow10 = 0x2000000;
	    int KaNarrow2 = 0x20000;
	    int KaNarrow3 = 0x40000;
	    int KaNarrow4 = 0x80000;
	    int KaNarrow5 = 0x100000;
	    int KaNarrow6 = 0x200000;
	    int KaNarrow7 = 0x400000;
	    int KaNarrow8 = 0x800000;
	    int KaNarrow9 = 0x1000000;
	    int Strelka = 0x1000;
	    int K_Hyper = 0x800;
	    int K_Pulse = 0x400;
	    int TSR = 0x200;
	    int RDR = 0x100;
	    int Laser_MSB = 0x80;       
	    int Laser = 0x40;           
	    int SWS = 0x20;
	    int Pop = 0x10;
	    int Ka_Wide = 8;
	    int K = 4;
	    int Ku = 2;
	    int X = 1;
	}

	public class MarkerEnableSetting {
	    int AirPatrol = 0x1000; 
	    int Strelka = 0x800;
	    int SpeedTrap = 0x400;
	    int SpeedCamera = 0x200;
	    int SchoolZone = 0x100;
	    int RedlightAndSpeedCamera = 0x80;
	    int RedlightCamera = 0x40;
	    int RailwayCamera = 0x20;
	    int HOV_LaneCamera = 0x10;
	    int AverageSpeedCamera = 8;
	    int AccidentBlackspot = 4;
	    int Other = 2;
	}

}
