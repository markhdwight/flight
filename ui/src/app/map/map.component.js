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
    //const { memphis, nashville, knoxville } = locations
    //const markers = [memphis, nashville, knoxville]

    //markers.forEach(marker => this.addMarker(marker))

    // add paths manually
    //const paths = [
      //[memphis, nashville, '#CC0099'],
      //[nashville, knoxville, '#AA1100']
    //]

    paths.forEach(args => this.addPath(...args))

    // add path from webservice
    $map.getMarkerByCityName('Chattanooga')
      .then(done => {
        //this.addPath(knoxville, chattanooga, '#FF3388')
        chattanooga = done.data
      })

    $map.getMarkerByCityName('Memphis')
      .then(done => {
        memphis = done.data
      })

    $map.getMarkerByCityName('Nashville')
      .then(done => {
        nashville = done.data
      })

    $map.getMarkerByCityName('Knoxville')
      .then(done => {
        knoxville = done.data
      })

      const markers = [memphis, nashville, knoxville]
      const paths = [
        [memphis, nashville,'#CC0099'],
        [nashville, knoxville, '#AA1100'],
        [knoxville, chattanooga, '#FF3388']
      ]

      markers.forEach(marker => this.addMarker(marker))
      paths.forEach(path => this.addPath(path))

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
