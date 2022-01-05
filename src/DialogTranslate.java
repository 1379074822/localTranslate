import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

public class DialogTranslate extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        TranslateDialog formTestDialog = new TranslateDialog(e.getProject());
        formTestDialog.setResizable(true); //是否允许用户通过拖拽的方式扩大或缩小你的表单框，我这里定义为true，表示允许
        formTestDialog.show();
    }
}
