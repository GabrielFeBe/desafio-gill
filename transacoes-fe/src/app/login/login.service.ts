import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  loginUrl = 'http://localhost:8080/person/login'
  constructor(private http:HttpClient){}

  login(email:string,password:string):Observable<number>{
    return this.http.post<number>(this.loginUrl, { email, password})

  }
}
