import { Component, Input, OnInit } from '@angular/core';
import { SatelliteService } from 'src/app/services/satellite.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Satellites } from 'src/app/models/satellites.model';

@Component({
  selector: 'app-satellite-details',
  templateUrl: './satellite-details.component.html',
  styleUrls: ['./satellite-details.component.css'],
})
export class SatelliteDetailsComponent {
  @Input() viewMode = false;

  @Input() currentSatellite: Satellites = {
    category: '',
    name: '',
    description: '',
    launched: false
  };

  message = '';

  constructor(
    private satelliteService: SatelliteService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getSatellite(this.route.snapshot.params['id']);
    }
  }

  getSatellite(id: string): void {
    this.satelliteService.get(id).subscribe({
      next: (data) => {
        this.currentSatellite = data;
        console.log(data);
      },
      error: (e) => console.error(e)
    });
  }

  updateLaunched(status: boolean): void {
    const data = {
      category: this.currentSatellite.category,
      name: this.currentSatellite.name,
      description: this.currentSatellite.description,
      launched: status
    };

    this.message = '';

    this.satelliteService.update(this.currentSatellite.id, data).subscribe({
      next: (res) => {
        console.log(res);
        this.currentSatellite.launched = status;
        this.message = res.message
          ? res.message
          : 'The satellite detail  was updated successfully!';
      },
      error: (e) => console.error(e)
    });


  }

  updateSatellite(): void {
    this.message = '';

    this.satelliteService
      .update(this.currentSatellite.id, this.currentSatellite)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message
            ? res.message
            : 'This satellite detail was updated successfully!';
        },
        error: (e) => console.error(e)
      });
  }

  deleteSatellite(): void {
    this.satelliteService.delete(this.currentSatellite.id).subscribe({
      next: (res) => {
        console.log(res);
        this.router.navigate(['/satellites']);
      },
      error: (e) => console.error(e)
    });
  }
}
