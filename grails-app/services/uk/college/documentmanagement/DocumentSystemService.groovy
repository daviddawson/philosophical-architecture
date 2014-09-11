package uk.college.documentmanagement

import grails.transaction.Transactional

@Transactional
class DocumentSystemService implements DocumentSystem {

    @Override
    List<DocumentData> getDocumentData(DocumentId module) {
      throw new IllegalStateException("Not Implemented")
    }

    @Override
    List<DocumentData> getDocumentList() {
      throw new IllegalStateException("Not Implemented")
    }
}
