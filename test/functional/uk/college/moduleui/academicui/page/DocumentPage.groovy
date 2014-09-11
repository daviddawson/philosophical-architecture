package uk.college.moduleui.academicui.page

import geb.Page

class DocumentPage extends Page {

  static url = "module/propose"
  static at = { driver.title.endsWith "Create Module Proposal" }
  static content = {
    proposalId { $(id:"proposal-id") }
    moduleTitle { $(id:"module-title") }
    text { $(id:"credit-value") }

    saveButton { $(id:"saveProposalButton")}
    submitButton { $(id:"submitProposalButton")}
  }

  def save() {
    saveButton.click()
  }

  def submit() {
    submitButton.click()
  }

}
