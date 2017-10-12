import flightMap from './map/map.module'
import apiUrl from './api.url'
import appComponent from './app.component.js'
import mapComponent from './map/map.component.js'
import signupComponent from './signup/signup.component.js'
import loginComponent from './login/login.component.js'
import bookFLightComponent from './bookFlight/bookFlight.component.js'

export default
  angular
    .module('flight', [
      'ngAria',
      'ngAnimate',
      'ngMaterial',
      'ngMessages',
      'ui.router',

      flightMap
    ]).config(['$stateProvider','$urlRouterProvider',
  function(stateProvider,urlRouter){
    
    const login = {
      name: 'login',
      url: '/login',
      component: 'loginComponent'
    }

    const signUp = {
      name: 'signup',
      url: '/signup',
      component: 'signupComponent'
    }

    const allFlights = {
      name:   'allFlights',
      url:    '/allFlights',
      component:  'mapComponent'
    }

    const bookFlight = {
      name: 'bookFlight',
      url:  '/bookFlight',
      component: 'bookFlightComponent'
    }

    stateProvider.state(allFlights)
    stateProvider.state(signUp)
    stateProvider.state(login)

    urlRouter.otherwise('/allFlights')
  }])
    .constant('apiUrl', apiUrl)
    .component('flightApp', appComponent)
    .name
