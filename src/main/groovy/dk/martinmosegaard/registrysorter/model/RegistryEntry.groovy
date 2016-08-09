package dk.martinmosegaard.registrysorter.model

/**
 * A registry entry is a line which can have a parent entry.
 */
class RegistryEntry {

  RegistryEntry parent
  int indent
  String line
  List<RegistryEntry> children = []

}
