package com.test;
import java.awt.Component;

import com.common.Message;
import com.common.MsgType;
import javax.swing.ImageIcon;
import javax.swing.JTree;  
import javax.swing.tree.DefaultMutableTreeNode;  
import javax.swing.tree.DefaultTreeCellRenderer;  
  
/** 
 * �Զ�����������,������ÿ���ڵ����óɲ�ͬ��ͼ�� 
 * @author RuiLin.Xie - xKF24276 
 * 
 */  
public class MyTreeCellRenderer extends DefaultTreeCellRenderer {

    private static final long   serialVersionUID    = 1L;
    private Message msg;

    public MyTreeCellRenderer(Message msg){
        this.msg = msg;
    }

    /** 
     * ��дgetTreeCellRendererComponent()
     */
    public Component getTreeCellRendererComponent(JTree tree, Object value,  
            boolean sel, boolean expanded, boolean leaf, int row,  
            boolean hasFocus) {
  
        //ִ�и���ԭ�Ͳ���  
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,  
                row, hasFocus);

        //�õ�ÿ���ڵ��TreeNode  
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        //�õ�ÿ���ڵ��text
        String str = node.toString();
        //��¼�ɹ�ʱ��ʼ���б�
		if(msg.getType() == MsgType.LOGIN_SUCCEED) {
            if (node.isLeaf()) {
                this.setIcon(new ImageIcon("image/friendlist/qq.png"));
            }else
                this.setIcon(new ImageIcon("image/friendlist/lie.png"));
        //�ѻ�ȡ�����ߺ����б�
        }else if(msg.getType() == MsgType.RET_ONLINE_FRIENDS) {
        	String [] onlineFriends = msg.getContent().split(" ");
        	if (node.isLeaf()) {
        		this.setIcon(new ImageIcon("image/friendlist/qq.png"));
        		for(int i=0;i<onlineFriends.length;i++) {
        			if(str.equals(onlineFriends[i]))
        				this.setIcon(new ImageIcon("image/friendlist/qq.gif"));
        		}

        	}else
        		this.setIcon(new ImageIcon("image/friendlist/lie.png"));
        }
        return this;  
    }  
  
}  