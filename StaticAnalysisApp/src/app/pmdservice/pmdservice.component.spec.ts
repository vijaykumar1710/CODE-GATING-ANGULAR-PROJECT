import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PmdserviceComponent } from './pmdservice.component';

describe('PmdserviceComponent', () => {
  let component: PmdserviceComponent;
  let fixture: ComponentFixture<PmdserviceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PmdserviceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PmdserviceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
