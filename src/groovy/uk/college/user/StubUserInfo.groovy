package uk.college.user

import uk.college.workflow.UserDetails

class StubUserInfo implements UserInfo {

  @Override
  UserDetails getCurrentUserDetails() {
    new UserDetails(
            forename: "Gerald",
            surname: "Butler",
            username: "gb123")
  }

  @Override
  UserDetails getUserDetails(String username) {
    return new UserDetails(
            forename: "Gerald",
            surname: "Butler",
            department: "Science",
            username: username)
  }

  def clear() {}
}
