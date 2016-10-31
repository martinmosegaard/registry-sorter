package dk.martinmosegaard.registrysorter.controller

import dk.martinmosegaard.registrysorter.model.RegistryEntry

import javax.swing.JLabel
import javax.swing.event.DocumentEvent.EventType
import javax.swing.text.AttributeSet
import javax.swing.text.AbstractDocument.DefaultDocumentEvent
import javax.swing.text.AbstractDocument.LeafElement
import javax.swing.text.BadLocationException
import javax.swing.text.DefaultStyledDocument
import javax.swing.text.DefaultStyledDocument.ElementBuffer
import javax.swing.text.DefaultStyledDocument.ElementSpec
import javax.swing.text.Document
import javax.swing.text.Element
import javax.swing.text.SimpleAttributeSet
import javax.swing.text.StyleConstants

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
    write(rootEntry, document, originalDocument)
    println '>>> writer'
    document.dump(System.out)
    println '<<< writer'
    document
  }

  void write(RegistryEntry entry, Document document, Document originalDocument) {
    Element rootElement = document.defaultRootElement
    document.writeLock()
    LeafElement black10Element = (LeafElement) document.createLeafElement(rootElement, new SimpleAttributeSet(), 0, 1)

    DefaultDocumentEvent documentEvent = new DefaultDocumentEvent(document, 0, 1, EventType.INSERT)
    try {
      document.content.insertString(0, "hej")
    } catch (BadLocationException e) {
      e.printStackTrace()
    }
    document.insertUpdate(documentEvent, black10Element)
    documentEvent.end()
    document.fireInsertUpdate(documentEvent)

    document.writeUnlock()
  }

  void writeThatWorks(RegistryEntry entry, Document document, Document originalDocument) {
    Element rootElement = document.defaultRootElement
    document.writeLock()
    LeafElement black10Element = (LeafElement) document.createLeafElement(rootElement, new SimpleAttributeSet(), 0, 1)
    JLabel black10 = new JLabel('10 ...')
    StyleConstants.setComponent(black10Element, black10)

    DefaultDocumentEvent documentEvent = new DefaultDocumentEvent(document, 0, 1, EventType.INSERT)
    try {
      document.content.insertString(0, " ")
    } catch (BadLocationException e) {
      e.printStackTrace()
    }
    document.insertUpdate(documentEvent, black10Element)
    documentEvent.end()
    document.fireInsertUpdate(documentEvent)

    document.writeUnlock()
  }

  void write2(RegistryEntry entry, Document document, Document originalDocument) {
//    Element paragraphElement = document.getParagraphElement(0)
  //  println "paragraphElement: ${paragraphElement}"
    Element rootElement = document.defaultRootElement
    ElementBuffer buffer = new ElementBuffer(document, rootElement)
    def leafSpecs = []
    leafSpecs.add(new ElementSpec(null, ElementSpec.ContentType,
        "hej".toCharArray(), 0, 3))
    document.writeLock()
    buffer.insert(0, 3, leafSpecs as ElementSpec[],
        new DefaultDocumentEvent(document, 0, 3, EventType.INSERT))
    document.writeUnlock()
  }

  void write0(RegistryEntry entry, Document document, Document originalDocument) {
    entry.children.reverseEach { RegistryEntry child ->
      write(child, document, originalDocument)
    }
    Element element = entry.element
    if (!element) {
      return
    }

    document.writeLock()

    def leafSpecs = []
    int length = 0
    for (int i = 0; i < element.elementCount; ++i) {
      Element leafElement = element.getElement(i)
      AttributeSet attributes = leafElement.attributes
      int start = leafElement.startOffset
      int end = leafElement.endOffset
      String line = originalDocument.getText(start, end - start)
      ElementSpec spec = new ElementSpec(attributes, ElementSpec.ContentType,
          line.toCharArray(), 0, line.length())
      length += line.length()
      leafSpecs.add(spec)
    }
    Element paragraphElement = document.getParagraphElement(0)
    ElementBuffer buffer = new ElementBuffer(document, paragraphElement)
    buffer.insert(0, length, leafSpecs as ElementSpec[],
        new DefaultDocumentEvent(document, 0, length, EventType.INSERT))

    document.writeUnlock()
//    document.insert(0, leafSpecs as ElementSpec[])
  }

}
