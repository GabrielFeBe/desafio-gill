import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';
@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private cookieService:CookieService){}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.cookieService.get("security");
    if(!token) {
      return next.handle(req);
    } 
    const req1 = req.clone({
      headers: req.headers.set('Authorization', `Bearer ${token}`),
    })
    return next.handle(req1);

  }
 
}