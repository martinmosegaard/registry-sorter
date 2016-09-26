# Document class

Code to print text of all lines (paragraph elements, children of the root):

```sh
Document doc = editor.getDocument();
Element root = doc.getDefaultRootElement();
for (int i = 0; i < root.getElementCount(); ++i) {
  Element element = root.getElement(i);
  int start = element.getStartOffset();
  int end = element.getEndOffset();
  System.out.println(doc.getText(start, end - start));
}
```

## Attributes

If a line is not indented with whitespace but using a tabulator in Word, the
element may have an attribute called `FirstLineIndent` (see also `StyleConstants`)
with a non-zero value.

## AlphabetizeView diff

```sh
--- a/src/main/java/dk/martinmosegaard/registrysorter/view/AlphabetizeView.java
+++ b/src/main/java/dk/martinmosegaard/registrysorter/view/AlphabetizeView.java
@@ -3,12 +3,18 @@ package dk.martinmosegaard.registrysorter.view;
 import java.awt.BorderLayout;
 import java.awt.Color;
 import java.awt.event.ActionListener;
+import java.util.Enumeration;

 import javax.swing.JButton;
+import javax.swing.JEditorPane;
 import javax.swing.JFrame;
 import javax.swing.JPanel;
 import javax.swing.JScrollPane;
 import javax.swing.JTextArea;
+import javax.swing.text.AttributeSet;
+import javax.swing.text.Document;
+import javax.swing.text.Element;
+import javax.swing.text.rtf.RTFEditorKit;
 import javax.swing.WindowConstants;

 public class AlphabetizeView extends JFrame {
@@ -17,6 +23,7 @@ public class AlphabetizeView extends JFrame {

   private JButton alphabetizeButton;
   private JTextArea textArea;
+  private JEditorPane editor;

   public AlphabetizeView(String title) {
     setTitle(title);
@@ -32,9 +39,14 @@ public class AlphabetizeView extends JFrame {
     topPanel.setLayout(new BorderLayout());
     getContentPane().add(topPanel, BorderLayout.CENTER);

-    textArea = new JTextArea();
+    RTFEditorKit rtf = new RTFEditorKit();
+    editor = new JEditorPane();
+    editor.setEditorKit(rtf);
+    editor.setBackground(Color.white);
+//    textArea = new JTextArea();
     JScrollPane scroller = new JScrollPane();
-    scroller.getViewport().add(textArea);
+//    scroller.getViewport().add(textArea);
+    scroller.getViewport().add(editor);
     topPanel.add(scroller, BorderLayout.CENTER);
   }
   @@ -42,12 +54,52 @@ public class AlphabetizeView extends JFrame {
        alphabetizeButton.addActionListener(listener);
      }

   -  public String getText() {
   -    return textArea.getText();
   +  public String getText() throws Exception {
   +    Document doc = editor.getDocument();
   +    Element root = doc.getDefaultRootElement();
   +    for (int i = 0; i < root.getElementCount(); ++i) {
   +      Element element = root.getElement(i);
   +      int start = element.getStartOffset();
   +      int end = element.getEndOffset();
   +      System.out.println("-" + doc.getText(start, end - start) + "+");
   +    }
   +
   +    System.out.println(editor.getDocument().getClass());
   +
   +    printElement(root);
   +    for (Element rootElement : editor.getDocument().getRootElements()) {
   +    //  printElement(rootElement);
   +
   +    }
   +
   +    //return textArea.getText();
   +    return editor.getDocument().getText(0, editor.getDocument().getLength());
   +  }
   +
   +  private void printElement(Element element) throws Exception {
   +    System.out.println("Element name: " + element.getName());
   +    int start = element.getStartOffset();
   +    int end = element.getEndOffset();
   +    System.out.println(">" + element.getDocument().getText(start, end - start) + "<");
   +    if ("paragraph".equals(element.getName())) {
   +    System.out.println("Element attributes:");
   +    AttributeSet attributes = element.getAttributes();
   +    for (Enumeration<?> attributeNames = attributes.getAttributeNames(); attributeNames.hasMoreElements(); ) {
   +      Object attrName = attributeNames.nextElement();
   +      System.out.println("Attribute: " + attrName + " = " + attributes.getAttribute(attrName));
   +    }
   +}
   +
   +    for (int i = 0; i < element.getElementCount(); ++i) {
   +      Element child = element.getElement(i);
   +      printElement(child);
   +    }
   +
 }

public void setText(String text) {
-    textArea.setText(text);
+    //textArea.setText(text);
+//    editor.getDocument().setText();
}

}
```
