import { TestBed } from '@angular/core/testing';

import { TheserviceService } from './theservice.service';

describe('TheserviceService', () => {
  let service: TheserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TheserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
