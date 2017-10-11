import loginComponent from './signup.component.js'
import loginService from './signup.service'

export default
  angular
    .module('flight.Signup', [])
    .component('flightSignup', signupComponent)
    .service('$signup', signupService)
    .name