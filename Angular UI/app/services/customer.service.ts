import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoanType } from '../Model/LoanType';
import { Observable } from 'rxjs';
import { UserAuthService } from './user-auth.service';
import { Customer } from '../Model/Customer';
import { LoanApplicationRequest } from '../Model/LoanApplicationRequest';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient, private userAuthService: UserAuthService) { }

  baseUrl = 'http://localhost:8080/api';
  requestHeader = new HttpHeaders({"No-Auth":"True"});
  getAuthorizationHeader(): any {
    let token = this.userAuthService.getToken();
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return headers;
  }

  getAllLoanTypesCustomer(): Observable<LoanType[]> {
    const endpoint = this.baseUrl + '/customer/dashboard';
    let token = this.userAuthService.getToken();
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<LoanType[]>(endpoint, { headers });
  }
  getAllLoanTypes(): Observable<LoanType[]> {
    const endpoint = this.baseUrl + '/dashboard';
    return this.http.get<LoanType[]>(endpoint);
  }
  getCustomerById(customerId: number): Observable<Customer> {
    let token = this.userAuthService.getToken();
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    const endpoint = this.baseUrl + `/admin/viewCustomerDetails/${customerId}`;
    return this.http.get<Customer>(endpoint, { headers });
  }

  getAppliedLoans(customerId: number): Observable<any[]> {
    const headers = this.getAuthorizationHeader();
    return this.http.get<any[]>(this.baseUrl + '/customer/viewAllAppliedLoans/' + customerId, { headers });
  }

  getAllCustomers(): Observable<any[]> {
    let token = this.userAuthService.getToken();
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    const endpoint = this.baseUrl + '/admin/viewAllCustomers';
    return this.http.get<any[]>(endpoint, { headers });
  }

  applyForLoan(loanApplicationRequest: LoanApplicationRequest, file: File):Observable<boolean> {
    let formData = new FormData();
    console.log(loanApplicationRequest);
    console.log(file);
    formData.append('loanRequest', JSON.stringify(loanApplicationRequest));
    formData.append('file', file);
    console.log(formData);
    
    return this.http.post<boolean>(this.baseUrl + '/customer/loan-application/applyLoan', formData, {headers: this.getAuthorizationHeader()});
  }
  updateAppliedLoan(loanApplicationRequest: LoanApplicationRequest, file: File):Observable<any> {
    let formData = new FormData();
    console.log(loanApplicationRequest);
    console.log(file);
    console.log("hello");
    
    formData.append('loanRequest', JSON.stringify(loanApplicationRequest));
    formData.append('file', file);
    return this.http.post<boolean>(this.baseUrl + '/customer/update-loan', formData, {headers: this.getAuthorizationHeader()});
  }
  getUpdatedLoanApplication(loanId:number){
    console.log(" "+loanId);
    
    this.http.get(this.baseUrl+'/customer/cancel-applied-loan/'+ loanId,{headers:this.getAuthorizationHeader()}).subscribe(
      response => alert("cancelled loan application")
    );
  }
  calculateEMI(data1:any,data2:any,data3:any){
    console.log(""+data1+""+data2+""+data3);
    
    const url = `${this.baseUrl}/calculateEMI/${data1}/${data2}/${data3}`;
    // const headers=this.getAuthorizationHeader();
    return this.http.get(url,{headers:this.requestHeader});
  }

  updateAccount(customer:Customer):Observable<boolean>{
    console.log(customer);
    const headers = this.getAuthorizationHeader();
    return this.http.put<boolean>(this.baseUrl+'/customer/updateAccount/'+customer.customerId,customer,{headers});
  }
}