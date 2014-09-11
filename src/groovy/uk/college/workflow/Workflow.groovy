package uk.college.workflow

import groovy.transform.TypeChecked
import org.springframework.validation.Errors
import uk.college.workflow.delete.DeleteProposalEvent
import uk.college.workflow.delete.DeleteProposalResult
import uk.college.workflow.inbox.LoadInboxEvent
import uk.college.workflow.inbox.LoadInboxResult
import uk.college.workflow.loadproposal.LoadDocumentEvent
import uk.college.workflow.loadproposal.LoadDocumentResult
import uk.college.workflow.save.SaveModuleProposalEvent
import uk.college.workflow.save.SaveModuleProposalResult
import uk.college.workflow.submit.SubmitDocumentEvent
import uk.college.workflow.submit.SubmitDocumentResult


@TypeChecked
public interface Workflow {
  SaveModuleProposalResult documentSaved(SaveModuleProposalEvent saveModuleProposalEvent)
  SubmitDocumentResult documentSubmitted(SubmitDocumentEvent moduleProposalEvent)
  LoadDocumentResult documentLoaded(LoadDocumentEvent loadProposalEvent)
  LoadInboxResult actionInboxLoaded(LoadInboxEvent loadInboxEvent)
  DeleteProposalResult documentDeleted(DeleteProposalEvent event)
}








abstract class BaseWorkflowEvent {
  UserDetails userDetails
}

abstract class BaseWorkflowResult {
  boolean success
  Exception error
  Errors errors

  String toString() {
    if (success) {
      return "${this.getClass().name} : Success"
    }
    if (error) {
      return "${this.getClass().name} : Failure [${error.message}]"
    }
    return "${this.getClass().name} : Failure [${errors?.allErrors}]"
  }
}
