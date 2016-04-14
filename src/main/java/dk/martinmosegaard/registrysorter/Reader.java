package dk.martinmosegaard.registrysorter;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class Reader {

  RegistryEntry read(String filePath) throws IOException {
    int currentIndent = 0;
    RegistryEntry root = new RegistryEntry(null, -1, "");
    RegistryEntry currentParent = root;
    List<RegistryEntry> currentEntries = currentParent.getChildren();
    RegistryEntry lastEntry = null;

    try (BufferedReader reader = Files.newBufferedReader(
        Paths.get(filePath), StandardCharsets.ISO_8859_1)) {
      String line = reader.readLine();
      while (line != null) {
        if (line.trim().isEmpty()) {
          line = reader.readLine();
          continue;
        }
        int indent = countLeadingSpaces(line);
        RegistryEntry entry;
        if (indent == currentIndent) {
          // Add to current entries
          entry = new RegistryEntry(currentParent, indent, line);
        } else if (indent > currentIndent) {
          // New subsection to be added as a child to last entry
          entry = new RegistryEntry(lastEntry, indent, line);
          currentIndent = indent;
          currentEntries = lastEntry.getChildren();
          currentParent = lastEntry;
        } else {
          // Back to previous level of indentation
          RegistryEntry parent = currentParent.getParent();
          while (parent.getIndent() >= indent) {
            parent = parent.getParent();
          }
          entry = new RegistryEntry(parent, indent, line);
          currentIndent = indent;
          currentEntries = parent.getChildren();
          currentParent = parent;
        }
        currentEntries.add(entry);
        lastEntry = entry;

        line = reader.readLine();
      }
    }

    return root;
  }

  private int countLeadingSpaces(String line) {
    for (int i = 0; i < line.length(); ++i) {
      if (line.charAt(i) != ' ' && line.charAt(i) != '\t') {
        return i;
      }
    }
    // A completely blank line
    throw new RuntimeException("Did not expect a completely blank line");
  }

}
