package uk.college.moduleui.academicui

import geb.spock.GebReportingSpec
import spock.lang.Shared
import uk.college.moduleui.DomainStubControl
import uk.college.moduleui.academicui.page.DocumentPage

class LoadDocumentSpec extends GebReportingSpec {

  @Shared def domainStub = new DomainStubControl(baseUrl: baseUrl)

  def setupSpec() {
    domainStub.useWorkflowStub()
  }

  def setup() {
    domainStub.clearWorkflowStream()
  }

  def tearDownSpec() {
    domainStub.useWorkflowLive()
  }

  def "User can load an existing document"() {

    when: "User navigates to the document page"

    to DocumentPage, "PROP-1234"

    and: "The event sent into the workflow is retrieved."

    def events = domainStub.eventsWorkflow()

    then: "load event is correctly sent"
    events.size() == 1
    events[0].documentId == "PROP-1234"

    and: "The document data is correctly displayed"
    moduleTitle == "Awesome Module"
  }
}

















