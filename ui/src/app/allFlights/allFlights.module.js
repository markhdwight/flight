import allFlightsLocations from './allFlights.locations'
import allFlightsComponent from './allFlights.component.js'
import allFlightsService from './allFlights.service'

export default
  angular
    .module('flight.allFlights', ['ngAllFlights'])
    .constant('locations', allFlightsLocations)
    .component('allFlightsComponent', allFlightsComponent)
    .service('$allFlights', allFlightsService)
    .name