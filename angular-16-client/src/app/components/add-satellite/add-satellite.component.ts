import { Component } from '@angular/core';
import { Satellites } from 'src/app/models/satellites.model';
import { SatelliteService } from 'src/app/services/satellite.service';

@Component({
  selector: 'app-add-satellite',
  templateUrl: './add-satellite.component.html',
  styleUrls: ['./add-satellite.component.css'],
})
export class AddSatelliteComponent {
  satellite: Satellites = {
    category: '',
    name: '',
    description: '',
    launched: false
  };
  submitted = false;

  constructor(private satelliteService: SatelliteService) {}

  saveSatellite(): void {
    const data = {
      category: this.satellite.category,
      name: this.satellite.name,
      description: this.satellite.description
    };

    this.satelliteService.create(data).subscribe({
      next: (res) => {
        console.log(res);
        this.submitted = true;
      },
      error: (e) => console.error(e)
    });
  }

  newSatellite(): void {
    this.submitted = false;
    this.satellite = {
      category: '',
      name: '',
      description: '',
      launched: false
    };
  }
}
