import { TestBed } from '@angular/core/testing';

import { GitdownloadService } from './gitdownload.service';

describe('GitdownloadService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GitdownloadService = TestBed.get(GitdownloadService);
    expect(service).toBeTruthy();
  });
});
