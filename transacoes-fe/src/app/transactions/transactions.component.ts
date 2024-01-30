import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { TransactionService } from './transaction.service';
import Transaction from '../insert-transactions/transaction';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrl: './transactions.component.scss'
})
export class TransactionsComponent implements OnInit {


  token: string | null = null;
  person:any;
  editOverView:null | Transaction= null;
  category: string= '';
  transactions:Transaction[] = [];
  valueOfFilteredTransactions= 0;
  clicked = false;
  deleteField = false;
  confirmation = '';
  filterNumber = 0;
  constructor(private router:Router, private cookies:CookieService, private service:TransactionService){
  
  }
  ngOnInit(): void {
    this.getPersonWithTransactions();
  }
  // deletando transação pelo id
  deletingTransaction(id:number){
    this.service.deleteTransactionById(id).subscribe((req)=>{
      this.getPersonWithTransactions();
    },(err)=>{
      alert('Erro ao tentar deletar tente novamente')

    },
    () => {})
  }
  // Colocando uma transação no item que vai aparecer no meio da tela
  editingTransaction(transaction:Transaction){
    this.editOverView = transaction;

  }
  deleteAllTransactionsFromUser(){
    if(this.confirmation === 'DELETAR') {
      this.service.deleteByPersonId(Number(this.token)).subscribe(()=> {
        alert("você deletou todas as transações")
        this.getPersonWithTransactions();
      },(err)=>{
        alert('Erro ao tentar deletar tudo')
      },
      ()=>{})
    } else {
      alert("Você dele digitar DELETAR em cacha alta")
    }
  }

  filterByNumber(){
    if(!this.transactions.some((transaction)=> transaction.id === this.filterNumber)){
      alert("Transação não encontrada")
    } else{
      this.transactions = this.transactions.filter((transacion) => transacion.id === this.filterNumber)
    }
  }

  showDeleteField(){
    this.deleteField = !this.deleteField;
  }
  updateTransaction() {
    if(this.editOverView ) {
     
      this.service.updateTransaction(this.editOverView , Number(this.token)).subscribe((res)=>{
        this.getPersonWithTransactions();
        this.editOverView = null;
      },(err)=>{
        alert('Erro ao tentar atualizar transação')
      },()=>{
      })
    } 
  }

  // eu poderia ter feito o calculo em relação às transações filtradas por categoria no proprios front-end,
  // nesse filter sem mudar nada nem aumentar complexidade mas fiz no back-end pois não sabia aonde seria preferivel.
    categoryFilter() {
        this.service.getTransactionsByCategory(Number(this.token) , this.category).subscribe((res)=>{ 
          this.valueOfFilteredTransactions = res;
          this.clicked = true;
        }
        ,(err)=>{alert("algum problema ocorreu ao tentar acessar categoria ou id no banco")} ,()=>{
          this.transactions = this.transactions.filter((transaction:Transaction) => transaction.category === this.category);
        });
      }

    cleaningFilters() {
      this.getPersonWithTransactions();
      this.clicked = false;
      this.category = '';
      }

    getPersonWithTransactions() {
      const cookie = this.cookies.get("security");
      this.token = cookie;
      if(!cookie) {
         this.router.navigate([""]);
         alert("Você só podera ver as transações se estiver logado!")
      }  else {
          this.service.getPersonTransactions(+cookie).subscribe((person)=>{
            this.person = person;
            this.transactions = person.transactions;
            this.clicked = false;
          }, (err) => {
            alert('Erro ao tentar fazer a requisição da pessoa e transações tente novamente')
          },() => {})
      }
    }

    
}
