package Network.Shared.Packet;

import Network.Shared.Util.FriendlyByteR;

// Every packet should have an associated packet Listener
// Thats what this is saying
public interface Packet<T extends PacketListener> {

//    Every packet should have a handler function
    void handle(T packetListener);

//    Every packet should have a write function in order to convert itself to bytes for sending
    void write(FriendlyByteR byteBuf);
}
