package com.test;
/**
 *
 */

import com.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(9999);
            //2.����ʽ�ȴ��ͻ�������  (����ֵ)Socket accept()����Ҫ���ӵ����׽��ֵĿͻ��˲���������
            Socket client = server.accept();
            System.out.println("һ���ͻ���������....");
            ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(client.getInputStream());
            User u = (User) input.readObject();
            System.out.println(u.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("����������ʧ�ܣ�");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
