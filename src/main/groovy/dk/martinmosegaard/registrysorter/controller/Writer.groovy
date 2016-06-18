package dk.martinmosegaard.registrysorter.controller;

import dk.martinmosegaard.registrysorter.model.RegistryEntry;

class Writer {

  String write(RegistryEntry rootEntry) {
    StringBuffer buffer = new StringBuffer();
    write(buffer, rootEntry);
    return buffer.toString();
  }

  void write(StringBuffer buffer, RegistryEntry entry) {
    if (entry.getLine() != "") {
      buffer.append(entry.getLine());
      buffer.append(System.lineSeparator());
    }
    for (RegistryEntry child : entry.getChildren()) {
      write(buffer, child);
    }
  }

}
