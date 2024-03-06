import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Customer } from '../Model/Customer';
import { CustomerService } from '../services/customer.service';
import { AdminService } from '../services/admin.service';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.css']
})
export class CustomerDetailsComponent {
  customer: Customer = new Customer();

  constructor(
    private route: ActivatedRoute,
    private customerService: CustomerService,
    private adminService:AdminService
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const customerId = +params['customerId'];
      this.loadCustomer(customerId);
    });
  }

  loadCustomer(customerId: number): void {
    this.customerService.getCustomerById(customerId)
      .subscribe((customer) => {
        this.customer = customer;
        this.customer.fullName = `${customer.customerFirstName} ${customer.customerLastName}`;
        this.customer.age = this.calculateAge(customer.dateOfBirth);
        this.customer.fullAddress = `${customer.address}, ${customer.state}, ${customer.country}`;
      });
  }

  calculateAge(dateOfBirth: Date): number {
    const today = new Date();
    const birthDate = new Date(dateOfBirth);
    let age = today.getFullYear() - birthDate.getFullYear();
    const monthDiff = today.getMonth() - birthDate.getMonth();
    if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthDate.getDate())) {
      age--;
    }
    return age;
  }
  downloadCustomerIdFile(customerProofId:number,idProofName:string){
    this.adminService.downloadCustomerIdFile(customerProofId,idProofName);
  }
}
