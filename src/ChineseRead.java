import com.intellij.idea.IdeaLogger;
import com.intellij.notification.EventLog;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class ChineseRead implements StartupActivity {
    static Map<String,String> map = new  HashMap<>();

    public static  String get(String key){
        return map.getOrDefault(key,key);

    }


    @Override
    public void runActivity(@NotNull Project project) {
        new Thread(()->{

            Logger instance = IdeaLogger.getInstance(ChineseRead.class);
            String file = "C:\\chinese.output";
            BufferedReader gbk = null;
            try {
                gbk = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                String s ;
                while((s =gbk.readLine())!=null){
                    map.put(s.split("=")[0],s.split("=")[1]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    gbk.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
