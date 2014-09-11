package uk.college.documentmanagement

class DocumentCommandProxy implements DocumentCommand {
  @Delegate DocumentCommand documentTarget
}
