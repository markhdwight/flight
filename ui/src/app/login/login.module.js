import loginComponent from './login.component.js'
import loginService from './login.service'

export default
  angular
    .module('flight.login', [])
    .component('flightLogin', loginComponent)
    .service('$login', loginService)
    .name