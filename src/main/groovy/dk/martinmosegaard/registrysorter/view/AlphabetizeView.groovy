package dk.martinmosegaard.registrysorter.view

import java.awt.BorderLayout
import java.awt.Color
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

import javax.swing.JButton
import javax.swing.JEditorPane
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.WindowConstants
import javax.swing.text.AttributeSet
import javax.swing.text.DefaultStyledDocument
import javax.swing.text.Document
import javax.swing.text.Element
import javax.swing.text.rtf.RTFEditorKit

import dk.martinmosegaard.registrysorter.controller.Alphabetizer
import dk.martinmosegaard.registrysorter.controller.Reader

/**
 * Main application window.
 */
class AlphabetizeView extends JFrame {

  private static final long serialVersionUID = 1

  private final JButton alphabetizeButton
  private final JEditorPane editor
  def alphabetizer = new Alphabetizer()

  AlphabetizeView(String title) {
    setTitle(title)
    setSize(800, 600)
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    setBackground(Color.gray)
    contentPane.setLayout(new BorderLayout())

    alphabetizeButton = new JButton('Alfabetiser')
    contentPane.add(alphabetizeButton, BorderLayout.NORTH)

    JPanel topPanel = new JPanel()
    topPanel.layout = new BorderLayout()
    contentPane.add(topPanel, BorderLayout.CENTER)

    RTFEditorKit rtf = new RTFEditorKit()
    editor = new JEditorPane()
    editor.editorKit = rtf
    editor.background = Color.white

    JScrollPane scroller = new JScrollPane()
    scroller.viewport.add(editor)
    topPanel.add(scroller, BorderLayout.CENTER)

    setListener()
  }

  void setListener() {
    alphabetizeButton.addActionListener(new ActionListener() {
      @Override
      void actionPerformed(ActionEvent event) {
        printText()
        setNewDocument()
        // TODO: Sort text
//        String text = textArea.text
//        String sortedText = alphabetizer.alphabetize(text)
//        textArea.text = sortedText
      }
    })
  }

  /*
  Structure:
  DefaultStyledDocument
  +- DefaultStyledDocument$DocumentSectionElement
    +- AbstractDocument$BranchElement
      +- AbstractDocument$LeafElement, one per different formatting
   */
  void printText() {
    Document doc = editor.document
    println('document class: ' + doc.class) // DefaultStyledDocument
    Element root = doc.defaultRootElement
    println('root element count: ' + root.elementCount)
    println('root element class: ' + root.class) // DefaultStyledDocument$SectionElement
    for (int i = 0; i < root.elementCount; ++i) {
      Element element = root.getElement(i)
      println('[idx=' + i + '] element class: ' + element.class) // AbstractDocument$BranchElement
      int start = element.startOffset
      int end = element.endOffset
      String line = doc.getText(start, end - start)
      println('[idx=' + i + '] [' + Reader.countLeadingSpaces(line) + '] [' + line + ']')
      // Attributes on a BranchElement do not contain bold and italic formatting
      println("[idx=${i}] element child count: ${element.elementCount}")
      for (int j = 0; j < element.elementCount; ++j) {
        Element child = element.getElement(j)
        String childLine = doc.getText(child.startOffset,
          child.endOffset - child.startOffset)
        println("[idx=${i},${j}] [${child.class}] [${childLine}]") // AbstractDocument$LeafElement
        // Attributes on a LeafElement contain formatting, if any
        println("leaf text=branch text? ${line == childLine}")
        AttributeSet attributes = child.attributes
        for (Object key : attributes.attributeNames) {
          println("  Attribute: ${key}=${attributes.getAttribute(key)}")
        }
      }
    }
  }

  // This method can insert the elements in the opposite order, but loses formatting
  void setNewDocument() {
    Document doc2 = new DefaultStyledDocument()

    Document doc = editor.document
    Element root = doc.defaultRootElement
    for (int i = 0; i < root.elementCount; ++i) {
      Element element = root.getElement(i)
      int start = element.startOffset
      int end = element.endOffset
      String line = doc.getText(start, end - start)

      if (line.trim()) {
        doc2.insertString(0, line, element)
      }
    }
    editor.document = doc2
  }
}
