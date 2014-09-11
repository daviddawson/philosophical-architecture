package uk.college.ui.tasks

import grails.validation.Validateable
import groovy.util.logging.Slf4j
import org.springframework.beans.BeanUtils
import uk.college.workflow.DocumentDetails
import uk.college.workflow.Workflow
import uk.college.workflow.save.SaveModuleProposalEvent
import uk.college.user.UserInfo

@Validateable
@Slf4j
class SaveDocument {

  Workflow workflowService
  UserInfo userInfoService

  String proposalId
  String moduleTitle
  String creditValue
  String independentStudyModule
  String creditLevel
  String moduleCoordinator
  String involvedDepartments
  String jacsCode

  //delivery
  String teachingCycle
  String preRequisiteModules
  String coRequisiteModule

  def save() {

    //TODO, if an error or exception comes back from this, add that error to the spring errors and return false.
    //TODO, using this --> errors.allErrors.add(new ObjectError())
    //TODO, for a field --> errors.getAllErrors().add(new FieldError())

    /*
     * SIDOC
     * This is the crossing of the domain boundary, converting from this class that knows about HTTP and the UI tasks
     * that the user is performing, into the events that the core stubcontrol uses that isn't aware of the UI functions.
     */
    def ev = new SaveModuleProposalEvent(details: details, userDetails: userInfoService.currentUserDetails)

    def result = workflowService.documentSaved(ev)

    return result.success
  }

  DocumentDetails getDetails() {
    def details = new DocumentDetails()
    BeanUtils.copyProperties(this, details, "metaClass")
    details
  }
}
