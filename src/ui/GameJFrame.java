package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    int[][] data = new int[4][4];
    int x = 0;
    int y = 0;
    String path = "puzzlegame\\image\\girl 2D\\girl 2D 1\\";
    int[][] win = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8,}, {9, 10, 11, 12}, {13, 14, 15, 0}};
    int step;

    public GameJFrame() {
        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //打乱图片
        initDate();

        //初始化图片及背景
        initImage();

        //设置为可见
        this.setVisible(true);
    }

    private void initJFrame() {
        //设置界面大小
        this.setSize(603, 680);
        //设置标题
        this.setTitle("拼图单机版 v1.0 by wjx");
        //置顶
        this.setAlwaysOnTop(true);
        //将界面居中
        this.setLocationRelativeTo(null);
        //设置关闭方式为:关闭任一界面则程序停止
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //关闭默认容器的默认居中
        this.setLayout(null);
        //给整个界面添加键盘监听事件
        this.addKeyListener(this);
    }

    //创建选项下的条目对象
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenuItem accountItem = new JMenuItem("作者微信");

    private void initJMenuBar() {
        //创建整个的菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单上面的两个选项的对象 (功能) (关于我们)
        JMenu functionMenu = new JMenu("功能");
        JMenu aboutMenu = new JMenu("关于我们");

        //给条目添加事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        //将每一个选项下面的条目添加到选项中
        functionMenu.add(replayItem);
        functionMenu.add(reLoginItem);
        functionMenu.add(closeItem);

        aboutMenu.add(accountItem);

        //将菜单里的两个选项添加到菜单当中
        jMenuBar.add(functionMenu);
        jMenuBar.add(aboutMenu);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initDate() {
        Random r = new Random();

        //打乱编号顺序
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[i];

            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }

        for (int i = 0; i < 16; i++) {
            if (tempArr[i] == 0) {                //记录空白容器的位置
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = tempArr[i];  //将打乱后的顺序赋值到二维数组中
        }
    }

    private void initImage() {
        //清空原有图片
        this.getContentPane().removeAll();

        //判断是否胜利
        if (victory()) {
            JLabel win = new JLabel(new ImageIcon("puzzlegame\\image\\win.png"));
            win.setBounds(203, 283, 193, 73);
            this.getContentPane().add(win);
        }

        //显示步数
        JLabel stepCount = new JLabel("步数:" + step);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);


        //按照二维数组的顺序生成图片
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                ImageIcon icon = new ImageIcon(path + data[i][j] + ".jpg");
                //创建一个管理容器
                JLabel label = new JLabel(icon);
                //设置位置大小
                label.setBounds(j * 105 + 83, i * 105 + 134, 105, 105);
                //设置边框 0:凸起 1:凹下
                label.setBorder(new BevelBorder(1));
                //把管理容器添加到界面中
                this.getContentPane().add(label);
            }
        }
        //设置背景
        //创建管理容器
        JLabel background = new JLabel(new ImageIcon("puzzlegame\\image\\background.png"));
        //设置背景位置，大小
        background.setBounds(40, 40, 508, 560);
        //把管理容器添加到界面中
        this.getContentPane().add(background);

        //刷新
        this.getContentPane().repaint();
    }

    public boolean victory() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j])
                    return false;
            }
        }
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //对上下左右移动进行判断  左:37 上: 38 右: 39 下: 40
        int code = e.getKeyCode();

        if (!victory()) {
            if (code == 37) {
                if (y == 3)
                    return;

                System.out.println("向左移动");
                data[x][y] = data[x][y + 1];
                data[x][y + 1] = 0;
                y++;
                //步数加1
                step++;
                //按照最新顺序加载图片
                initImage();
            } else if (code == 38) {
                if (x == 3)
                    return;

                System.out.println("向上移动");
                data[x][y] = data[x + 1][y];
                data[x + 1][y] = 0;
                x++;
                //步数加1
                step++;
                //按照最新顺序加载图片
                initImage();
            } else if (code == 39) {
                if (y == 0)
                    return;

                System.out.println("向右移动");
                data[x][y] = data[x][y - 1];
                data[x][y - 1] = 0;
                y--;
                //步数加1
                step++;
                //按照最新顺序加载图片
                initImage();
            } else if (code == 40) {
                if (x == 0)
                    return;

                System.out.println("向下移动");
                data[x][y] = data[x - 1][y];
                data[x - 1][y] = 0;
                x--;
                //步数加1
                step++;
                //按照最新顺序加载图片
                initImage();
            }
        }
        if (code == 65) {
            //清空
            this.getContentPane().removeAll();

            //加载完全图
            JLabel all = new JLabel(new ImageIcon(path + "all" + ".jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);

            //加载背景图片
            JLabel background = new JLabel(new ImageIcon("puzzlegame\\image\\background.png"));
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);

            //加载步数
            JLabel stepCount = new JLabel("步数:" + step);
            stepCount.setBounds(50, 30, 100, 20);
            this.getContentPane().add(stepCount);

            //刷新
            this.getContentPane().repaint();
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65)
            initImage();
        else if (code == 87) {
            data = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            initImage();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取被点击的条目对象
        Object obj = e.getSource();
        //判断事件源并做出响应
        if (obj == replayItem) {
            System.out.println("重新游戏");
            //清空步数
            step = 0;
            //重新打乱
            initDate();
            //重新加载图片
            initImage();
        } else if (obj == reLoginItem) {
            System.out.println("重新登录");
            //将游戏界面设置为不可见
            this.setVisible(false);
            //打开登录界面
            new LoginJFrame();
        } else if (obj == closeItem) {
            System.out.println("关闭游戏");
            //关闭虚拟机
            System.exit(0);
        } else if (obj == accountItem) {
            System.out.println("作者微信");
            //创建一个弹窗
            JDialog jDialog = new JDialog();
            //创建容器储存图片
            JLabel account = new JLabel(new ImageIcon("puzzlegame\\image\\about.png"));
            //设置容器大小位置
            account.setBounds(0, 0, 258, 258);
            //将容器添加到弹窗中
            jDialog.add(account);
            //设置弹窗大小
            jDialog.setSize(344, 344);
            //置顶
            jDialog.setAlwaysOnTop(true);
            //居中
            jDialog.setLocationRelativeTo(null);
            //弹窗不关闭则无法执行下面操作
            jDialog.setModal(true);
            //显示弹窗
            jDialog.setVisible(true);
        }
    }
}
