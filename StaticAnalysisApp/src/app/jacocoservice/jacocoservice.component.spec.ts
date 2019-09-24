import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JacocoserviceComponent } from './jacocoservice.component';

describe('JacocoserviceComponent', () => {
  let component: JacocoserviceComponent;
  let fixture: ComponentFixture<JacocoserviceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JacocoserviceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JacocoserviceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
