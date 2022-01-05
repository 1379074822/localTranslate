import com.intellij.idea.IdeaLogger;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class EnglishRead  implements StartupActivity {
    static Map<String,String> map = new HashMap<>();

    @Override
    public void runActivity(@NotNull Project project) {
        new Thread(()->{
            Logger instance = IdeaLogger.getInstance(EnglishRead.class);
            String file = "C:\\english.output";
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
