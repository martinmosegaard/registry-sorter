package dk.martinmosegaard.registrysorter.model;

import java.util.ArrayList;
import java.util.List;

public class RegistryEntry {

  private final RegistryEntry parent;
  private final int indent;
  private final String line;
  private final List<RegistryEntry> children = new ArrayList<>();

  public RegistryEntry(RegistryEntry parent, int indent, String line) {
    this.parent = parent;
    this.indent = indent;
    this.line = line;
  }

  public RegistryEntry getParent() {
    return parent;
  }

  public int getIndent() {
    return indent;
  }

  public String getLine() {
    return line;
  }

  public List<RegistryEntry> getChildren() {
    return children;
  }

}
