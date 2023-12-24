package Network.Shared.Util;

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


    public FriendlyByteR writeInt(int value) {
        while((value & -128) != 0) {
            this.writeByte(value & 127 | 128);
            value >>>= 7;
        }

        this.writeByte(value);
        return this;
    }
    public int readInt() {
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

    public FriendlyByteR writeString(String text) {
        return this.writeString(text, 32767);
    }
    public FriendlyByteR writeString(String text, int maxLen) {
        if (text.length() > maxLen) {
            throw new EncoderException("String too big (was " + text.length() + " characters, max " + maxLen + ")");
        } else {
            byte[] abyte = text.getBytes(StandardCharsets.UTF_8);
            int i = getMaxEncodedUtfLength(maxLen);
            if (abyte.length > i) {
                throw new EncoderException("String too big (was " + abyte.length + " bytes encoded, max " + i + ")");
            } else {
                this.writeInt(abyte.length);
                this.writeBytes(abyte);
                return this;
            }
        }
    }


    public String readString() {
        return this.readString(32767);
    }
    public String readString(int maxUtfLength) {
        int i = getMaxEncodedUtfLength(maxUtfLength);
        int j = this.readInt();
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




    public FriendlyByteR writeIntArray(int[] intArray) {
        this.writeInt(intArray.length);

        for(int i : intArray) {
            this.writeInt(i);
        }

        return this;
    }
    public int[] readIntArray() {
        int i = this.readInt();
        int[] aint = new int[i];

        for(int j = 0; j < aint.length; ++j) {
            aint[j] = this.readInt();
        }

        return aint;
    }

    //  ByteBuf Functions
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
    public String toString(int index, int length, Charset charset) {
        return this.source.toString(index, length, charset);
    }
}
