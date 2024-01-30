import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { InsertTransactionsService } from './insert-transactions.service';
import Transaction from './transaction';

@Component({
  selector: 'app-insert-transactions',
  templateUrl: './insert-transactions.component.html',
  styleUrl: './insert-transactions.component.scss'
})
export class InsertTransactionsComponent implements OnInit{


value:number = 0;
transactiondate:Date|null = null;
category:string ='';
cookie:string | null = null;
bulkTransactionArray:Transaction[] = [];
isBulk = false;
constructor(private router:Router, private cookies:CookieService, private service: InsertTransactionsService){}
  
ngOnInit(): void {
  const cookie = this.cookies.get("security");
  if(!cookie) {
    this.router.navigate([""]);
    alert("Você só podera inserir as transações se estiver logado!")
  } else{
    this.cookie = cookie;
    this.bulkTransactionArray.push(new Transaction(0,null,Number(this.cookie),''));
  }
  
}

insertBulkOfTransactions() {
  console.log(this.bulkTransactionArray)
this.service.insertBulkTransaction(this.bulkTransactionArray).subscribe((res)=>{
  console.log(res)
  alert("lote de transações inseridas com sucesso")
}
,(err)=>{ console.log(err)
alert("falha ao efetuar o lote")
}
,()=>{
this.bulkTransactionArray = [new Transaction(0,null,Number(this.cookie),'')];
});

}

bulkToglle(){
  this.isBulk = !this.isBulk;
}

addNewFields(){
  this.bulkTransactionArray.push(new Transaction(0,null,Number(this.cookie),''));
}

insertTransaction() {
  const transaction = new Transaction(this.value,this.transactiondate,Number(this.cookie),this.category);
  this.service.insertSingleTransaction(transaction).subscribe((res)=>{
    console.log(res)
    alert("transação adicionada com sucesso")
  }
  ,(err)=>{ console.log(err)
  alert("falha ao efetuar transação")
  }
  ,()=>{
    this.category = '';
    this.transactiondate = null;
    this.value = 0;
  });
  }
}
