package uk.college.moduleui

import groovy.json.JsonSlurper

class DomainStubControl {

  def baseUrl

  def useSitsStub() {
    new URL("${baseUrl}domain/documentmanagement/stub").text
  }
  def useSitsLive() {
    new URL("${baseUrl}domain/documentmanagement/live").text
  }
  def useWorkflowStub() {
    new URL("${baseUrl}domain/workflow/stub").text
  }
  def useWorkflowLive() {
    new URL("${baseUrl}domain/workflow/live").text
  }
  def useUserInfoStub() {
    new URL("${baseUrl}domain/userInfo/stub").text
  }
  def useUserInfoLive() {
    new URL("${baseUrl}domain/userInfo/live").text
  }

  def clearWorkflowStream() {
    new URL("${baseUrl}domain/workflow/clearStream").text
  }
  def clearUserInfoStream() {
    new URL("${baseUrl}domain/userInfo/clearStream").text
  }

  def eventsUserInfo() {
    new JsonSlurper().parseText(new URL("${baseUrl}domain/userInfo/eventStream").text)
  }
  def eventsNotification() {
    new JsonSlurper().parseText(new URL("${baseUrl}domain/notification/eventStream").text)
  }
  def eventsWorkflow() {
    new JsonSlurper().parseText(new URL("${baseUrl}domain/workflow/eventStream").text)
  }
}
