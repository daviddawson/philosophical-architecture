package uk.college.documentmanagement


class StubDocumentSystem implements DocumentSystem {

  List received = []

  @Override
  List<DocumentData> getDocumentData(DocumentId module) {
    received << module
    return null
  }

  @Override
  List<DocumentData> getDocumentList() {
    received << ["moduleList": ""]
    return null
  }

}
