# Test with Pages on Mac

## Inserting text

Inserting leaf elements only does not work with newlines. Take a look at this:
http://www.comp.nus.edu.sg/~cs3283/ftp/Java/swingConnect/text/element_buffer/element_buffer.html

## Reading a document

The root element contains `BranchElement`s, which contain `LeafElement`s.
There is one branch element per line.
If a line contains formatting, there may be several leaf elements, one per different
formatted block.

```sh
/*
Structure:
DefaultStyledDocument
+- DefaultStyledDocument$DocumentSectionElement
  +- AbstractDocument$BranchElement
    +- AbstractDocument$LeafElement, one per different formatting
 */
void printText() {
  Document doc = editor.document
  println('document class: ' + doc.class) // DefaultStyledDocument
  Element root = doc.defaultRootElement
  println('root element count: ' + root.elementCount)
  println('root element class: ' + root.class) // DefaultStyledDocument$SectionElement
  for (int i = 0; i < root.elementCount; ++i) {
    Element element = root.getElement(i)
    println('[idx=' + i + '] element class: ' + element.class) // AbstractDocument$BranchElement
    int start = element.startOffset
    int end = element.endOffset
    String line = doc.getText(start, end - start)
    println('[idx=' + i + '] [' + Reader.countLeadingSpaces(line) + '] [' + line + ']')
    // Attributes on a BranchElement do not contain bold and italic formatting
    println("[idx=${i}] element child count: ${element.elementCount}")
    for (int j = 0; j < element.elementCount; ++j) {
      Element child = element.getElement(j)
      String childLine = doc.getText(child.startOffset,
        child.endOffset - child.startOffset)
      println("[idx=${i},${j}] [${child.class}] [${childLine}]") // AbstractDocument$LeafElement
      // Attributes on a LeafElement contain formatting, if any
      println("leaf text=branch text? ${line == childLine}")
      AttributeSet attributes = child.attributes
      for (Object key : attributes.attributeNames) {
        println("  Attribute: ${key}=${attributes.getAttribute(key)}")
      }
    }
  }
}
```

## Updating text

The `DefaultStyledDocument` has an `insertString` method which takes an element, or
something with attributes. But the example below loses formatting.

```sh
void setNewDocument() {
  Document doc2 = new DefaultStyledDocument()

  Document doc = editor.document
  Element root = doc.defaultRootElement
  for (int i = 0; i < root.elementCount; ++i) {
    Element element = root.getElement(i)
    int start = element.startOffset
    int end = element.endOffset
    String line = doc.getText(start, end - start)

    if (line.trim()) {
      doc2.insertString(0, line, element)
    }
  }
  editor.document = doc2
}
```
