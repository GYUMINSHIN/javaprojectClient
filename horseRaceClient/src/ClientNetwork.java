import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientNetwork extends Thread {
    private Socket soc; // �ٽ� ���� ����
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;
 
    private DatagramSocket ds; // ���� ����(receive��)
    private String ui;
    private User user;
 
    public ClientNetwork(String c) {
        this.ui = c;
        System.out.println("������");
        try {
            soc = new Socket(c, 8040);
            System.out.println("??");
            oos = new ObjectOutputStream(soc.getOutputStream());
            ois = new ObjectInputStream(soc.getInputStream());
            System.out.println("[client] connected to server");
            ds = new DatagramSocket(soc.getLocalPort()); // // TCP�� UDP�� ���� ��Ʈ�� ����� �� ����.
 
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
                    //  ������ UDP ������ ���ۿ� ���� ���� ���� ó��
                }
            } catch (IOException e) {
                System.out.println("dp failed .. " + e.toString());
                ds.close();
                break;
            }
 
        }
    }
    
    // ==============================================================================
 
    // Ŭ���̾�Ʈ���� ������ ��û�� �����͸� ����
    
}
