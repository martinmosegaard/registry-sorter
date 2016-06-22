package dk.martinmosegaard.registrysorter.controller

import dk.martinmosegaard.registrysorter.model.RegistryEntry

class Alphabetizer {

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
