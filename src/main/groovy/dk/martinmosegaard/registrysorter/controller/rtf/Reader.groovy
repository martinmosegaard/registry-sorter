package dk.martinmosegaard.registrysorter.controller.rtf

import dk.martinmosegaard.registrysorter.model.rtf.RegistryEntry

import javax.swing.text.Document
import javax.swing.text.Element

/**
 * Reads text into a ReaderEntry model.
 */
class Reader {

  RegistryEntry read(Document document) {
    int currentIndent = 0
    RegistryEntry root = new RegistryEntry(parent:null, indent:-1, line:'')
    RegistryEntry currentParent = root
    List<RegistryEntry> currentEntries = currentParent.children
    RegistryEntry lastEntry = null

    Element rootElement = document.defaultRootElement

    for (int i = 0; i < rootElement.elementCount; ++i) {
      Element branchElement = rootElement.getElement(i)
      int start = branchElement.startOffset
      int end = branchElement.endOffset
      String line = doc.getText(start, end - start).trim()

      if (!line) {
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

}
