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
import javax.swing.text.Document
import javax.swing.text.rtf.RTFEditorKit

import dk.martinmosegaard.registrysorter.controller.Alphabetizer

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
        Document document = editor.document
        Document sortedDocument = alphabetizer.alphabetize(document)
        editor.document = sortedDocument
      }
    })
  }

}
