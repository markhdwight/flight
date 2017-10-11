import flightMap from './map/map.module'
import apiUrl from './api.url'
import appComponent from './app.component.js'
import mapComponent from './map/map.component.js'

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
      name: 'signUp',
      url: '/signUp',
      component: 'signUpComponent'
    }

    const allFlights = {
      name:   'allFlights',
      url:    '/allFlights',
      component:  'mapComponent'
    }

    stateProvider.state(allFlights)

    urlRouter.otherwise('/allFlights')
  }])
    .constant('apiUrl', apiUrl)
    .component('flightApp', appComponent)
    .name
