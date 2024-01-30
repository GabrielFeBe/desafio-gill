import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  token:string | null = null;
  title = 'transacoes-fe';

  constructor(private cookies:CookieService, private router:Router, private location:Location){}  
  ngOnInit(): void {
    const cookie = this.cookies.get("security")
    this.token = cookie;
  }
  
  logOut(){
    this.cookies.delete("security")
    this.router.navigate(['']).then(()=> {
      this.location.replaceState('');
      window.location.reload();
    })

  }

}
