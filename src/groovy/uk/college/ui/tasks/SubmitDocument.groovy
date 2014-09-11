package uk.college.ui.tasks

import grails.validation.Validateable
import org.springframework.validation.Errors
import uk.college.user.UserInfo
import uk.college.workflow.Workflow
import uk.college.workflow.submit.SubmitDocumentEvent
import uk.college.workflow.submit.SubmitDocumentResult

@Validateable
class SubmitDocument {

  Workflow workflowService
  UserInfo userInfoService

  String documentId

  Errors errors

  def submit() {

    def ev = new SubmitDocumentEvent(documentId: documentId,
        userDetails: userInfoService.currentUserDetails)

    SubmitDocumentResult result = workflowService.documentSubmitted(ev)
    errors = result.errors
    result.success
  }
}
