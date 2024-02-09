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
  username= '';
  password ='';
  name = '';
  constructor(private service:RegisterService, private router:Router, private cookies:CookieService, private location:Location){}
  onRegister(){
    this.service.registerPerson({username:this.username,
    password:this.password,
    name:this.name,
    }).subscribe((res)=>{
      this.router.navigate(['/']).then(() =>{
        this.location.replaceState('/');
        window.location.reload();
      } );
    },
    (err)=>{alert(err.error)},
    ()=>{
    
    })
  }

}
