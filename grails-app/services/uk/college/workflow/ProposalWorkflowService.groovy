package uk.college.workflow

import uk.college.workflow.delete.DeleteProposalEvent
import uk.college.workflow.delete.DeleteProposalResult
import uk.college.workflow.inbox.LoadInboxEvent
import uk.college.workflow.inbox.LoadInboxResult
import uk.college.workflow.inbox.WipLoadInbox
import uk.college.workflow.loadproposal.LoadDocumentEvent
import uk.college.workflow.loadproposal.LoadDocumentResult
import uk.college.workflow.loadproposal.WipLoadDocument
import uk.college.workflow.save.WipSaveDocument
import uk.college.workflow.save.SaveModuleProposalEvent
import uk.college.workflow.save.SaveModuleProposalResult
import uk.college.workflow.submit.SubmitDocumentEvent
import uk.college.workflow.submit.SubmitDocumentResult

class ProposalWorkflowService implements Workflow {

  WipSaveDocument wipSaveDocument
  WipLoadDocument wipLoadDocument
  WipLoadInbox wipLoadInbox

  @Override
  DeleteProposalResult documentDeleted(DeleteProposalEvent event) {
    throw new IllegalStateException("Not Implemented")
  }

  @Override
  SaveModuleProposalResult documentSaved(SaveModuleProposalEvent saveModuleProposalEvent) {
    wipSaveDocument(saveModuleProposalEvent)
  }

  @Override
  SubmitDocumentResult documentSubmitted(SubmitDocumentEvent moduleProposalEvent) {
    throw new IllegalStateException("Not Implemented")
  }

  @Override
  LoadDocumentResult documentLoaded(LoadDocumentEvent loadProposalEvent) {
    wipLoadDocument(loadProposalEvent)
  }

  @Override
  LoadInboxResult actionInboxLoaded(LoadInboxEvent loadInboxEvent) {
    wipLoadInbox(loadInboxEvent)
  }
}
