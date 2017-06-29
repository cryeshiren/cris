package com.whitein.threadpool;

import com.sun.deploy.net.HttpRequest;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by CR on 2017/6/29.
 */
public class SimpleHttpServer {
    //处理http请求线程池
    static ThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<HttpRequestHandler>(5);
    //服务器文件地址即根地址
    static String basePath;
    private static ServerSocket serverSocket;
    //服务器监听端口
    static int port = 8080;

    //设置服务器端口
    public static void setPort( int port ){
        if( port>0 ){
            SimpleHttpServer.port = port;
        }
    }

    //设置服务器根路径
    public static void setBasePath( String basePath ){
        if( basePath!=null && !"".equals(basePath) && new File(basePath).exists() && new File(basePath).isDirectory()){
            SimpleHttpServer.basePath = basePath;
        }
    }

    public static void start() throws Exception {
        serverSocket = new ServerSocket(port);
        SimpleHttpServer.setBasePath("C:\\Users\\CR\\Desktop");
        System.out.println("server start listening...");
        //客户端
        Socket socket = null;
        while( (socket = serverSocket.accept()) != null ){
            threadPool.execute(new HttpRequestHandler(socket));
        }
        serverSocket.close();
    }

    static class HttpRequestHandler implements Runnable {
        private Socket socket;
        HttpRequestHandler( Socket socket ){
            this.socket = socket;
        }

        public void run() {
            //读取request
            BufferedReader in = null;
            //响应response
            PrintWriter out = null;
            //服务器文件读取
            InputStream fileIn = null;
            BufferedReader fileInn = null;

            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = in.readLine();
                //获得绝对路径
                String path = basePath.concat(header.split(" ")[1].replace("/","\\"));
                out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

                if( path.endsWith("jpg") || path.endsWith("ico")){//处理图片请求
                    fileIn = new FileInputStream(path);
                    //字节输出流
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int i = 0;
                    while((i = fileIn.read()) != -1){
                        baos.write(i);
                    }
                    byte[] bytes = baos.toByteArray();
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type: image/jpeg");
                    out.println("Content-Length: " + bytes.length);
                    out.println("");
                    socket.getOutputStream().write(bytes, 0, bytes.length);
                }else{
                    fileInn = new BufferedReader(new FileReader(path));
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type: text/html; charset=UTF-8");
                    out.println("");
                    String line;
                    while( (line = fileInn.readLine()) != null ){
                        out.print(line);
                    }
                }
                out.flush();

            } catch (IOException e) {
                e.printStackTrace();
                out.println("HTTP/1.1 500");
                out.println("");
                out.flush();
            } finally {
                HttpRequestHandler.close(in, out, fileIn, fileInn);
            }
        }

        //关闭流
        private static void close( Closeable... closeables ){
            if( closeables != null ){
                for( Closeable closeable: closeables ){
                    if( closeable!=null ){
                        try {
                            closeable.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    @Test
    public void testServer() throws Exception {
        SimpleHttpServer.start();
    }
}


