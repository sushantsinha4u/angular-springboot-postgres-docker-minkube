import { Component, OnInit } from '@angular/core';
import { Satellites } from 'src/app/models/satellites.model';
import { SatelliteService } from 'src/app/services/satellite.service';

@Component({
  selector: 'app-satellites-list',
  templateUrl: './satellites-list.component.html',
  styleUrls: ['./satellites-list.component.css'],
})
export class SatellitesListComponent {
  satellites?: Satellites[];
  currentSatellite: Satellites = {};
  currentIndex = -1;
  category = '';

  constructor(private satelliteService: SatelliteService) {}

  ngOnInit(): void {
    this.retrieveSatellites();
  }

  retrieveSatellites(): void {
    this.satelliteService.getAll().subscribe({
      next: (data) => {
        this.satellites = data;
        console.log(data);
      },
      error: (e) => console.error(e)
    });
  }

  refreshList(): void {
    this.retrieveSatellites();
    this.currentSatellite = {};
    this.currentIndex = -1;
  }

  setActiveSatellite(satellites: Satellites, index: number): void {
    this.currentSatellite = satellites;
    this.currentIndex = index;
  }

  removeAllSatellites(): void {
    this.satelliteService.deleteAll().subscribe({
      next: (res) => {
        console.log(res);
        this.refreshList();
      },
      error: (e) => console.error(e)
    });
  }

  searchTitle(): void {
    this.currentSatellite = {};
    this.currentIndex = -1;

    this.satelliteService.findByCategory(this.category).subscribe({
      next: (data) => {
        this.satellites = data;
        console.log(data);
      },
      error: (e) => console.error(e)
    });
  }
}
