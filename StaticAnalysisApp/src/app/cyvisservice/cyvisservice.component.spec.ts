import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CyvisserviceComponent } from './cyvisservice.component';

describe('CyvisserviceComponent', () => {
  let component: CyvisserviceComponent;
  let fixture: ComponentFixture<CyvisserviceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CyvisserviceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CyvisserviceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
