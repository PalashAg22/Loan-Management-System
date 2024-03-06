import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoanType } from '../Model/LoanType';
import { CustomerService } from '../services/customer.service';

@Component({
  selector: 'app-home-customer',
  templateUrl: './home-customer.component.html',
  styleUrls: ['./home-customer.component.css']
})
export class HomeCustomerComponent implements OnInit {

  loanTypeList: LoanType[] = [];
  tempLoanTypeList: LoanType[] = [];
  searchValue = '';
  constructor(private customerService: CustomerService, private router: Router) { }
  ngOnInit(): void {
    this.customerGetLoanType();
  }
  customerGetLoanType() {
    this.customerService.getAllLoanTypes().subscribe(
      (loanType) => {
        this.loanTypeList = loanType;
      }
    );
  }
  onSearch() {
    this.searchValue = this.searchValue.trim().toLowerCase();
    if (this.searchValue) {
      this.tempLoanTypeList = this.loanTypeList.filter(type =>
      (type.loanInterestBaseRate.toString().includes(this.searchValue) ||
        type.loanManagementFees.toString().includes(this.searchValue) ||
        type.loanTypeId.toString().includes(this.searchValue) ||
        type.loanTypeName.toLowerCase().includes(this.searchValue)))
    } else {
      this.tempLoanTypeList = [];
    }
  }
  
  applyLoan(loanTypeName:string) {
    this.router.navigate(['customer/apply-loan',loanTypeName])
  }

  calculateEMI(loanTypeName: string) {
    this.router.navigate(['calculateEMI/', loanTypeName]);
  }
}