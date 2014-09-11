package uk.college.workflow.loadproposal

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import uk.college.user.UserInfo
import uk.college.workflow.NoSuchProposal
import uk.college.workflow.ProposedDocument
import uk.college.workflow.DocumentDetails
import uk.college.workflow.BaseWorkflowEvent
import uk.college.workflow.BaseWorkflowResult

@Component
@Slf4j
class WipLoadDocument {

  @Autowired UserInfo userInfoService

  LoadDocumentResult call(LoadDocumentEvent event) {
    try {
      ProposedDocument module = ProposedDocument.get(event.documentId)

      if (!module) {
        return new LoadDocumentResult(success: false, error:new NoSuchProposal(event.documentId))
      }

      DocumentDetails details = module as DocumentDetails

      return new LoadDocumentResult(success:true, event:event, details: details)
    } catch (Exception ex) {
      log.error("Failed saving a Proposal ", ex)
      return new LoadDocumentResult(success:false, event:event, error: ex)
    }
  }
}

class LoadDocumentEvent extends BaseWorkflowEvent {
  String documentId

}

class LoadDocumentResult extends BaseWorkflowResult {

  DocumentDetails details
  LoadDocumentEvent event

}
