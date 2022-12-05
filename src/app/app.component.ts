import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'myApp';
  x:number=10;
  y:number=20;

  n1:number;
  n2:number;
  result:number;

  sub1:number;
  sub2:number;
  sub3:number;
  total_marks_secured:number;
  total_marks:number=300;
  percent:number;
  grade:string; 

  showAddress: boolean = false;

  addressObj = {
    hno: '95/3',
    street: 'Vinayakanagar 14th ward',
    city: 'Tumkur',
    postcode: '572101'
  }

  arryNum:number[]=[4,6,3,52,5,6,3];

  lblButtonText:string='Show Address';

  toggleAddress(){
    this.showAddress=!this.showAddress;
    // if(this.showAddress == false)
    //   this.lblButtonText="Show Address";
    // else
    //   this.lblButtonText='Hide Address';
  }

  calc(op:string){
    console.log(this.n1);
    console.log(this.n2);
    console.log(op);

    switch(op){
      case 'ADD':
        this.result =Number(this.n1)+Number(this.n2);
        break;
      case 'SUB':
        this.result =this.n1-this.n2;
        break;
      case 'MUL':
        this.result =this.n1*this.n2;
        break;
      case 'DIV':
        this.result =this.n1/this.n2;
        break;
    }
  }
  marks(r:string){
    this.total_marks_secured = Number(this.sub1)+Number(this.sub2)+Number(this.sub3);
    this.percent = (this.total_marks_secured * 100)/this.total_marks;
    if (this.percent >=90) {
      this.grade='A';
    } 
    if (this.percent >=75) {
      this.grade='B'
    } else{
      this.grade='C'
    }
    
  }


}
