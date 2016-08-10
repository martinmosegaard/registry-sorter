package dk.martinmosegaard.registrysorter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.rtf.RTFEditorKit;

/**
 * Status: Seems to handle copy-paste text from LibreOffice Writer
 * in bold and italic.
 * Try to incorporate this into AlphabetizeView.
 * Be aware of visual differences, perhaps font size.
 * TODO:
 * - figure out how to work with text of JEditorPane or RTFEditorKit
 * JTextPane.getStyledDocument -> StyledDocument
 * JTextPane.getStyledEditorKit -> StyledEditorKit
 * JEditorPane.getEditorKit
 */
public class RtfView extends JFrame {

  public RtfView() {
    setTitle("RTF Text Application");
    setSize(800, 600);
    setBackground(Color.gray);
    getContentPane().setLayout(new BorderLayout());

    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BorderLayout());
    getContentPane().add(topPanel, BorderLayout.CENTER);

    RTFEditorKit rtf = new RTFEditorKit();
    JEditorPane editor = new JEditorPane();
    editor.setEditorKit(rtf);
    editor.setBackground(Color.white);

    JScrollPane scroller = new JScrollPane();
    scroller.getViewport().add(editor);
    topPanel.add(scroller, BorderLayout.CENTER);
  }

 }
