package dk.martinmosegaard.registrysorter.view

import java.awt.BorderLayout
import java.awt.Color
import java.awt.event.ActionListener

import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextArea
import javax.swing.WindowConstants

class AlphabetizeView extends JFrame {

  private static final long serialVersionUID = 1

  private JButton alphabetizeButton
  private JTextArea textArea

  AlphabetizeView(String title) {
    setTitle(title)
    setSize(800, 600)
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    setBackground(Color.gray)
    getContentPane().setLayout(new BorderLayout())

    alphabetizeButton = new JButton('Alfabetiser')
    getContentPane().add(alphabetizeButton, BorderLayout.NORTH)

    JPanel topPanel = new JPanel()
    topPanel.layout = new BorderLayout()
    getContentPane().add(topPanel, BorderLayout.CENTER)

    textArea = new JTextArea()
    JScrollPane scroller = new JScrollPane()
    scroller.viewport.add(textArea)
    topPanel.add(scroller, BorderLayout.CENTER)
  }

  void setListener(ActionListener listener) {
    alphabetizeButton.addActionListener(listener)
  }

  String getText() {
    return textArea.text
  }

  void setText(String text) {
    textArea.text = text
  }

}
