package networkrepl.actions

import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent, CommonDataKeys}

class SendCodeToRepl extends AnAction {
  override def actionPerformed(event: AnActionEvent): Unit = {

    val editor = CommonDataKeys.EDITOR.getData(event.getDataContext)
    if (editor == null) return

    val selection = editor.getSelectionModel

    val title = "Send selected code to the Space!"

    def debugPrint(content: String): Unit = {
      import sys.process._
      val url = "localhost:8080/echo"
      Seq("curl", "--silent", "-XPOST", url, "-d", content).!
    }

    if (selection.hasSelection) {
      val content = selection.getSelectedText
      debugPrint(content)
    }
  }
}
