import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { InsertTransactionsComponent } from './insert-transactions/insert-transactions.component';

const routes: Routes = [
  {
    path:'', component:LoginComponent
  },
  {
    path:'register', component: RegisterComponent
  },
  {path:'insert', component:InsertTransactionsComponent}
  ,
  {path:'transactions', component:TransactionsComponent}
  // { path: '**', component:  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
