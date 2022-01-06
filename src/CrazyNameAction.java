import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import java.util.Timer;
import java.util.TimerTask;

public class CrazyNameAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {

        CrazyNameDialog crazyNameDialog = new CrazyNameDialog(e.getProject());
        crazyNameDialog.setResizable(true);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                crazyNameDialog.input.requestFocus();
            }
        },100);
        crazyNameDialog.show();

    }
}
