import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class CrazyNameDialog extends DialogWrapper {

    private JPanel north = new JPanel();

    private JScrollPane center = new com.intellij.ui.components.JBScrollPane();

    public JTextField input = new JTextField();

    private JTextArea after = new JTextArea("");

    {
        input.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                try {
                    if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                        after.setText("");
                        String key = input.getText().toLowerCase().trim();
                        String[] split = key.split(" ");
                        if(split.length<1){
                            return;
                        }
                        List<List<String>> lists = new ArrayList<>();
                        for (int i = 0; i < split.length; i++) {
                            List<String> strings = new ArrayList<>();
                            String s = split[i];
                            if(StringUtils.isEmpty(s)){
                                continue;
                            }
                            String value = ChineseRead.map.get(s);
                            if(StringUtils.isEmpty(value)){
                                strings.add("xxxx");
                                lists.add(strings);
                                continue;
                            }
                            String[] split1 = value.split(",");
                            List<String> collect = Arrays.stream(split1).map(x->{
                                if(x.contains("\\[")&&x.contains("\\]")){
                                    return x.split("\\[")[0];
                                }
                                return x;
                            }).sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
                            int max = 0;
                            if(split.length<4){
                                max = 3;
                            }else{
                                max = 2;
                            }
                            for (int j = 0; j < collect.size() && j < max; j++) {
                                String ss = collect.get(j);
                                String s1 = ss.replaceAll("[^a-zA-Z ]", "");
                                String s2 = s1.toLowerCase().trim();
                                String[] s3 = s2.split(" ");
                                for (String sss : s3) {
                                    String s4 = sss;
                                    if(i!=0){
                                        s4 = toBig(sss);
                                    }
                                    strings.add(s4);
                                }
                            }
                            lists.add(strings);
                        }

                        List<String> di = new ArrayList<>(lists.get(0));
                        for (int i = 1; i < lists.size(); i++) {
                            di = dicarer(di,lists.get(i));
                        }
                        after.setText(StringUtils.join(di," | "));
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

    private List<String> dicarer(List<String> s1,List<String> s2){
        List<String> list = new ArrayList<>();
        for (String s : s1) {
            for (String ss : s2) {
                list.add(s+ss);
            }
        }
        return list;
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
