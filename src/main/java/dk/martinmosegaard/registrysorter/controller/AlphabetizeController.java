package dk.martinmosegaard.registrysorter.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dk.martinmosegaard.registrysorter.model.RegistryEntry;
import dk.martinmosegaard.registrysorter.view.AlphabetizeView;

public class AlphabetizeController implements ActionListener {

  private final AlphabetizeView view;

  public AlphabetizeController(AlphabetizeView view) {
    this.view = view;
    view.setListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    try {
      String text = view.getText();
      Reader reader = new Reader();
      RegistryEntry rootEntry = reader.read(text);
      Sorter sorter = new Sorter();
      sorter.sort(rootEntry);
      Writer writer = new Writer();
      String sortedText = writer.write(rootEntry);
      view.setText(sortedText);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
