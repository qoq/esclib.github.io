<H1>esclib.github.io</H1>

This repository describes a reverse-engineered Escort radar detector protocol and provides a utility library to communicate to Escort/Beltronics radar detectors.

<H2>License</H2>
Please note that this project is licenced under AGPL. This means that if you want to use this information in your projects, you will have to licence your project under the same license and publish your source code. This promotes distributed support for projects that target a rather small community of radar detector users.

<H2>Escort communications</H2>

Escort detectors support two types of communications: over USB and over Bluetooth. Some older devices have a physical serial port, this library does not support them. The current version of the library does not cover USB communications (firmware upgrades, data management). Only bluetooth communications are currently covered.

<H2>Escort Bluetooth IO</H2>

Escort detectors use a packet-based I/O protocol. Each packet is formatted as follows:
<PRE>
byte 0xF5 (start of packet)
byte 0x?? (length of the data in the packet, not all packets have data)
byte 0x?? Command identifier (bit 7 is always 1 for the command byte)
XX bytes of packet data (if present). Data bytes always have bit 7 set to 0
</PRE>

Before you can exhange commands, a bi-directional authentication needs to take place. Each side sends a random 64-bit challenge and validates the response. Response is calculated by XTEA-encrypting the received challenge. There are two sets of two static encryption keys. One set is used for Bluetooth devices, the other is used for newer Bluetooth-LE devices. In each set, there is a device key (used by radar detector) and client key (used by mobile app). Here is how authentication works:
<PRE>
 - Once bluetooth connnection is established, the client sends a status request command.
 - If the channel is not authenticated, the device will respond with an auth request (challenge).
 - Client sends auth response to the challenge and the device validates client's response.

[The following seps are not required for all devices, but it is always good to authenticate the other end]

 - Client sends auth request to the device.
 - Device responds to client's auth request and the client validates it.
 
 At this point, the communications channel is unlocked and regular commands can be sent and received.
 
</PRE>

See source code for more details
