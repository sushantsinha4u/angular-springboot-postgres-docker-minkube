import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SatelliteDetailsComponent } from './satellite-details.component';

describe('SatelliteDetailsComponent', () => {
  let component: SatelliteDetailsComponent;
  let fixture: ComponentFixture<SatelliteDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SatelliteDetailsComponent]
    });
    fixture = TestBed.createComponent(SatelliteDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
