package uk.college.ui.tasks

import grails.validation.Validateable
import groovy.util.logging.Slf4j
import uk.college.workflow.DocumentDetails
import uk.college.workflow.Workflow
import uk.college.workflow.loadproposal.LoadDocumentEvent

@Validateable
@Slf4j
class ReviewModuleProposal {

  Workflow workflowService

  String documentId

  DocumentDetails call() {
    if (documentId) {
      return workflowService.documentLoaded(new LoadDocumentEvent(documentId: documentId)).details
    } else {
      return null
    }
  }
}
