package uk.college.ui.tasks

import grails.validation.Validateable
import groovy.util.logging.Slf4j
import uk.college.workflow.ProposalStatus
import uk.college.workflow.DocumentDetails
import uk.college.workflow.Workflow
import uk.college.workflow.loadproposal.LoadDocumentEvent

@Validateable
@Slf4j
class LoadDocument {

  Workflow workflowService

  String documentId

  DocumentDetails call() {
    if (documentId) {
      def module = workflowService.documentLoaded(new LoadDocumentEvent(documentId: documentId)).details
      if (!module) {
        return defaultProposalDetails()
      }
      return module
    } else {
      return defaultProposalDetails()
    }
  }

  def defaultProposalDetails() {
    new DocumentDetails(status:ProposalStatus.PENDING)
  }
}
