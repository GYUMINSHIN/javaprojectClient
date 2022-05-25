import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientNetwork extends Thread {
    private Socket soc; // 핵심 연결 소켓
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;
 
    private DatagramSocket ds; // 서브 소켓(receive용)
    private String ui;
    private User user;
 
    public ClientNetwork(String c) {
        this.ui = c;
        System.out.println("연결중");
        try {
            soc = new Socket(c, 8040);
            System.out.println("??");
            oos = new ObjectOutputStream(soc.getOutputStream());
            ois = new ObjectInputStream(soc.getInputStream());
            System.out.println("[client] connected to server");
            ds = new DatagramSocket(soc.getLocalPort()); // // TCP와 UDP는 같은 포트로 사용할 수 있음.
 
        } catch (IOException e) {
            System.out.println("[client] network error " + e.toString());
        }
        start();
    }
 
    @Override
    public void run() {
        while (!ds.isClosed()) {
            DatagramPacket dp = new DatagramPacket(new byte[2048], 2048);
            try {
                ds.receive(dp);
                System.out.println("client UDP received");
                String data = new String(dp.getData(), 0, dp.getLength());
                System.out.println(data);
                String[] str = data.split("#");
                switch (str[0]) {
                    //  서버의 UDP 데이터 전송에 대한 값에 따라 처리
                }
            } catch (IOException e) {
                System.out.println("dp failed .. " + e.toString());
                ds.close();
                break;
            }
 
        }
    }
    
    // ==============================================================================
 
    // 클라이언트에서 서버로 요청할 데이터를 정의
    
}
