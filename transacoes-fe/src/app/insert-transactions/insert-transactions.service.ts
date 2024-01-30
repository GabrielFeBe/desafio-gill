import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import Transaction from './transaction';
import { Observable } from 'rxjs';
import endpoint from '../../endpoint';

@Injectable({
  providedIn: 'root'
})
export class InsertTransactionsService {
  private transactionUrl = endpoint + '/transactions';
  constructor(private http:HttpClient) { }

  insertSingleTransaction(transaction: Transaction):Observable<any> {
    return this.http.post(this.transactionUrl,transaction);
  }
  insertBulkTransaction(transaction: Transaction[]):Observable<any> {
    return this.http.post(this.transactionUrl + "/batch",{
      numberOfInsertions: transaction.length,
      value: transaction
    });
  }

}
