import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  private url = 'http://localhost:8080/person';
  constructor(private http: HttpClient) { }

  registerPerson(person:any):Observable<any>{
    return this.http.post<any>(this.url, person);
  }


}
