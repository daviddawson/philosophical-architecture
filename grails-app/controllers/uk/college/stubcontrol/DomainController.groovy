package uk.college.stubcontrol

import grails.converters.JSON
import uk.college.documentmanagement.DocumentCommand
import uk.college.documentmanagement.DocumentSystem
import uk.college.workflow.Workflow
import uk.college.user.UserInfo

class DomainController {

  DocumentCommand stubDocumentCommand
  DocumentCommand sitsCommandService
  def documentCommand
  DocumentSystem stubDocumentQuery
  DocumentSystem documentSystemService
  def documentSearchService

  Workflow stubWorkflowService
  Workflow proposalWorkflowService
  def workflowService

  UserInfo stubUserInfoService
  UserInfo liveUserInfoService
  def userInfoService

  def status() {
    def status = [
      workflow: workflowService.workflowTarget.getClass().name.toLowerCase().contains("stub")? "stub": "live",
      userInfo: userInfoService.userInfoTarget.getClass().name.toLowerCase().contains("stub")?  "stub": "live",
      document: documentCommand.documentTarget.getClass().name.toLowerCase().contains("service")? "live": "stub",
      documentSystem: documentSearchService.documentTarget.getClass().name.toLowerCase().contains("service")? "live": "stub"
    ]

    render status as JSON
  }

  def stub(String domain) {

    def enablers = [
            workflow: { workflowService.workflowTarget = stubWorkflowService },
            userInfo:{ userInfoService.userInfoTarget = stubUserInfoService },
            document: {
              documentCommand.documentTarget = stubDocumentCommand
              documentSearchService.documentTarget = stubDocumentQuery
            }
    ]

    if (enablers[domain]) {
      enablers[domain]()
      render "$domain is using the runtime Stub"
    } else {
      render "No domain ${domain}"
    }

  }
  def live(String domain) {

    def enablers = [
            workflow: { workflowService.workflowTarget = proposalWorkflowService },
            userInfo:{ userInfoService.userInfoTarget = liveUserInfoService },
            document: {
              stubDocumentCommand.documentTarget = sitsCommandService
              documentSearchService.documentTarget = documentSystemService
            }
    ]

    if (enablers[domain]) {
      enablers[domain]()
      render "$domain is using the live domain"
    } else {
      render "No domain ${domain}"
    }
  }

  def eventStream(String domain) {
    def domains = [
            workflow: stubWorkflowService,
            userInfo: stubUserInfoService,
            document: stubDocumentQuery,
            documentCommand: stubDocumentCommand
    ]
    if (!domains[domain]) {
      render "No such domain"
      return
    }
    render domains[domain].stream as JSON
  }

  def clearStream(String domain) {
    def domains = [
            workflow: stubWorkflowService,
            userInfo: stubUserInfoService,
            document: stubDocumentQuery,
            documentCommand: stubDocumentCommand
    ]
    if (!domains[domain]) {
      render "No such domain"
      return
    }
    domains[domain].clear()
    render "Event stream cleared"
  }
}
