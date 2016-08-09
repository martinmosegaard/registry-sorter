package dk.martinmosegaard.registrysorter.controller

import dk.martinmosegaard.registrysorter.model.RegistryEntry

/**
 * Writes a registry entry model to a string.
 */
class Writer {

  String write(RegistryEntry rootEntry) {
    def buffer = new StringBuffer()
    write(buffer, rootEntry)
    buffer.toString()
  }

  void write(StringBuffer buffer, RegistryEntry entry) {
    if (entry.line != '') {
      buffer.append(entry.line)
      buffer.append(System.lineSeparator())
    }
    entry.children.each { RegistryEntry child ->
      write(buffer, child)
    }
  }

}
