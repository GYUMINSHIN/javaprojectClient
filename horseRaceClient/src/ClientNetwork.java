import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientNetwork extends Thread {
    private Socket soc; // �ٽ� ���� ����
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;
 
    private DatagramSocket ds; // ���� ����(receive��)
    private String ui;
    
    public User[] users;
    public int userNum;
    public String card;
    public int flag = 0; // 0: Loading / 1: Card / 2: Horse / 3: Winner / 4: Game Over
    public int symbol = 0, curPosition = 0, movePosition = 0;
 
    public ClientNetwork(String c) {
        this.ui = c;
        System.out.println("������");
        try {
            soc = new Socket(c, 8040);
            System.out.println("??");
            oos = new ObjectOutputStream(soc.getOutputStream());
            ois = new ObjectInputStream(soc.getInputStream());
            if (soc.isConnected())
            {
                System.out.println("[client] connected to server");	
            }
            ds = new DatagramSocket(soc.getLocalPort()); // // TCP�� UDP�� ���� ��Ʈ�� ����� �� ����.
 
        } catch (IOException e) {
            System.out.println("[client] network error " + e.toString());
        }
        start();
    }
 
    @Override
    public void run() {
//        while (!ds.isClosed()) {
//            DatagramPacket dp = new DatagramPacket(new byte[2048], 2048);
//            try {
//                ds.receive(dp);
//                System.out.println("client UDP received");
//                String data = new String(dp.getData(), 0, dp.getLength());
//                System.out.println(data);
//                String[] str = data.split("#");
//                switch (str[0]) {
//                    //  ������ UDP ������ ���ۿ� ���� ���� ���� ó��
//                }
//            } catch (IOException e) {
//                System.out.println("dp failed .. " + e.toString());
//                ds.close();
//                break;
//            }
// 
//        }
    	String[] command = null;
        while(soc.isConnected()) {
            String received = null;
            try {
                received = (String)ois.readObject();
            }catch(IOException | ClassNotFoundException e) {    // ������ �����
                System.out.println("[client] ������ ����");
            }
            
//            System.out.println(received);
            command = received.split("#");
            Object resp = null;
//            System.out.println(command[0]);
            switch(command[0]) {
            case "s":
//            	System.out.println(command[1]);
            	break;
            case "n":
            	userNum = Integer.valueOf(command[1]);
            	users = new User[userNum];
            	break;
            case "u":
            	users[Integer.valueOf(command[1])] = new User(command[2], 0);
//            	System.out.println(users[Integer.valueOf(command[1])].name);
            	break;
            case "c":
            	while (flag != 0) {
            		try {
//            			System.out.println(flag);
						this.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
            	}
            	card = command[1];
//            	System.out.println(card);
            	flag = 1;
            	break;
            case "h":
            	while (flag != 0) {
            		try {
//            			System.out.println(flag);
						this.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
            	}
            	symbol = Integer.valueOf(command[1]);
            	curPosition = Integer.valueOf(command[2]);
            	movePosition = Integer.valueOf(command[3]);
            	flag = 2;
            	break;
            case "w":
            	while (flag != 0) {
            		try {
            			this.sleep(10);
            		} catch (InterruptedException e) {
            			e.printStackTrace();
            		}
            	}
				System.out.println(users[Integer.valueOf(command[1])].name + " gets " + (Integer.valueOf(command[2]) - users[Integer.valueOf(command[1])].score) + " points!");
            	users[Integer.valueOf(command[1])].score = Integer.valueOf(command[2]);
            	flag = 3;
            	break;
            case "e":
            	while (flag != 0) {
            		try {
            			this.sleep(10);
            		} catch (InterruptedException e) {
            			e.printStackTrace();
            		}
            	}
            	flag = 4;
            	break;
            }
        }
    }
    
    // ==============================================================================
 
    // Ŭ���̾�Ʈ���� ������ ��û�� �����͸� ����
    
    public void send(String s) {
    	try {
			oos.writeObject(s);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
