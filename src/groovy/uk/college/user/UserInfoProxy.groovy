package uk.college.user


class UserInfoProxy implements UserInfo {

  @Delegate UserInfo userInfoTarget

}
