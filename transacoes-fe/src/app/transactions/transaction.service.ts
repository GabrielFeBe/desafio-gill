import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import Transaction from '../insert-transactions/transaction';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  private url = "http://localhost:8080/";
  constructor(private http:HttpClient) { }

  public getPersonTransactions(id:number):Observable<any> {
    return this.http.get(this.url +"person" + `?id=${id}`);
  }
  public getTransactionsByCategory(id:number, category:string):Observable<number> {
    return this.http.get<number>(this.url + "transactions/category" + `?id=${id}&category=${category}`);
  }

  public updateTransaction(transaction:Transaction, id:number){
    return this.http.put<void>(this.url +"transactions" + `?id=${id}`, transaction);
  }

  public deleteByPersonId(personid:number) {
    return this.http.delete<void>(this.url + "transactions" + "/all" + `?personid=${personid}`)
  }

  public deleteTransactionById(id:number) {
     return this.http.delete<void>(this.url + "transactions" + `?id=${id}`);
  }

}
