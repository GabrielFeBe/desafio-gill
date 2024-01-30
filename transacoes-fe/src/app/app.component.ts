import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  token:string | null = null;
  title = 'transacoes-fe';

  constructor(private cookies:CookieService){}  
  ngOnInit(): void {
    const cookie = this.cookies.get("security")
    this.token = cookie;
  }
  
}
