import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoanApplicationRequest } from '../Model/LoanApplicationRequest';
import { UserAuthService } from '../services/user-auth.service';
import { Customer } from '../Model/Customer';
import { LoanType } from '../Model/LoanType';
import { CustomerService } from '../services/customer.service';

@Component({
  selector: 'app-update-applied-loan',
  templateUrl: './update-applied-loan.component.html',
  styleUrls: ['./update-applied-loan.component.css']
})
export class UpdateAppliedLoanComponent {

  constructor(private router:Router, private customerService:CustomerService ,private route:ActivatedRoute,private userAuthService:UserAuthService){}
  loanId!:number;
  loanApplicationRequest = new LoanApplicationRequest();
  file!:File;
  customer:Customer = this.userAuthService.getCustomer();
  loanType:LoanType [] = [];


  ngOnInit(){
    this.route.params.subscribe(params => this.loanId=params['loanId'])
    this.customerService.getAllLoanTypesCustomer().subscribe(
      (response)=>{
        this.loanType = response;
      }
    )
    console.log(this.loanId);
  }
  onFileUpload(data:any){
    this.file =data.target.files[0];
    console.log(this.file);
  }
  getData(data:any){
    console.log(data);
    this.loanApplicationRequest.loanId = this.loanId;
    this.loanApplicationRequest.customerId = this.customer.customerId;
     this.loanApplicationRequest.loanTypeName = data.loanType;
     this.loanApplicationRequest.principal = data.principal;
     this.loanApplicationRequest.propertyAddress = data.propertyAddress;
     this.loanApplicationRequest.propertyAreaInm2 = data.propertyAreaInm2;
     this.loanApplicationRequest.propertyValue = data.propertyValue;
     this.loanApplicationRequest.tenureInMonths = data.tenureInMonths;
     console.log(this.loanApplicationRequest); 
    this.customerService.updateAppliedLoan(this.loanApplicationRequest,this.file).subscribe(
      (Respose)=>{
        alert("successfully updated")
        window.location.reload();
      }
    );
    
    this.router.navigate(['customer/myLoans']);
    
  }
}
