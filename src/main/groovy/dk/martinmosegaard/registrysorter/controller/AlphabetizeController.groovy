package dk.martinmosegaard.registrysorter.controller

import java.awt.event.ActionEvent
import java.awt.event.ActionListener

import dk.martinmosegaard.registrysorter.model.RegistryEntry
import dk.martinmosegaard.registrysorter.view.AlphabetizeView

class AlphabetizeController implements ActionListener {

  private final AlphabetizeView view

  AlphabetizeController(AlphabetizeView view) {
    this.view = view
    view.listener = this
  }

  @Override
  void actionPerformed(ActionEvent event) {
    try {
      String text = view.text
      String sortedText = alphabetize(text)
      view.text = sortedText
    } catch (Exception e) {
      e.printStackTrace()
    }
  }

  String alphabetize(String text) {
    def reader = new Reader()
    RegistryEntry rootEntry = reader.read(text)
    def sorter = new Sorter()
    sorter.sort(rootEntry)
    def writer = new Writer()
    String sortedText = writer.write(rootEntry)
    return sortedText
  }

}
