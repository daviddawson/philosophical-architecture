package uk.college.workflow.inbox

import org.springframework.stereotype.Component
import uk.college.workflow.ProposedDocument
import uk.college.workflow.DocumentDetails
import uk.college.workflow.BaseWorkflowEvent
import uk.college.workflow.BaseWorkflowResult

@Component
class WipLoadInbox {

  LoadInboxResult call(LoadInboxEvent event) {
    try {
      def results = ProposedDocument.list()
      return new LoadInboxResult(success: true, event:event, details: results*.toDetails())
    } catch (Exception ex) {
      return new LoadInboxResult(success: false, error:ex, event:event)
    }
  }
}

class LoadInboxEvent extends BaseWorkflowEvent { }

class LoadInboxResult extends BaseWorkflowResult {
  LoadInboxEvent event

  List<DocumentDetails> details
}
