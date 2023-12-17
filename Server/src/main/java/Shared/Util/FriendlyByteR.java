package Shared.Util;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


// This entire class is from Minecraft 1.20.1
public class FriendlyByteR {

    private final ByteBuf source;

//    Constructor
    public FriendlyByteR(ByteBuf rawBytesSource) {
        this.source = rawBytesSource;
    }


    public FriendlyByteR writeVarInt(int value) {
        while((value & -128) != 0) {
            this.writeByte(value & 127 | 128);
            value >>>= 7;
        }

        this.writeByte(value);
        return this;
    }
    public int readVarInt() {
        int i = 0;
        int j = 0;

        byte currentByte;
        do {
            currentByte = this.readByte();
            i |= (currentByte & 127) << j++ * 7;
            if (j > 5) {
                throw new RuntimeException("VarInt too big");
            }
        } while((currentByte & 128) == 128);

        return i;
    }

    public FriendlyByteR writeUtf(String p_130071_) {
        return this.writeUtf(p_130071_, 32767);
    }
    public FriendlyByteR writeUtf(String p_130073_, int p_130074_) {
        if (p_130073_.length() > p_130074_) {
            throw new EncoderException("String too big (was " + p_130073_.length() + " characters, max " + p_130074_ + ")");
        } else {
            byte[] abyte = p_130073_.getBytes(StandardCharsets.UTF_8);
            int i = getMaxEncodedUtfLength(p_130074_);
            if (abyte.length > i) {
                throw new EncoderException("String too big (was " + abyte.length + " bytes encoded, max " + i + ")");
            } else {
                this.writeVarInt(abyte.length);
                this.writeBytes(abyte);
                return this;
            }
        }
    }


    public String readUtf() {
        return this.readUtf(32767);
    }
    public String readUtf(int maxUtfLength) {
        int i = getMaxEncodedUtfLength(maxUtfLength);
        int j = this.readVarInt();
        if (j > i) {
            throw new DecoderException("The received encoded string buffer length is longer than maximum allowed (" + j + " > " + i + ")");
        } else if (j < 0) {
            throw new DecoderException("The received encoded string buffer length is less than zero! Weird string!");
        } else {
            String s = this.toString(this.readerIndex(), j, StandardCharsets.UTF_8);
            this.readerIndex(this.readerIndex() + j);
            if (s.length() > maxUtfLength) {
                throw new DecoderException("The received string length is longer than maximum allowed (" + s.length() + " > " + maxUtfLength + ")");
            } else {
                return s;
            }
        }
    }
    private static int getMaxEncodedUtfLength(int p_236871_) {
        return p_236871_ * 3;
    }


//    ByteBuf Functions
    public ByteBuf writeByte(int p_130470_) {
        return this.source.writeByte(p_130470_);
    }
    public byte readByte() {
        return this.source.readByte();
    }
    public int readerIndex() {
        return this.source.readerIndex();
    }
    public ByteBuf readerIndex(int p_130343_) {
        return this.source.readerIndex(p_130343_);
    }
    public ByteBuf writeBytes(byte[] p_130493_) {
        return this.source.writeBytes(p_130493_);
    }
    public String toString(int p_130454_, int p_130455_, Charset p_130456_) {
        return this.source.toString(p_130454_, p_130455_, p_130456_);
    }



}
