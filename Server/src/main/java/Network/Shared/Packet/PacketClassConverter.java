package Network.Shared.Packet;

import Network.Shared.Packet.PacketDefinition.*;
import Network.Shared.Util.FriendlyByteR;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

// This converts classes to packet IDs and IDs to Class constructors
public class PacketClassConverter {

    //    Used to get the packetID from a class type (ENCODING)
    public static final Map<Class<?>, Integer> CLASS_TO_ID =
            new HashMap<>() {{
                put(ClientboundChatPacket.class, 1);
                put(ServerboundLoginPacket.class, 2);
                put(ServerboundChatPacket.class, 3);
                put(ServerboundDisconnectPacket.class, 4);
                put(ClientboundMapPacket.class, 5);
            }};

    //    Used to get the constructor from a packetID (DECODING)
    public static final Map<Integer, Function<FriendlyByteR, Packet<?>>> ID_TO_CONSTRUCTOR =
            new HashMap<>() {{
                put(PacketClassConverter.CLASS_TO_ID.get(ClientboundChatPacket.class), ClientboundChatPacket::new);
                put(PacketClassConverter.CLASS_TO_ID.get(ServerboundLoginPacket.class), ServerboundLoginPacket::new);
                put(PacketClassConverter.CLASS_TO_ID.get(ServerboundChatPacket.class), ServerboundChatPacket::new);
                put(PacketClassConverter.CLASS_TO_ID.get(ServerboundDisconnectPacket.class), ServerboundDisconnectPacket::new);
                put(PacketClassConverter.CLASS_TO_ID.get(ClientboundMapPacket.class), ClientboundMapPacket::new);
            }};
}
