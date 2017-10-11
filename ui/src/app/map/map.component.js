import templateUrl from './map.component.html'

/* @ngInject */
class MapController {
  zoom = 7
  center = [35.5175, -86.5804]
  markers = []
  paths = []
  chattanooga = {
    latitude:0,
    longitude:0
  }
  memphis = {
    latitude:0,
    longitude:0
  }
  nashville = {
    latitude:0,
    longitude:0
  }
  knoxville = {
    latitude:0,
    longitude:0
  }

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

    //paths.forEach(args => this.addPath(...args))

    // add path from webservice   
    $map.getMarkerByCityName('Chattanooga')
      .then(done => {
        //this.addPath(knoxville, chattanooga, '#FF3388')
        this.chattanooga.latitute = done.latitude
        //alert(chattanooga.latitude)
        //alert(done.latitude)
        this.chattanooga.longitude = done.longitude
      })

    //alert(this.chattanooga.latitude)

    $map.getMarkerByCityName('Memphis')
      .then(done => {
        this.memphis.latitude = done.latitude
        this.memphis.longitude = done.longitude
      })

    $map.getMarkerByCityName('Nashville')
      .then(done => {
        this.nashville.latitude = done.latitude
        this.nashville.longitude = done.longitude
      })

    $map.getMarkerByCityName('Knoxville')
      .then(done => {
        this.knoxville.latitude = done.latitude
        this.knoxville.longitude = done.longitude
      })

      const markers = [this.memphis, this.nashville, this.knoxville]
      const paths = [
        [this.memphis, this.nashville,'#CC0099'],
        [this.nashville, this.knoxville, '#AA1100'],
        [this.knoxville, this.chattanooga, '#FF3388']
      ]

      markers.forEach(marker => this.addMarker(marker))
      paths.forEach(args => this.addPath(...args))

      // paths.forEach(path => this.addPath(path))

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
