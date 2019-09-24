import { TestBed } from '@angular/core/testing';

import { QuilleditorService } from './quilleditor.service';

describe('QuilleditorService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: QuilleditorService = TestBed.get(QuilleditorService);
    expect(service).toBeTruthy();
  });
});
