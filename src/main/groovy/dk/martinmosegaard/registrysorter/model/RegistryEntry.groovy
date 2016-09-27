package dk.martinmosegaard.registrysorter.model

import javax.swing.text.Element

/**
 * A registry entry is a line which can have a parent entry.
 */
class RegistryEntry {

  RegistryEntry parent
  int indent
  String line
  Element element
  List<RegistryEntry> children = []

}
