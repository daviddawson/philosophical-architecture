package uk.college.ui

import uk.college.ui.tasks.LoadHome

class HomeController {

  def index(LoadHome home) {
    home()
  }
}
