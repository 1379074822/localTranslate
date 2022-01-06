import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import java.util.Timer;
import java.util.TimerTask;

public class DialogTranslate extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        TranslateDialog formTestDialog = new TranslateDialog(e.getProject());
        formTestDialog.setResizable(true);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                formTestDialog.input.requestFocus();
            }
        },100);
        formTestDialog.show();
    }
}
