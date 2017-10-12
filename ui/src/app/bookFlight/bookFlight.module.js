import bookFlightComponent from './bookFlight.component.js'
import bookFlightService from './bookFlight.service'

export default
  angular
    .module('flight.allFlights', ['ngAllFlights'])
    .constant('locations', allFlightsLocations)
    .component('allFlightsComponent', allFlightsComponent)
    .service('$allFlights', allFlightsService)
    .name