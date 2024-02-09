import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import Transaction from '../insert-transactions/transaction';
import endpoint from '../../endpoint';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  private url = endpoint +  '/';
  constructor(private http:HttpClient) { }

  public getPersonTransactions():Observable<any> {
    return this.http.get(this.url +"person");
  }
  public getTransactionsByCategory(category:string):Observable<number> {
    return this.http.get<number>(this.url + "transactions/category" + `?category=${category}`);
  }

  public updateTransaction(transaction:Transaction){
    return this.http.put<void>(this.url +"transactions" , transaction);
  }

  public deleteByPersonId() {
    return this.http.delete<void>(this.url + "transactions" + "/all" )
  }

  public deleteTransactionById(id:number) {
     return this.http.delete<void>(this.url + "transactions" + `?id=${id}`);
  }

}
