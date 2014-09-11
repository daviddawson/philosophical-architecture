package uk.college.workflow

class UserDetails {

  String username
  String forename
  String surname

  String department
  String emailAddress

  String getFullName() {
    "${forename} ${surname}"
  }

}
