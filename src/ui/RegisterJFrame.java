package ui;

import javax.swing.*;

public class RegisterJFrame extends JFrame {

    public RegisterJFrame(){

        initJFrame();

        this.setVisible(true);
    }

    private void initJFrame() {
        //设置界面大小
        this.setSize(488,500);
        //设置标题
        this.setTitle("拼图单机版 注册界面");
        //置顶
        this.setAlwaysOnTop(true);
        //将界面居中
        this.setLocationRelativeTo(null);
        //设置关闭方式为:关闭任一界面则程序停止
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
