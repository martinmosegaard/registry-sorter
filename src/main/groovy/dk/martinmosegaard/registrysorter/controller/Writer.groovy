package dk.martinmosegaard.registrysorter.controller

import dk.martinmosegaard.registrysorter.model.RegistryEntry

import javax.swing.text.AttributeSet
import javax.swing.text.DefaultStyledDocument
import javax.swing.text.DefaultStyledDocument.ElementSpec
import javax.swing.text.Document
import javax.swing.text.Element

/**
 * Writes a registry entry model to a string.
 */
class Writer {

  /**
   * Write plain text.
   */
  String write(RegistryEntry rootEntry) {
    def buffer = new StringBuffer()
    write(buffer, rootEntry)
    buffer.toString()
  }

  void write(StringBuffer buffer, RegistryEntry entry) {
    if (entry.line != '') {
      buffer.append(entry.line)
      buffer.append(System.lineSeparator())
    }
    entry.children.each { RegistryEntry child ->
      write(buffer, child)
    }
  }

  /**
   * Write RTF text.
   */
  Document write(RegistryEntry rootEntry, Document originalDocument) {
    Document document = new DefaultStyledDocument()
    write(rootEntry, document, originalDocument)
    document
  }

  void write(RegistryEntry entry, Document document, Document originalDocument) {
    Element element = entry.element
    if (element) {
      def leafSpecs = []
      for (int i = 0; i < element.elementCount; ++i) {
        Element leafElement = element.getElement(i)
        AttributeSet attributes = leafElement.attributes
        int start = leafElement.startOffset
        int end = leafElement.endOffset
        String line = originalDocument.getText(start, end - start).trim()
        ElementSpec spec = new ElementSpec(attributes, ElementSpec.ContentType,
            line.toCharArray(), 0, line.length())
        leafSpecs.add(spec)
      }
      document.insert(0, leafSpecs as ElementSpec[])
    }
    entry.children.each { RegistryEntry child ->
      write(child, document, originalDocument)
    }
  }

}
