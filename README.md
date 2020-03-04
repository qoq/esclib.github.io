<H1>esclib.github.io<H1>

This repository describes a reverse-engineered Escort radar detector protocol and provides a utility library to communicate to Escort/Beltronics radar detectors.

<H2>License</H2>
Please note that this project is licenced under AGPL. This means that if you want to use this information in your projects, you will have to licence your project under the same license and publish your source code. This promotes distributed support for projects that target a rather small community of radar detector users.

<H2>Escort communications</H2>

Escort detectors support two types of communications: over USB and over Bluetooth. Some older devices have a physical serial port, this library does not support them. The current version of the library does not cover USB communications (firmware upgrades, data management). Only bluetooth communications are currently covered.

<H2>Escort Bluetooth IO</H2>

Escort detectors use a very packet-based simple protocol. Each packet is formatted as follows:
byte 0xF5 (start of packet)
byte XX (length of the data in the packet, not all packets have data)
byte CC Command identifier
XX bytes of packet data (if present)
