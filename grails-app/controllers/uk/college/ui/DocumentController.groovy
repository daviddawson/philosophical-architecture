package uk.college.ui

import uk.college.ui.tasks.LoadDocument
import uk.college.ui.tasks.SaveDocument
import uk.college.ui.tasks.SubmitDocument
import uk.college.workflow.ProposalStatus

class DocumentController {

  def propose(LoadDocument loadModuleProposal) {
    def proposal = loadModuleProposal()
    if (proposal.status == ProposalStatus.DELETED) {
      response.sendError(404, "No Document with ID ${loadModuleProposal.documentId} was found")
      return
    }
    return [proposal: proposal]
  }

  def saveProposal(SaveDocument moduleProposal) {
    if (moduleProposal.save()) {
      flash.message = "Document Saved, but not submitted"
      redirect controller:"home"
    } else {
      redirect action:"propose"
    }
  }

  def submitProposal(SubmitDocument moduleProposal) {
    if (moduleProposal.submit()) {
      flash.message = "Document Submitted"
      redirect controller:"home"
    } else {
      flash.message = "Document failed validation, correct the errors before submitting."
      redirect action:"propose", params: [documentId:moduleProposal.documentId]
    }
  }

}


