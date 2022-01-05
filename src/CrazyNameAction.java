import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

public class CrazyNameAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {

        CrazyNameDialog formTestDialog = new CrazyNameDialog(e.getProject());
        formTestDialog.setResizable(true);
        formTestDialog.show();

    }
}
