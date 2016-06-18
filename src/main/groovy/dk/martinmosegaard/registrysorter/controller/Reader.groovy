package dk.martinmosegaard.registrysorter.controller

import dk.martinmosegaard.registrysorter.model.RegistryEntry

class Reader {

  RegistryEntry read(String text) throws IOException {
    int currentIndent = 0
    RegistryEntry root = new RegistryEntry(parent: null, indent: -1, line: "")
    RegistryEntry currentParent = root
    List<RegistryEntry> currentEntries = currentParent.children
    RegistryEntry lastEntry = null

    if (text == null) {
      return root
    }

    text.eachLine { line ->
      if (line.trim()) {
        int indent = countLeadingSpaces(line)
        RegistryEntry entry
        if (indent == currentIndent) {
          // Add to current entries
          entry = new RegistryEntry(parent: currentParent, indent: indent, line: line)
        } else if (indent > currentIndent) {
          // New subsection to be added as a child to last entry
          entry = new RegistryEntry(parent: lastEntry, indent: indent, line: line)
          currentIndent = indent
          currentEntries = lastEntry.children
          currentParent = lastEntry
        } else {
          // Back to previous level of indentation
          RegistryEntry parent = currentParent.parent
          while (parent.getIndent() >= indent) {
            parent = parent.parent
          }
          entry = new RegistryEntry(parent: parent, indent: indent, line: line)
          currentIndent = indent
          currentEntries = parent.children
          currentParent = parent
        }
        currentEntries.add(entry)
        lastEntry = entry
      }
    }

    return root
  }

  int countLeadingSpaces(String line) {
    for (int i = 0; i < line.length(); ++i) {
      if (line.charAt(i) != ' ' && line.charAt(i) != '\t') {
        return i
      }
    }
    // A completely blank line
    throw new RuntimeException("Did not expect a completely blank line")
  }

}
