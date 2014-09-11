package uk.college.documentmanagement

class DocumentSystemProxy implements DocumentSystem {
  @Delegate DocumentSystem documentTarget
}
