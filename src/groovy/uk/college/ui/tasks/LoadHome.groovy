package uk.college.ui.tasks

import grails.validation.Validateable
import groovy.util.logging.Slf4j
import uk.college.workflow.Workflow
import uk.college.workflow.inbox.LoadInboxEvent
import uk.college.user.UserInfo

@Validateable
@Slf4j
class LoadHome {

  Workflow workflowService
  UserInfo userInfoService

  def call() {

    def actionInbox = actionInbox()

    [actionInbox:actionInbox]
  }

  def actionInbox() {
    workflowService.actionInboxLoaded(
            new LoadInboxEvent(userDetails: userInfoService.currentUserDetails)).details.collect {
      [       proposalId:it.documentId,
              title: it.moduleTitle,
              proposer: "${it.proposingUser?.forename} ${it.proposingUser?.surname}",
              //TODO, derive this or be told.....?
              yourAction: "Review New Submission",
              action: "propose"]
    }
  }
}
