package uk.college.workflow

import org.springframework.validation.Errors

class DocumentDetails {

  Date dateProposed

  UserDetails proposingUser

  ProposalStatus status

  //summary
  String documentId
  String moduleTitle
  String text

  Errors errors

}
