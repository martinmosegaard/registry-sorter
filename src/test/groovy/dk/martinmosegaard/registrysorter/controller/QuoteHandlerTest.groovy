package dk.martinmosegaard.registrysorter.controller

import spock.lang.Specification

/**
 * Unit test the QuoteHandler.
 */
class QuoteHandlerTest extends Specification {

  def 'can remove leading single quotes'() {
    setup:
    String line = "'flower'"

    when:
    String trimmedLine = QuoteHandler.removeQuotes(line)

    then:
    trimmedLine == "flower'"
  }

  def 'can remove leading double quotes'() {
    setup:
    String line = '"flower"'

    when:
    String trimmedLine = QuoteHandler.removeQuotes(line)

    then:
    trimmedLine == 'flower"'
  }

  def 'can remove leading mixed quotes'() {
    setup:
    String line = "'\"flower'"

    when:
    String trimmedLine = QuoteHandler.removeQuotes(line)

    then:
    trimmedLine == "flower'"
  }

}
