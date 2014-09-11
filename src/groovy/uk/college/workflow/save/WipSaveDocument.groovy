package uk.college.workflow.save

import groovy.util.logging.Slf4j
import org.springframework.beans.BeanUtils
import org.springframework.stereotype.Component
import uk.college.workflow.ProposalStatus
import uk.college.workflow.ProposedDocument
import uk.college.workflow.DocumentDetails
import uk.college.workflow.BaseWorkflowEvent
import uk.college.workflow.BaseWorkflowResult

@Component
@Slf4j
class WipSaveDocument {

  SaveModuleProposalResult call(SaveModuleProposalEvent saveModuleProposalEvent) {
    try {
      ProposedDocument module = ProposedDocument.get(saveModuleProposalEvent.details.documentId)

      if (module && !savePermitted(module)) {
        return new SaveModuleProposalResult(
                success:false,
                details: module.toDetails(),
                event:saveModuleProposalEvent,
                error: new IllegalStateException("Proposal cannot be overwritten in status ${module.status}"))
      }

      if (!module) {
        module = new ProposedDocument()
      }

      BeanUtils.copyProperties(saveModuleProposalEvent.details, module, "metaClass")
      module.proposingUser = saveModuleProposalEvent.userDetails.username

      def mod = module.save(flush:true, validate:false, failOnError: true)

      return new SaveModuleProposalResult(success:true, details: mod.toDetails(), event:saveModuleProposalEvent)
    } catch (Exception ex) {
      log.error("Failed saving a Proposal ", ex)
      return new SaveModuleProposalResult(success:false, event:saveModuleProposalEvent, error: ex)
    }
  }

  def savePermitted(ProposedDocument module) {
    return module.status in [
            ProposalStatus.PENDING.name(),
            ProposalStatus.COMMENT_REQUIRED.name(),
    ]
  }

}

class SaveModuleProposalEvent extends BaseWorkflowEvent {

  DocumentDetails details

}

class SaveModuleProposalResult extends BaseWorkflowResult {

  DocumentDetails details
  SaveModuleProposalEvent event

}
