package uk.college.workflow

class WorkflowProxy implements Workflow {

  @Delegate Workflow workflowTarget

}
