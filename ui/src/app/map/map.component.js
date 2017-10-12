import templateUrl from './map.component.html'

/* @ngInject */
class MapController {
  zoom = 7
  center = [35.5175, -86.5804]
  markers = []
  paths = []

  constructor ($map, locations) {
    this.$map = $map

    
    // add markers from an angular constant
    const { chatanooga, memphis, nashville, knoxville } = locations
    const cities = {
      'Chatanooga':chatanooga,
      'Memphis':memphis,
      'Nashville':nashville,
      'Knoxville':knoxville
    }

    console.log(cities['Chatanooga'])

    let flightColors = ['#CC0099','#AA1100','#FF3388','#333388','#572072']

    // add path from webservice   
    $map.getMarkerByCityName('Chattanooga')
      .then((done) => {
        //this.addPath(knoxville, chattanooga, '#FF3388')
        this.addMarker(done)
      })
      

    $map.getMarkerByCityName('Memphis')
      .then((done) => {
        this.addMarker(done)
      })


    $map.getMarkerByCityName('Nashville')
      .then((done) => {
        this.addMarker(done)
      })


    $map.getMarkerByCityName('Knoxville')
      .then((done) => {
        this.addMarker(done)
      })

      //Should add a path for each flight in the schedule
      $map.getFlightPaths()
        .then((done) => {
          let colorCount = 0
          done.forEach((flight) => {
            this.addPath(cities[flight.origin],cities[flight.destination],flightColors[colorCount++])
          })
        })

  }

  addMarker ({ latitude, longitude }) {
    this.markers.push({
      position: `[${latitude}, ${longitude}]`
    })
  }

  addPath (a, b, color) {
    this.paths.push({
      path: `[[${a.latitude}, ${a.longitude}], [${b.latitude}, ${b.longitude}]]`,
      strokeColor: color,
      strokeOpacity: 1.0,
      strokeWeight: 3,
      geodesic: true
    })
  }

}

export default {
  templateUrl,
  controller: MapController,
  controllerAs: '$mapCtrl'
}
