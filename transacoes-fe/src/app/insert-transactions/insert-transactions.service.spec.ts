import { TestBed } from '@angular/core/testing';

import { InsertTransactionsService } from './insert-transactions.service';

describe('InsertTransactionsService', () => {
  let service: InsertTransactionsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InsertTransactionsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
