import { Injectable } from '@angular/core';
import { Customer } from '../Model/Customer';
import { Admin } from '../Model/admin';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  constructor() { }

  public setRole(role: string) {
    localStorage.setItem("role",role);
  }

  public getRole(){
    return localStorage.getItem("role");
  }

  public setToken(token:string) {
    localStorage.setItem("token",token);
  }

  public getToken(): string {
    return localStorage.getItem("token") ?? '';
  }  

  public clearToken(){
    localStorage.clear();
  }

  public isLoggedIn(): boolean {
    const token = this.getToken();
    const role = this.getRole();
    return token !== null && role !== null;
  }
  public setCustomer(customer:Customer){
    localStorage.setItem('customer',JSON.stringify(customer));
  }

  public getCustomer():any{
    const customer = localStorage.getItem('customer');
    if(customer == null){
      throw new Error('customer not found');
    }
    return JSON.parse(customer);
  }
  public setAdmin(admin:Admin){
    localStorage.setItem('admin',JSON.stringify(admin));
  }

  public getAdmin():any{
    const admin = localStorage.getItem('admin');
    if(admin == null){
      throw new Error('customer not found');
    }
    return JSON.parse(admin);
  }
  
}
