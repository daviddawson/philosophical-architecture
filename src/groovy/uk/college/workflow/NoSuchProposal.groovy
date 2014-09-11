package uk.college.workflow

class NoSuchProposal extends RuntimeException {
  String proposalId

  NoSuchProposal(String proposalId) {
    super("Proposal with ID [${proposalId}] could not be found")
    this.proposalId = proposalId
  }
}
