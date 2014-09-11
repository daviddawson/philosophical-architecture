package uk.college.documentmanagement

public interface DocumentSystem {
    List<DocumentData> getDocumentData(DocumentId module)
    List<DocumentData> getDocumentList()
}
