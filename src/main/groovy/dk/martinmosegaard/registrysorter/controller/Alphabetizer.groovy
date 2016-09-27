package dk.martinmosegaard.registrysorter.controller

import dk.martinmosegaard.registrysorter.model.RegistryEntry

import javax.swing.text.Document

/**
 * Used for alphabetizing text.
 */
class Alphabetizer {

  /**
   * Alphabetize plain text.
   */
  String alphabetize(String text) {
    def reader = new Reader()
    RegistryEntry rootEntry = reader.read(text)
    def sorter = new Sorter()
    sorter.sort(rootEntry)
    def writer = new Writer()
    String sortedText = writer.write(rootEntry)
    sortedText
  }

  /**
   * Alphabetize RTF text.
   */
  Document alphabetize(Document document) {
    def reader = new Reader()
    RegistryEntry rootEntry = reader.read(document)
    def sorter = new Sorter()
    sorter.sort(rootEntry)
    def writer = new Writer()
    Document sortedDocument = writer.write(rootEntry, document)
    sortedDocument
  }

}
