package dk.martinmosegaard.registrysorter.view

import java.awt.BorderLayout
import java.awt.Color
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextArea
import javax.swing.WindowConstants

import dk.martinmosegaard.registrysorter.controller.Alphabetizer

class AlphabetizeView extends JFrame {

  private static final long serialVersionUID = 1

  private JButton alphabetizeButton
  private JTextArea textArea
  def alphabetizer = new Alphabetizer()

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

    setListener()
  }

  void setListener() {
    alphabetizeButton.addActionListener(new ActionListener() {
      @Override
      void actionPerformed(ActionEvent event) {
        try {
          String text = textArea.text
          String sortedText = alphabetizer.alphabetize(text)
          textArea.text = sortedText
        } catch (Exception e) {
          e.printStackTrace()
        }
      }
    })
  }

}
