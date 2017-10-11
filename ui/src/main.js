import angular from 'angular';
import './app/app.module'

angular.module('flightApp', ['ui.router', 'ngCookies']).config(['$stateProvider', '$urlRouterProvider', function (stateProvider, urlRouter){

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
