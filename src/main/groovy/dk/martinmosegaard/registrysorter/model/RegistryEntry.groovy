package dk.martinmosegaard.registrysorter.model

class RegistryEntry {

  RegistryEntry parent
  int indent
  String line
  List<RegistryEntry> children = new ArrayList<>()

}
