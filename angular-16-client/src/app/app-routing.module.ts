import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SatellitesListComponent } from './components/satellites-list/satellites-list.component';
import { SatelliteDetailsComponent } from './components/satellite-details/satellite-details.component';
import { AddSatelliteComponent } from './components/add-satellite/add-satellite.component';

const routes: Routes = [
  { path: '', redirectTo: 'satellites', pathMatch: 'full' },
  { path: 'satellites', component: SatellitesListComponent },
  { path: 'satellites/:id', component: SatelliteDetailsComponent },
  { path: 'add', component: AddSatelliteComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
