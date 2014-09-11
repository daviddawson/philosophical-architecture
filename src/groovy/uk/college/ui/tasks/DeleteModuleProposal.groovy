package uk.college.ui.tasks

import grails.validation.Validateable
import uk.college.user.UserInfo
import uk.college.workflow.Workflow
import uk.college.workflow.delete.DeleteProposalEvent

@Validateable
class DeleteModuleProposal {

  Workflow workflowService
  UserInfo userInfoService

  String proposalId

  def delete() {

    workflowService.documentDeleted(
            new DeleteProposalEvent(
                    documentId: proposalId,
                    userDetails: userInfoService.currentUserDetails))
  }
}
