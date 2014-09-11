import grails.util.Environment
import uk.college.documentmanagement.DocumentCommandProxy
import uk.college.documentmanagement.DocumentSystemProxy
import uk.college.documentmanagement.StubDocumentSystem
import uk.college.workflow.StubWorkflow
import uk.college.workflow.WorkflowProxy
import uk.college.documentmanagement.StubDocumentCommand
import uk.college.user.StubUserInfo
import uk.college.user.UserInfoProxy

beans = {
  xmlns context:"http://www.springframework.org/schema/context"
  context.'component-scan'('base-package': "uk.college")

  stubWorkflowService(StubWorkflow)
  stubUserInfoService(StubUserInfo)
  stubDocumentCommand(StubDocumentCommand)
  stubDocumentQuery(StubDocumentSystem)

  userInfoService(UserInfoProxy) {
    userInfoTarget = ref("liveUserInfoService")
  }
  workflowService(WorkflowProxy) {
    workflowTarget = ref("proposalWorkflowService")
  }
  documentCommand(DocumentCommandProxy) {
    documentTarget = ref("stubDocumentCommand")
  }
  documentSearchService(DocumentSystemProxy) {
    documentTarget = ref("documentSystemService")
  }
}
