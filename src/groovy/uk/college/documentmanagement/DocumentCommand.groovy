package uk.college.documentmanagement

public interface DocumentCommand {
  SitsProposalSubmissionResult proposeNewModule(SitsNewModuleProposal moduleProposal)
}

class SitsProposalSubmissionResult {
  boolean succeeded
}

class SitsNewModuleProposal {

}