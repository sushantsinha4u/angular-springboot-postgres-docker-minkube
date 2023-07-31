import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddSatelliteComponent } from './components/add-satellite/add-satellite.component';
import { SatelliteDetailsComponent } from './components/satellite-details/satellite-details.component';
import { SatellitesListComponent } from './components/satellites-list/satellites-list.component';

@NgModule({
  declarations: [
    AppComponent,
    AddSatelliteComponent,
    SatelliteDetailsComponent,
    SatellitesListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
