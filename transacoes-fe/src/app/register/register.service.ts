import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import endpoint from '../../endpoint';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  private url =  endpoint + '/person';
  constructor(private http: HttpClient) { }

  registerPerson(person:any):Observable<any>{
    return this.http.post<any>(this.url, person);
  }


}
