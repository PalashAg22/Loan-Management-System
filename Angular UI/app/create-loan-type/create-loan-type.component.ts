// import { Component, OnInit } from '@angular/core';
// import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import { LoanType } from '../Model/LoanType';
// import { LoanTypeService } from '../services/loan-type.service';

// @Component({
//   selector: 'app-create-loan-type',
//   templateUrl: './create-loan-type.component.html',
//   styleUrls: ['./create-loan-type.component.css']
// })
// export class CreateLoanTypeComponent implements OnInit {

//   loanTypeForm!: FormGroup;

//   constructor(private formBuilder: FormBuilder,private loanTypeService:LoanTypeService) { }

//   ngOnInit(): void {
//     this.loanTypeForm = this.formBuilder.group({
//       loanTypeName: ['', Validators.required],
//       loanInterestBaseRate: ['', Validators.required],
//       loanManagementFees: ['', Validators.required]
//     });
//   }

//   onSubmit() {
//     if (this.loanTypeForm.valid) {
//       this.loanTypeService.createNewLoan(this.loanTypeForm.value).subscribe((created)=>{
//         // if(created) {
//           alert(created);
//         // }else{
//         //   alert("Something went wrong");
//         // }
//       });
//     }
//   }

// }
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoanType } from '../Model/LoanType';
import { LoanTypeService } from '../services/loan-type.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-loan-type',
  templateUrl: './create-loan-type.component.html',
  styleUrls: ['./create-loan-type.component.css']
})
export class CreateLoanTypeComponent implements OnInit {

  loanTypeForm!: FormGroup;

  constructor(private fb: FormBuilder,private loanTypeService:LoanTypeService,private router:Router) { }

  ngOnInit(): void {
    this.loanTypeForm = this.fb.group({
      loanTypeName: ['', Validators.required],
      loanInterestBaseRate: ['', [Validators.required, Validators.min(4), Validators.max(30)]],
      loanManagementFees: ['', [Validators.required, Validators.min(0), Validators.max(5000)]]
    });
  }

  onSubmit() {
    if (this.loanTypeForm.valid) {
      this.loanTypeService.createNewLoan(this.loanTypeForm.value).subscribe(()=>{
        alert("Loan Type created Successfully.");
        this.router.navigate(['admin/view-all-loans']);
      }
      );
    }
  }

}


