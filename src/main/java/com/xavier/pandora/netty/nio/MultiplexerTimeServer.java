package com.xavier.pandora.netty.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class MultiplexerTimeServer implements Runnable{
    public static final Logger logger = LoggerFactory.getLogger(MultiplexerTimeServer.class);
    private Selector selector;
    private ServerSocketChannel servChannel;
    private volatile boolean stop;

    public MultiplexerTimeServer(int port) {
        try {
            selector = Selector.open();
            servChannel = ServerSocketChannel.open();
            //Non blocking async pattern
            servChannel.configureBlocking(false);
            //Binding sockets (IP wildcard)
            servChannel.socket().bind(new InetSocketAddress(port), 1024);
            //Register this channel with the given selector
            servChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                //休眠时间设置为1秒，无论是否有读写事件发生，selector每隔1秒都被唤醒一次。
                //selector也提供一个无参的select方法：当有处于就绪状态的Channel时，selector将返回该Channel的SelectionKey集合。
                //通过对就绪状态的Channel集合进行迭代，可以进行网络的异步读写操作。
                //This method performs a blocking selection operation.  It returns only after at least one channel is selected,
                // this selector's method is invoked, the current thread is interrupted, or the given timeout period expires,
                // whichever comes first.
                selector.select(1000);
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectedKeys.iterator();
                SelectionKey key = null;
                while(it.hasNext()) {
                    key = it.next();
                    it.remove();
                    try{
                        //处理客户端的请求信息
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if(key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key)  throws IOException{
        if(key.isValid()) { //Tells whether or not this key is valid
            if(key.isAcceptable()) { //Tests whether this key's channel is ready to accept a new socket connection
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                //接收客户端的连接请求并创建SocketChannel实例
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                sc.register(selector, SelectionKey.OP_READ);
            }
            if(key.isReadable()) {
                SocketChannel sc = (SocketChannel) key.channel();
                //开辟1MB的缓冲区
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                //因为上面已经将SocketChannel设置为非阻塞模式，所以下面的sc.read是非阻塞的
                int readBytes = sc.read(readBuffer);
                //read返回值>0 - 读到了字节，对字节进行编解码
                //read返回值=0 - 没有读到字节，属于正常场景，忽略；
                //read返回值=-1：链路已经关闭，需要关闭SocketChannel，释放资源
                if(readBytes >0) {
                    //Flips this buffer.  The limit is set to the current position and then the position is set to zero.
                    readBuffer.flip();
                    //The number of elements remaining in this buffer
                    byte[] bytes = new byte[readBuffer.remaining()];
                    //This method transfers bytes from this buffer into the given destination array.
                    readBuffer.get(bytes);
                    String body = new String (bytes, "UTF-8");
                    logger.info("The time server receive order: " + body);
                    String currentTime = "QUERY TIME ORDER"
                            .equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString()
                            : "BAD ORDER";
                    doWrite(sc, currentTime);
                } else if (readBytes < 0) {
                    //对端链路关闭
                    key.cancel();
                    sc.close();
                } else {
                    ; //读到0字节，忽略
                }
            }
        }
    }

    private void doWrite(SocketChannel channel,String response) throws IOException {
        if (response != null && response.trim().length() > 0) {
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
            logger.info("Current Time: " + response);
        }
    }
}
