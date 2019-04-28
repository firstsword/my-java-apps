package nioStudy;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by firstsword on 2019/3/1.
 */
public class Demo1 {

    //http://www.jasongj.com/java/nio_reactor/

    @Test
    public void t1() throws Exception {
        //使用FileChannel的零拷贝将本地文件内容传输到网络的示例代码如下所示。

        SocketChannel socketChannel = SocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(1234);
        socketChannel.connect(address);
        RandomAccessFile file = new RandomAccessFile(
                Demo1.class.getClassLoader().getResource("test.txt").getFile(), "rw");
        FileChannel channel = file.getChannel();
        channel.transferTo(0, channel.size(), socketChannel);
        channel.close();
        file.close();
        socketChannel.close();
    }

    @Test
    public void t2() throws Exception {
        //单线程逐个处理所有请求

        //使用阻塞I/O的服务器，一般使用循环，逐个接受连接请求并读取数据，然后处理下一个请求
        ServerSocket socket = new ServerSocket(6666);

        while (true) {
            Socket accept = socket.accept();
            InputStream is = accept.getInputStream();
            //IOUtils.toString(is);

            //同一时间只能处理一个请求，等待I/O的过程浪费大量cpu资源，同时无法充分使用多cpu的优势
        }

    }

    @Test
    public void t3() throws Exception {
        //用多线程改进阻塞的io
        ServerSocket server = new ServerSocket(6666);

        while (true) {
            Socket socket = server.accept();

            new Thread(() -> {
                try {
                    InputStream is = socket.getInputStream();
                    //IOUtils.toString(is);

                    //一个连接建立之后，创建一个线程来处理其I/O操作
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }

    @Test
    public void t4() throws Exception {
        //为了防止请求过多，导致服务器创建的线程数过多，造成多线程上下文切换的开销，可以通过线程池来限制创建的线程数
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        ServerSocket server = new ServerSocket(6666);

        while (true) {
            Socket socket = server.accept();

            executorService.submit(() -> {
                try {
                    InputStream is = socket.getInputStream();
                    //IOUtils.toString(is)
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    //socket.close();
                }
            });
        }
    }

    @Test
    public void t5() throws Exception {
        //经典的Reactor模式

        //Acceptor处理客户端连接请求
        //Reactor将I/O事件发配给对应的Handler
        //Handler执行非阻塞读/写

        //最简单的Reactor模式的实现如下

        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(6666));

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //selector.select()是阻塞的
        while (selector.select() > 0) {
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();

            while (it.hasNext()) {
                SelectionKey key = it.next();
                it.remove();

                if (key.isAcceptable()) {
                    ServerSocketChannel acceptChannel = (ServerSocketChannel) key.channel();

                    SocketChannel socketChannel = acceptChannel.accept();
                    socketChannel.configureBlocking(false);

                    //System.out.println(socketChannel.getRemoteAddress());

                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();

                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int count = socketChannel.read(buffer);

                    if (count <= 0) {
                        socketChannel.close();
                        key.cancel();
                        continue;
                    }

                    //System.out.println(new String(buffer.array()));
                }

                keys.remove(key);
            }
        }
    }

    @Test
    public void t6() throws Exception {
        //System.out.println(1 << 4);
    }


}

/*
Unix下五种I/O模型：


需要注意的是，阻塞并不等价于同步，而非阻塞并非等价于异步。事实上这两组概念描述的是I/O模型中的两个不同维度。


1.同步和异步着重点在于多个任务执行过程中，后发起的任务是否必须等先发起的任务完成之后再进行。
而不管先发起的任务请求是阻塞等待完成，还是立即返回通过循环等待请求成功。

2.而阻塞和非阻塞重点在于请求的方法是否立即返回（或者说是否在条件不满足时被阻塞）。


阻塞I/O两个阶段：
1：等待数据就绪。网络 I/O 的情况就是等待远端数据陆续抵达；磁盘I/O的情况就是等待磁盘数据从磁盘上读取到内核态内存中。
2：数据拷贝。出于系统安全，【用户态】的程序没有权限直接读取【内核态】内存，因此内核负责把内核态内存中的数据拷贝一份到用户态内存中。


非阻塞I/O
非阻塞I/O请求包含如下三个阶段

socket设置为 NONBLOCK（非阻塞）就是告诉内核，当所请求的I/O操作无法完成时，不要将线程睡眠，而是返回一个错误码(EWOULDBLOCK) ，这样请求就不会阻塞。
I/O操作函数将不断的测试数据是否已经准备好，如果没有准备好，继续测试，直到数据准备好为止。整个I/O 请求的过程中，虽然用户线程每次发起I/O请求后可以立即返回，但是为了等到数据，仍需要不断地轮询、重复请求，消耗了大量的 CPU 的资源。
数据准备好了，从内核拷贝到用户空间。
一般很少直接使用这种模型，而是在其他I/O模型中使用非阻塞I/O 这一特性。这种方式对单个I/O 请求意义不大，但给I/O多路复用提供了条件。


异步I/O
调用aio_read 函数，告诉内核描述字，缓冲区指针，缓冲区大小，文件偏移以及通知的方式，然后立即返回。
当内核将数据拷贝到缓冲区后，再通知应用程序。所以异步I/O模式下，阶段1和阶段2全部由内核完成，完成不需要用户线程的参与。


*/



/*
Java中四种I/O模型
        上一章所述Unix中的五种I/O模型，除信号驱动I/O外，Java对其它四种I/O模型都有所支持。
        其中Java最早提供的blocking I/O即是阻塞I/O，而NIO即是非阻塞I/O，
        同时通过NIO实现的Reactor模式即是I/O复用模型的实现，通过AIO实现的Proactor模式即是异步I/O模型的实现。

从IO到NIO
面向流 vs. 面向缓冲
Java IO是面向流的，每次从流（InputStream/OutputStream）中读一个或多个字节，直到读取完所有字节，它们没有被缓存在任何地方。
另外，它不能前后移动流中的数据，如需前后移动处理，需要先将其缓存至一个缓冲区。

Java NIO面向缓冲，数据会被读取到一个缓冲区，需要时可以在缓冲区中前后移动处理，这增加了处理过程的灵活性。
但与此同时在处理缓冲区前需要检查该缓冲区中是否包含有所需要处理的数据，
并需要确保更多数据读入缓冲区时，不会覆盖缓冲区内尚未处理的数据。


阻塞 vs. 非阻塞
Java IO的各种流是阻塞的。当某个线程调用read()或write()方法时，该线程被阻塞，直到有数据被读取到或者数据完全写入。
阻塞期间该线程无法处理任何其它事情。

Java NIO为非阻塞模式。
读写请求并不会阻塞当前线程，在数据可读/写前当前线程可以继续做其它事情，所以一个单独的线程可以管理多个输入和输出通道。

选择器（Selector）
Java NIO的选择器允许一个单独的线程同时监视多个通道，可以注册多个通道到同一个选择器上，
然后使用一个单独的线程来“选择”已经就绪的通道。这种“选择”机制为一个单独线程管理多个通道提供了可能。


零拷贝
Java NIO中提供的FileChannel拥有transferTo和transferFrom两个方法，可直接把FileChannel中的数据拷贝到另外一个Channel，
或者直接把另外一个Channel中的数据拷贝到FileChannel。该接口常被用于高效的网络/文件的数据传输和大文件拷贝。
在操作系统支持的情况下，通过该方法传输数据并不需要将源数据从内核态拷贝到用户态，再从用户态拷贝到目标通道的内核态，
同时也避免了两次用户态和内核态间的上下文切换，也即使用了“零拷贝”，所以其性能一般高于Java IO中提供的方法。
*/
