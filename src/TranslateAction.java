import com.intellij.idea.IdeaLogger;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.*;
import org.apache.commons.lang.StringUtils;
import org.codehaus.plexus.util.SelectorUtils;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TranslateAction extends AnAction {
    Logger ins = IdeaLogger.getInstance(TranslateAction.class);
    @Override
    public void actionPerformed(AnActionEvent e) {
        try {
            JBPopupFactory instance = JBPopupFactory.getInstance();
//        // 创建需要执行的任务
//        Runnable runnable = ()-> Messages.showMessageDialog("aaa", "hello", Messages.getInformationIcon());

//        ListPopup popup = instance.createConfirmation("123", runnable, 2);
//        popup.showInBestPositionFor(e.getDataContext());
            String selectedText = e.getRequiredData(CommonDataKeys.EDITOR).getSelectionModel().getSelectedText();

            String msg = "";
            if(StringUtils.isEmpty(selectedText)){
                return;
            }
            String key = selectedText.toLowerCase().trim();
            msg = getString(msg, key);
            JBPopup message = instance.createMessage(msg);
            message.showInBestPositionFor(e.getDataContext());
        } catch (Exception e1) {
            e1.printStackTrace();
        }


    }

    static String getString(String msg, String key) {
        if( ChineseRead.map.containsKey(key)){
            msg = ChineseRead.map.get(key);
        }
        if( EnglishRead.map.containsKey(key)){
            msg = EnglishRead.map.get(key);
        }
        return msg;
    }
}
