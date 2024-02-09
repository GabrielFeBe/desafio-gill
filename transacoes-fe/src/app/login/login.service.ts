import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import endpoint from '../../endpoint';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  loginUrl = endpoint + '/auth/login';
  constructor(private http:HttpClient){}

  login(username:string,password:string):Observable<{token:string}>{
    return this.http.post<{token:string}>(this.loginUrl, {username, password})

  }
}
