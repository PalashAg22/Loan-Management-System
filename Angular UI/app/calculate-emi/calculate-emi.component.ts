import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from '../services/customer.service';
import { LoanType } from '../Model/LoanType';

@Component({
  selector: 'app-calculate-emi',
  templateUrl: './calculate-emi.component.html',
  styleUrls: ['./calculate-emi.component.css']
})
export class CalculateEmiComponent {
  constructor(private route:ActivatedRoute,private customerService:CustomerService, private router:Router){}
  selectedLoan!:string;
  loanType : LoanType [] = [];
  
  ngOnInit(){
    this.getLoanTypes();
    this.status();
  }
  getLoanTypes(){
    this.customerService.getAllLoanTypes().subscribe(
      (response)=>{
        this.loanType = response;
      }
    )
  }
  status(){
    this.route.params.subscribe(params => this.selectedLoan = params['loanTypeName']);
  }

  submitForm(data:any){
    console.log(data.loanAmount+" "+ data.loanDuration+ " " + data.loanType);
    this.customerService.calculateEMI(data.loanAmount, data.loanDuration,data.loanType)
    .subscribe(
      (response)=>{
        alert("Your estimated emi will be: "+ response);
        // this.router.navigate(['customer/home']);
        console.log(response);
      }
    );
  }
}
