package uk.college.workflow

import org.springframework.beans.BeanUtils

class ProposedDocument {

  String status = ProposalStatus.PENDING.name()

  String id

  String proposingUser

  //summary
  String moduleCode
  String moduleTitle

  String text

  Object asType(Class type) {
    if (type != DocumentDetails) {
      return super.asType(type)
    }
    toDetails()
  }

  DocumentDetails toDetails() {
    DocumentDetails details = new DocumentDetails()

    BeanUtils.copyProperties(this, details, "metaClass", "status")
    details.documentId = id
    details.status = ProposalStatus.valueOf(status)

    details
  }
}
