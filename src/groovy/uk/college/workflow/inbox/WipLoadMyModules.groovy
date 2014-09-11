package uk.college.workflow.inbox

import org.springframework.stereotype.Component
import uk.college.workflow.ProposalStatus
import uk.college.workflow.ProposedDocument
import uk.college.workflow.DocumentDetails
import uk.college.workflow.BaseWorkflowEvent
import uk.college.workflow.BaseWorkflowResult

@Component
class WipLoadMyModules {

  LoadMyModulesResult call(LoadMyModulesEvent event) {
    try {
      def results = ProposedDocument.findAllByProposingUserAndStatusInList(event.userDetails.username, [
              ProposalStatus.PENDING,
              ProposalStatus.COMMENT_REQUIRED,
              ProposalStatus.SUBMITTED,
      ])
      return new LoadMyModulesResult(success: true, event:event, details: results*.toDetails())
    } catch (Exception ex) {
      return new LoadMyModulesResult(success: false, error:ex, event:event)
    }
  }
}

class LoadMyModulesEvent extends BaseWorkflowEvent {}

class LoadMyModulesResult extends BaseWorkflowResult {
  boolean success

  LoadMyModulesEvent event

  List<DocumentDetails> details = []
}
