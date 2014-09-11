package uk.college.user

import uk.college.workflow.UserDetails

class LiveUserInfoService implements UserInfo {

  @Override
  UserDetails getCurrentUserDetails() {
    return new UserDetails(
            forename: "Nicoli",
            surname: "Tesla",
            username: "ts123")
  }

  @Override
  UserDetails getUserDetails(String username) {
    return new UserDetails(
            forename: "Nicoli",
            surname: "Tesla",
            username: username)
  }
}
