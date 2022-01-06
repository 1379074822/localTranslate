import com.intellij.idea.IdeaLogger;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TranslateDialog extends DialogWrapper {

    private String projectName; //假如需要获取到项目名，作为该类的属性放进来
    Logger ins = IdeaLogger.getInstance(TranslateAction.class);
    private JPanel north = new JPanel();

    private JScrollPane center = new JScrollPane();
    private JLabel label = new JLabel();

    public JTextField input = new JTextField();

    JTextArea after = new JTextArea("");

    {
        input.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                try {
                    if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                        String key = input.getText().toLowerCase().trim();

                        String msg = "";
                        msg = TranslateAction.getString(msg, key);
                        msg =" " + StringUtils.replace(msg,",","\n");
                        after.setText(msg);
                        initCenter();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
                    close(1);
                }

                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    input.requestFocus();
                    input.transferFocus();
                    input.grabFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });


    }




    public TranslateDialog(@Nullable Project project) {
        super(project);
        setTitle("内网翻译小王子~~"); // 设置会话框标题
        setAutoAdjustable(true);
        this.projectName = project.getName();
        init();
    }

    @Override
    protected void init(){
        super.init();
        setSize(1000,1000);
    }

    @Override
    protected JComponent createNorthPanel() {
        return initNorth(); //返回位于会话框north位置的swing样式
    }


    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return initCenter();
    }

    @Override
    protected JComponent createSouthPanel() {
        return initSouth();
    }


    public static void main(String[] args) {
        String s = " gums a ";
        String s1 = s.replaceAll("[^a-z ]", "");
        System.out.println(s.trim());
    }
    public JPanel initNorth() {

        north.setLayout(new GridLayout(1, 1));
//        JLabel title = new JLabel("表单标题");
//        title.setFont(new Font("微软雅黑", Font.PLAIN, 26)); //字体样式
//        title.setHorizontalAlignment(SwingConstants.CENTER); //水平居中
//        title.setVerticalAlignment(SwingConstants.CENTER); //垂直居中
//        north.add(title);
        input.setFont(new Font("微软雅黑",Font.PLAIN, 16));
        input.setFocusable(true);
        north.add(input);
        return north;
    }

    public JScrollPane initCenter() {
        after.setLineWrap(true);
        after.setColumns(45);
        after.setRows(13);
        after.setEditable(false);
        after.setFont(new Font("微软雅黑",Font.PLAIN, 16));
        center.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        center.setViewportView(after);
        return center;
    }

    public JPanel initSouth() {
        JPanel s = new JPanel();
        return s;
    }
}
