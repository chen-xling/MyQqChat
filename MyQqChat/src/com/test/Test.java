package com.test;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.util.Hashtable;

public class Test {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setTitle("JFrame����");//���ñ���
        f.setBounds(150, 150, 300, 300);//���ô������꼰��С,��λ:����
        f.setVisible(true);//���ô���ɼ�
        f.setResizable(false);//���ô����С���ɱ�
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ô���رշ�ʽ

        Container c = f.getContentPane();//��ȡ�˴�������,��������������
//        //����ı���ǩ��������ʾ
//        JLabel l = new JLabel("�Ҿ��Ǹ���ǩ!!!",SwingConstants.CENTER);
//        c.add(l);
//        Icon imag = new ImageIcon("image/friendlist/qq.png");
//        l.setIcon(imag);
//        l.setText("�ұ��ı��ˣ�");//�޸ı�ǩ����
//        System.out.println(l.getText());//��ȡ��ǩ����
//        //l.setFont(new Font("΢���ź�",Font.BOLD,18));//(�Ӵ�)���ñ�ǩ����(����+��ʽ+��С)
//        l.setForeground(Color.RED);//���ı�ǩǰ��ɫ,��������ɫ
        Hashtable<String,Object> ht = new Hashtable<>();
        String[] friends = {"10055","10077","10099","10011","10012"};
        JLabel[] jlbs = new JLabel[friends.length];
        Icon imag = new ImageIcon("image/friendlist/qq.png");
//        for(int i=0; i<friends.length; i++){
//            jlbs[i] = new JLabel(friends[i]);
//            jlbs[i].setIcon(imag);
//        }
        ht.put("�ҵĺ���",friends);
        JTree jtree = new JTree(ht);
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) jtree.getCellRenderer();
/*        renderer.setLeafIcon(null);
        renderer.setClosedIcon(new ImageIcon("image/friendlist/lie.png"));
        renderer.setOpenIcon(new ImageIcon("image/friendlist/lie.png"));*/
        //jtree.setCellRenderer(new MyTreeCellRenderer());
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(jtree);
        scrollPane.setBounds(0, 0, 400, 200);
        f.add(scrollPane);

    }
}
