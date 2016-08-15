package dk.martinmosegaard.registrysorter.controller

import dk.martinmosegaard.registrysorter.model.RegistryEntry

/**
 * Sorts registry entries by comparing the contents of lines.
 */
class Sorter implements Comparator<RegistryEntry> {

  void sort(RegistryEntry entry) {
    entry.children.each { RegistryEntry child ->
      sort(child)
    }
    Collections.sort(entry.children, this)
  }

  @Override
  int compare(RegistryEntry e1, RegistryEntry e2) {
    def firstLine = QuoteHandler.removeQuotes(e1.line)
    def secondLine = QuoteHandler.removeQuotes(e2.line)

    firstLine.toLowerCase().compareTo(secondLine.toLowerCase())
  }

}
