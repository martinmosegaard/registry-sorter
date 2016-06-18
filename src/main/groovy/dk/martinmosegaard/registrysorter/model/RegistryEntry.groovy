package dk.martinmosegaard.registrysorter.model

class RegistryEntry {

  private final RegistryEntry parent
  private final int indent
  private final String line
  private final List<RegistryEntry> children = new ArrayList<>()

  RegistryEntry(RegistryEntry parent, int indent, String line) {
    this.parent = parent
    this.indent = indent
    this.line = line
  }

  RegistryEntry getParent() {
    return parent
  }

  int getIndent() {
    return indent
  }

  String getLine() {
    return line
  }

  List<RegistryEntry> getChildren() {
    return children
  }

}
