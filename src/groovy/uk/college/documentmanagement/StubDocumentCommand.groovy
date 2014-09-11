package uk.college.documentmanagement

class StubDocumentCommand implements DocumentCommand {

  @Override
  SitsProposalSubmissionResult proposeNewModule(SitsNewModuleProposal moduleProposal) {
    new SitsProposalSubmissionResult(succeeded: true)
  }

}
