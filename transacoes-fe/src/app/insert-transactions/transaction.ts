export default class Transaction{
  value:number;
  transactiondate:Date | null;
  personid:number;
  category:string;
  id?:number;
  constructor(value:number,transactiondate: Date | null,
    personid:number,category:string, id?:number
    ){
      if(id) this.id = id;
      this.value=value;
      this.transactiondate=transactiondate;
      this.personid = personid;
      this.category = category
    }
}