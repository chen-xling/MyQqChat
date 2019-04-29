package com.server.model;

import com.common.Message;
import com.common.MsgType;
import com.common.User;
import com.server.tools.JDBC_Util;
import com.server.tools.ManageClientThread;
import com.server.tools.ServerConClientThread;
import com.server.view.ServerFrame;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    private ServerSocket server;
    private Socket client;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private volatile boolean isRunning;

    public Server(){
        System.out.println("---------------Server(9999)---------------");
        isRunning = true;
        new Thread(this).start();
    }

    /**
     * �����߳�����
     */
    public void myStop() {
        isRunning = false;
        close(server);
    }

    @Override
    public void run() {
        try {
            //1.���÷������׽��� ServerSocket(int port)�����󶨵�ָ���˿ڵķ������׽���
            server = new ServerSocket(9999);
            while(isRunning) {
                //2.����ʽ�ȴ��ͻ�������  (����ֵ)Socket accept()����Ҫ���ӵ����׽��ֵĿͻ��˲���������
                client = server.accept();
                System.out.println("һ���ͻ���������....");
                input = new ObjectInputStream(client.getInputStream());
                output = new ObjectOutputStream(client.getOutputStream());
                User u = (User)input.readObject();
                System.out.println(u.toString());
                doUserLogin(u);
            }
        } catch (IOException e) {
            close(output,input,client,server);//�ͷ���Դ
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * �����û���¼
     * @param u
     */
    private void doUserLogin(User u){
        Message msg = new Message();
        JDBC_Util jc = new JDBC_Util();
        //���û�δ��¼
        if(null == ManageClientThread.getClientThread(u.getUid())){
            try{
                String qname = jc.checkUserInfo(u);
                if(null != qname){//��¼�ɹ�
                    msg.setType(MsgType.LOGIN_SUCCEED);
                    msg.setContent(qname + "-" + jc.getFriendsList(u.getUid()));
                    output.writeObject(msg);
                    //�ͻ������ӳɹ���Ϊ�䴴���̱߳������������ͨѶ
                    ServerConClientThread th = new ServerConClientThread(client);
                    th.start();
                    //������ӵ��̼߳���
                    ManageClientThread.addClientThread(u.getUid(),th);
                    //֪ͨ�����û�
                    th.notifyOthers(u.getUid());
                    ServerFrame.showMsg("�û�"+u.getUid()+"�ɹ���¼��");
                }else{
                    msg.setType(MsgType.LOGIN_FAILED);
                    output.writeObject(msg);
                    close(output,input,client);
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }else{//���û��ѵ�¼
            try {
                msg.setType(MsgType.ALREADY_LOGIN);
                output.writeObject(msg);
                close(output,input,client);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ���ڹرն��io��
     * @param ios
     */
    private void close(Closeable... ios) {//�ɱ䳤����
        for(Closeable io: ios) {
            try {
                if(null != io)
                    io.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
