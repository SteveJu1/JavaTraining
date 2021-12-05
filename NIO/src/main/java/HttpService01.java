import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpService01 {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8081);
        while (true) {
            System.out.println("pending when socket accept");
            Socket socket = serverSocket.accept();
            System.out.println("after socket accept");
            service(socket);
        }
    }

    private static void service(Socket socket) throws IOException, InterruptedException {
        System.out.println(socket.toString());
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

        printWriter.println("HTTP/1.1 200 OK");
        printWriter.println("Content-Type:text/html;charset=utf-8");
        String body = "hello,nio1";
        printWriter.println("Content-Length:" + body.getBytes().length);
        printWriter.println();
        printWriter.write(body);
        printWriter.close();
        socket.close();
        System.out.println("finish thread work,current thread:" + Thread.currentThread().getName());
    }
}
