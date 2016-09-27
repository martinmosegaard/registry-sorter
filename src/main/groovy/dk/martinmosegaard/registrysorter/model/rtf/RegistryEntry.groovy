package dk.martinmosegaard.registrysorter.model.rtf

import javax.swing.text.AbstractDocument.BranchElement

/**
 * A registry entry is a line which can have a parent entry.
 */
class RegistryEntry {

  RegistryEntry parent
  int indent
  String line
  BranchElement element
  List<RegistryEntry> children = []

}
