package dk.martinmosegaard.registrysorter;

import java.util.Collections;
import java.util.Comparator;

import dk.martinmosegaard.registrysorter.model.RegistryEntry;

class Sorter implements Comparator<RegistryEntry> {

  void sort(RegistryEntry entry) {
    for (RegistryEntry child : entry.getChildren()) {
      sort(child);
    }
    Collections.sort(entry.getChildren(), this);
  }

  @Override
  public int compare(RegistryEntry e1, RegistryEntry e2) {
    return e1.getLine().toLowerCase().compareTo(e2.getLine().toLowerCase());
  }

}
