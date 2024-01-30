import { Component } from '@angular/core';
import { RegisterService } from './register.service';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  email= '';
  password ='';
  name = '';
  constructor(private service:RegisterService, private router:Router, private cookies:CookieService, private location:Location){}
  onRegister(){
    this.service.registerPerson({email:this.email,
    password:this.password,
    name:this.name,
    }).subscribe((res)=>{
      this.cookies.set('security',`${res.id}`, {expires:1})
      this.router.navigate(['/transactions']).then(() =>{
        this.location.replaceState('/transactions');
        window.location.reload();
      } );
    },
    (err)=>{console.log(err)},
    ()=>{
    
    })
  }

}
