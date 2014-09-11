package uk.college.workflow.submit

import uk.college.workflow.BaseWorkflowEvent
import uk.college.workflow.BaseWorkflowResult
import uk.college.workflow.DocumentDetails

class SubmitDocumentEvent extends BaseWorkflowEvent {

  String documentId

}

class SubmitDocumentResult extends BaseWorkflowResult {
  DocumentDetails details

}