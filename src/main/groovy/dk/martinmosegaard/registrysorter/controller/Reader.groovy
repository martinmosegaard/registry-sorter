package dk.martinmosegaard.registrysorter.controller

import dk.martinmosegaard.registrysorter.model.RegistryEntry

import javax.swing.text.Document
import javax.swing.text.Element

/**
 * Reads text into a ReaderEntry model.
 */
class Reader {

  /**
   * Read plain text.
   */
  RegistryEntry read(String text) throws IOException {
    int currentIndent = 0
    RegistryEntry root = rootEntry
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
          entry = new RegistryEntry(parent:currentParent, indent:indent, line:line)
        } else if (indent > currentIndent) {
          // New subsection to be added as a child to last entry
          entry = new RegistryEntry(parent:lastEntry, indent:indent, line:line)
          currentIndent = indent
          currentEntries = lastEntry.children
          currentParent = lastEntry
        } else {
          // Back to previous level of indentation
          RegistryEntry parent = currentParent.parent
          while (parent.indent >= indent) {
            parent = parent.parent
          }
          entry = new RegistryEntry(parent:parent, indent:indent, line:line)
          currentIndent = indent
          currentEntries = parent.children
          currentParent = parent
        }
        currentEntries.add(entry)
        lastEntry = entry
      }
    }

    root
  }

  /**
   * Read RTF text.
   */
  RegistryEntry read(Document document) {
    int currentIndent = 0
    RegistryEntry root = rootEntry
    RegistryEntry currentParent = root
    List<RegistryEntry> currentEntries = currentParent.children
    RegistryEntry lastEntry = null

    println '>>> reader'
    document.dump(System.out)
    println '<<< reader'
    Element rootElement = document.defaultRootElement

    for (int i = 0; i < rootElement.elementCount; ++i) {
      Element branchElement = rootElement.getElement(i)
      int start = branchElement.startOffset
      int end = branchElement.endOffset
      String line = document.getText(start, end - start)

      if (!line.trim()) {
        continue
      }

      int indent = countLeadingSpaces(line)
      RegistryEntry entry
      if (indent == currentIndent) {
        // Add to current entries
        entry = new RegistryEntry(parent:currentParent, indent:indent, line:line,
            element:branchElement)
      } else if (indent > currentIndent) {
        // New subsection to be added as a child to last entry
        entry = new RegistryEntry(parent:lastEntry, indent:indent, line:line,
            element:branchElement)
        currentIndent = indent
        currentEntries = lastEntry.children
        currentParent = lastEntry
      } else {
        // Back to previous level of indentation
        RegistryEntry parent = currentParent.parent
        while (parent.indent >= indent) {
          parent = parent.parent
        }
        entry = new RegistryEntry(parent:parent, indent:indent, line:line,
            element:branchElement)
        currentIndent = indent
        currentEntries = parent.children
        currentParent = parent
      }
      currentEntries.add(entry)
      lastEntry = entry
    }

    root
  }

  static int countLeadingSpaces(String line) {
    for (int i = 0; i < line.length(); ++i) {
      if (line.charAt(i) != ' ' && line.charAt(i) != '\t') {
        return i
      }
    }
    // A completely blank line
    0
  }

  static RegistryEntry getRootEntry() {
    new RegistryEntry(parent:null, indent:-1, line:'')
  }

}
