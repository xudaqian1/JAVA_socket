import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

/**
 * 代码来源：http://blog.csdn.net/yaoyun2013/article/details/7411383
 */
@SuppressWarnings("serial")
public class progress_bar extends JWindow  {
    // 定义加载窗口大小，显示加载图片和进度条合起来的大小
    public static final int LOAD_WIDTH =290;
    public static final int LOAD_HEIGHT = 190;
    // 获取屏幕窗口大小,也就是电脑分辨率
    public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    // 定义进度条组件  
    public JProgressBar progressbar;
    // 定义标签组件  
    public JLabel label;

    // 构造函数  
    public progress_bar() {

        // 创建标签,并在标签上放置一张图片  
        label = new JLabel(new ImageIcon("image/file.jpg"));
        label.setBounds(0, 0, LOAD_WIDTH, LOAD_HEIGHT -15);//设置背景图片在组件中的位置，-15是为了放置进度条
        // 创建进度条  
        progressbar = new JProgressBar();
        // 显示当前进度值信息  
        progressbar.setStringPainted(true);
        // 设置进度条边框不显示  
        progressbar.setBorderPainted(false);
        // 设置进度条的前景色  
        progressbar.setForeground(new Color(0, 210, 40));//设置进度条条中在动的那部分的颜色
        // 设置进度条的背景色  
        progressbar.setBackground(new Color(188, 190, 194));//设置进度条中不懂得那部分的颜色
        progressbar.setBounds(0, LOAD_HEIGHT - 15, LOAD_WIDTH, 15);//设置进度条的位置，在下方高度为15
        // 添加组件  
        this.add(label);
        this.add(progressbar);
        // 设置布局为空  
        this.setLayout(null);
        // 设置窗口初始位置  
        this.setLocation((WIDTH - LOAD_WIDTH) / 2, (HEIGHT - LOAD_HEIGHT) / 2);//设置在屏幕正中间
        // 设置窗口大小  
        this.setSize(LOAD_WIDTH, LOAD_HEIGHT);
        // 设置窗口显示  
        this.setVisible(true);//

    }
    //@Override
    public void run(int  test) {
        /*
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }*/
        if(test<100)
        {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            progressbar.setValue(test);
        }
        else {
            JOptionPane.showMessageDialog(this, "加载完成");
            this.dispose();
        }
    }
/*
    public static void main(String[] args) {
        progress_bar t = new progress_bar();

      t.run();
    }
*/
}