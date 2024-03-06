import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoanType } from '../Model/LoanType';
import { UserAuthService } from './user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class LoanTypeService {
  
  PATH_OF_API:string="http://localhost:8080/api/admin";
  constructor(private http: HttpClient,private userAuthService:UserAuthService) { }

  getAuthorization(){
    const token = this.userAuthService.getToken();
    let headers = new HttpHeaders().set('Authorization',`Bearer ${token}`)
    return headers;
  }
  getLoanTypes(): Observable<any[]> {
    let token = this.userAuthService.getToken();
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<any[]>(this.PATH_OF_API+'/allLoanTypes',{headers});
  }
  createNewLoan(loan:LoanType):Observable<any>{
    let token = this.userAuthService.getToken();
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    const url = this.PATH_OF_API+'/createLoanType';
    return this.http.post(url,loan, {headers});
  }

  updateLoanType(loan:LoanType):Observable<LoanType>{
   const headers = this.getAuthorization();
     return this.http.put<LoanType>(this.PATH_OF_API+'/updateLoanType', loan,{headers});
     
  }
}
