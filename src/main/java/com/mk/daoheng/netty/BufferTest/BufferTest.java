package com.mk.daoheng.netty.BufferTest;

import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class BufferTest {

    public static void main(String[] args) throws Exception{
        //1、
        IntBuffer intBuffer = IntBuffer.allocate(10);
        // remark limit copacity position resert clear put get flip wrap

        //2、
        FileInputStream fileStream = new FileInputStream("");
        FileChannel fileChannel = fileStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);

        byteBuffer.putInt(10);
        byteBuffer.putChar('a');

        while (true){
            byteBuffer.clear(); //不clear会buffer中就会清空

            int read = fileChannel.read(byteBuffer);
            if(read == -1){
                break;
            }
            System.out.printf(byteBuffer.toString());
            byteBuffer.flip();
        }


        //3、  put也会改变position的值
        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(10);
        buffer.putChar('a');
        buffer.flip();

        //4、 slice() 后 于原有buffer是同一数据源  (limit 和 capacity之间的数据)
        buffer.position();

        //5、  只读buffer
        byteBuffer.asReadOnlyBuffer();

        //6、  HeapByteBuffer 堆buffer

        //7、  DirectByteBuffer 直接内存Buffer
        // new 出来的都是在java内存中
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(16);

        //8、  MappedByteBuffer  内存映射文件
        //直接像操作内存一样操作文件，对文件的修改由操作系统完成
        RandomAccessFile randomAccessFile = new RandomAccessFile("123.text","rw");
        FileChannel fileChannel1 = randomAccessFile.getChannel();
        MappedByteBuffer mappedByteBuffer = fileChannel1.map(FileChannel.MapMode.READ_WRITE,0,5);
        mappedByteBuffer.put(0,(byte)'a');
        mappedByteBuffer.put(3,(byte)'b');
        randomAccessFile.close();

        //9、  共享锁，排它锁  filelock
        FileLock fileLock = fileChannel1.lock(3,6,true);

        //10、   Acattering(一个channel读到多个buffer中) Gathering（反之）  在自定义协议时候可以使用

    }
}
