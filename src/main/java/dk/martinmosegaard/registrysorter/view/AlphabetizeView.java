package dk.martinmosegaard.registrysorter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class AlphabetizeView extends JFrame {

  private static final long serialVersionUID = 1;

  private JButton alphabetizeButton;
  private JTextArea textArea;

  public AlphabetizeView() {
    setTitle("Registry Sorter");
    setSize(800, 600);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setBackground(Color.gray);
    getContentPane().setLayout(new BorderLayout());

    alphabetizeButton = new JButton("Alfabetiser");
    getContentPane().add(alphabetizeButton, BorderLayout.NORTH);

    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BorderLayout());
    getContentPane().add(topPanel, BorderLayout.CENTER);

    textArea = new JTextArea();
    JScrollPane scroller = new JScrollPane();
    scroller.getViewport().add(textArea);
    topPanel.add(scroller, BorderLayout.CENTER);
  }

  public void setListener(ActionListener listener) {
    alphabetizeButton.addActionListener(listener);
  }

  public String getText() {
    return textArea.getText();
  }

  public void setText(String text) {
    textArea.setText(text);
  }

}
