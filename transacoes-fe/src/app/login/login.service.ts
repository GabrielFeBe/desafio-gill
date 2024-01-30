import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import endpoint from '../../endpoint';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  loginUrl = endpoint + '/person/login';
  constructor(private http:HttpClient){}

  login(email:string,password:string):Observable<number>{
    return this.http.post<number>(this.loginUrl, {email, password})

  }
}
