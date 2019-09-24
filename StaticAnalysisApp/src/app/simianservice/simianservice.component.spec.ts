import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SimianserviceComponent } from './simianservice.component';

describe('SimianserviceComponent', () => {
  let component: SimianserviceComponent;
  let fixture: ComponentFixture<SimianserviceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SimianserviceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SimianserviceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
