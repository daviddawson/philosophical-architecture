package uk.college.user

import uk.college.workflow.UserDetails

interface UserInfo {

  UserDetails getCurrentUserDetails()

  UserDetails getUserDetails(String username)

}
