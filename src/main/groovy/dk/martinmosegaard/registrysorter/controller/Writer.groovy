package dk.martinmosegaard.registrysorter.controller

import dk.martinmosegaard.registrysorter.model.RegistryEntry

class Writer {

  String write(RegistryEntry rootEntry) {
    def buffer = new StringBuffer()
    write(buffer, rootEntry)
    buffer.toString()
  }

  void write(StringBuffer buffer, RegistryEntry entry) {
    if (entry.getLine() != "") {
      buffer.append(entry.getLine())
      buffer.append(System.lineSeparator())
    }
    entry.children.each { RegistryEntry child ->
      write(buffer, child)
    }
  }

}
