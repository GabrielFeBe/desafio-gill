import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { LoginService } from './login.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

username= '';
password ='';
constructor(private router:Router, private cookies:CookieService, private service: LoginService, private location:Location){
  
}
onLogin(){
  // O número de dias até expirar
  this.service.login(this.username,this.password).subscribe((res)=> {
    this.cookies.set("security", res.token , { expires: 1});
    this.router.navigate(['/transactions']).then(() =>{
      this.location.replaceState('/transactions');
      window.location.reload();
    } );
  },
  (err)=>{ 
    if(err.status === 403) {
      alert("Email ou senha incorrreto")
      this.password = ''
      this.username = ''
    } else {
      this.password = ''
      this.username = ''
      alert(err.error)
    }
  }
  ,()=>{
  })

}
}
