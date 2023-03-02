import { TestBed } from '@angular/core/testing';

import { HidecomponentService } from './hidecomponent.service';

describe('TheserviceService', () => {
  let service: HidecomponentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HidecomponentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
