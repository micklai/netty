package com.mk.daoheng.Charset;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;

public class CharsetTest {

    public static void main(String[] args) throws Exception {
        RandomAccessFile in = new RandomAccessFile("a.text","r");
        RandomAccessFile out = new RandomAccessFile("b.text","rw");

        File file = new File("a.text");

        FileChannel inChannel = in.getChannel();
        FileChannel outChannel = out.getChannel();
        MappedByteBuffer inMapperd = inChannel.map(FileChannel.MapMode.READ_ONLY,0,file.length());

        Charset charset = Charset.forName("utf-8");
        CharsetDecoder decoder = charset.newDecoder();
        CharsetEncoder encoder = charset.newEncoder();

        CharBuffer charBuffer = decoder.decode(inMapperd);
//        outChannel.write(Charset.forName("iso-8859-1").newEncoder().encode(charBuffer));
        ByteBuffer byteBuffer = encoder.encode(charBuffer);
        outChannel.write(byteBuffer);


        in.close();
        out.close();

    }
}
