import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class CrazyNameDialog extends DialogWrapper {

    private JPanel north = new JPanel();

    private JPanel center = new JPanel();

    private JTextField input = new JTextField();

    private JTextArea after = new JTextArea("");

    {
        input.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                try {
                    if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                        after.setText("");
                        String key = input.getText().toLowerCase().trim();
                        String[] split = key.split(" ");
                        StringBuilder sb = new StringBuilder();
                        boolean flag = false;
                        for (String s : split) {
                            if(StringUtils.isEmpty(s)){
                                continue;
                            }
                            String value = ChineseRead.map.get(s);
                            String[] split1 = value.split(",");
                            Optional<String> min = Arrays.stream(split1).min(Comparator.comparingInt(String::length));
                            if(min.isPresent()){
                                String s1 = min.get().replaceAll("[^a-zA-Z ]", "");
                                String s2 = s1.toLowerCase().trim();
                                String[] s3 = s2.split(" ");
                                for (String ss : s3) {
                                    String s4 = ss;
                                    if(flag){
                                         s4 = toBig(ss);
                                    }
                                    sb.append(s4);
                                    flag = true;
                                }
                            }else{
                                sb.append("xxxx");
                            }
                        }
                        after.setText(sb.toString());
                        initCenter();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
                    close(1);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public static String toBig(String str) {
        // 转换成字符数组
        char[] ch = str.toCharArray();
        // 判断首字母是否是字母
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            // 利用ASCII码实现大写
            ch[0] = (char) (ch[0] - 32);
        }
        return String.valueOf(ch);
    }

    public CrazyNameDialog(@Nullable Project project) {
        super(project);
        setTitle("稀奇古怪起名器");
        setAutoAdjustable(true);
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


    public JPanel initNorth() {

        north.setLayout(new GridLayout(1, 1));
//        JLabel title = new JLabel("表单标题");
//        title.setFont(new Font("微软雅黑", Font.PLAIN, 26)); //字体样式
//        title.setHorizontalAlignment(SwingConstants.CENTER); //水平居中
//        title.setVerticalAlignment(SwingConstants.CENTER); //垂直居中
//        north.add(title);
        input.setFont(new Font("微软雅黑",Font.PLAIN, 16));
        input.grabFocus();
        north.add(input);

        return north;
    }

    public JPanel initCenter() {
        after.setLineWrap(true);
        after.setColumns(45);
        after.setRows(13);
        after.setEditable(false);
        after.setFont(new Font("微软雅黑",Font.PLAIN, 16));
        center.add(after);
        return center;
    }

    public JPanel initSouth() {
        JPanel s = new JPanel();
        return s;
    }
}
