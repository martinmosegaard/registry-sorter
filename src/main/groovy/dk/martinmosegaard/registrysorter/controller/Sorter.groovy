package dk.martinmosegaard.registrysorter.controller

import dk.martinmosegaard.registrysorter.model.RegistryEntry

class Sorter implements Comparator<RegistryEntry> {

  void sort(RegistryEntry entry) {
    entry.children.each { RegistryEntry child ->
      sort(child)
    }
    Collections.sort(entry.children, this)
  }

  @Override
  int compare(RegistryEntry e1, RegistryEntry e2) {
    return e1.line.toLowerCase().compareTo(e2.line.toLowerCase())
  }

}
