package uk.college.workflow.delete

import uk.college.workflow.BaseWorkflowEvent
import uk.college.workflow.BaseWorkflowResult

class DeleteProposalEvent extends BaseWorkflowEvent {
  String comment
  String documentId
}

class DeleteProposalResult extends BaseWorkflowResult {
  DeleteProposalEvent event
}
