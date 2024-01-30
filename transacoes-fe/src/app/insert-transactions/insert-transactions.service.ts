import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import Transaction from './transaction';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InsertTransactionsService {
  private transactionUrl = 'http://localhost:8080/transactions';
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
