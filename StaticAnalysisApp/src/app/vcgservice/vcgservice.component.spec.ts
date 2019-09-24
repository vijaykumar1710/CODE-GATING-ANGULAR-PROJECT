import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VCGserviceComponent } from './vcgservice.component';

describe('VCGserviceComponent', () => {
  let component: VCGserviceComponent;
  let fixture: ComponentFixture<VCGserviceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VCGserviceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VCGserviceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
