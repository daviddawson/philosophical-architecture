package uk.college.workflow

import grails.validation.ValidationErrors
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

class StubWorkflow implements Workflow {

  List received = []

  @Override
  SaveModuleProposalResult documentSaved(SaveModuleProposalEvent saveModuleProposalEvent) {
    received << saveModuleProposalEvent
    return new SaveModuleProposalResult(success: true, details: saveModuleProposalEvent.details, event: saveModuleProposalEvent)
  }

  @Override
  SubmitDocumentResult documentSubmitted(SubmitDocumentEvent moduleProposalEvent) {
    received << moduleProposalEvent
    if (moduleProposalEvent.documentId == "PROP-FAILURE") {
      def ev = new SubmitDocumentResult(success:false,
          details: defaultDetails(moduleProposalEvent.documentId))
      ev.errors = new ValidationErrors(ev.details)
      ev.errors.rejectValue("status", "123")
      return ev
    }

    return new SubmitDocumentResult(success:true, details: defaultDetails(moduleProposalEvent.documentId))
  }

  @Override
  LoadDocumentResult documentLoaded(LoadDocumentEvent loadProposalEvent) {
    received << loadProposalEvent
    if (loadProposalEvent.documentId == "PROP-FAILURE") {
      def ev = new LoadDocumentResult(success:false,
          details: failureDetails(loadProposalEvent.documentId))
      return ev
    }

    if (loadProposalEvent.documentId == "PROP-SUBMITTED-123") {
      return new LoadDocumentResult(success: true,
              details: submittedDetails(loadProposalEvent.documentId))
    }
    return new LoadDocumentResult(success: true,
        details: defaultDetails(loadProposalEvent.documentId))
  }

  @Override
  LoadInboxResult actionInboxLoaded(LoadInboxEvent loadInboxEvent) {
    received << loadInboxEvent
    return new LoadInboxResult(details: [defaultDetails("12345")], success:true)
  }

  @Override
  DeleteProposalResult documentDeleted(DeleteProposalEvent event) {
    received << event
    new DeleteProposalResult(success:true, event: event)
  }

  DocumentDetails submittedDetails(proposalId="PROP1") {
    def details = defaultDetails(proposalId)
    details.status = ProposalStatus.SUBMITTED
    details
  }

  DocumentDetails failureDetails(proposalId="PROP-FAILURE") {
    def ret = defaultDetails(proposalId)

    ret.creditValue = "FAILED"
    ret.errors = new ValidationErrors(ret)
    ret.errors.rejectValue("creditValue", "123", "FAILURE OF WILL")

    ret
  }

  DocumentDetails defaultDetails(proposalId="PROP1") {
    new DocumentDetails(
            status: ProposalStatus.PENDING,
            documentId: proposalId,
            moduleTitle :"Awesome Module",
        text: """
          The world is a wonderful place, with amazing things in it
""",
            proposingUser:  defaultUser(),
    )
  }

  def defaultUser() {
    new UserDetails(forename:"Derek", surname: "Williamson")
  }

  def clear() {
    received.clear()
  }

  def getStream() {
    received.collect {
      it.properties
    }
  }
}
