import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSatelliteComponent } from './add-satellite.component';

describe('AddSatelliteComponent', () => {
  let component: AddSatelliteComponent;
  let fixture: ComponentFixture<AddSatelliteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddSatelliteComponent]
    });
    fixture = TestBed.createComponent(AddSatelliteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
