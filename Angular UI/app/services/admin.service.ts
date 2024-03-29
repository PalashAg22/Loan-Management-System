import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserAuthService } from './user-auth.service';
import { Admin } from '../Model/admin';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http:HttpClient,private userAuthService:UserAuthService) { }
  PATH_OF_API:string="http://localhost:8080/api/admin";
  public updateAccount(admin:any){
    let token = this.userAuthService.getToken();
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    const url = this.PATH_OF_API+'/update-account';
    return this.http.put(url,admin, {headers});
  }
  
  createAdmin(admin:Admin) {
    let token = this.userAuthService.getToken();
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    const url = this.PATH_OF_API+'/createNewAdmin';
    return this.http.post(url,admin, {headers});
  }
  getAuthorizationToken():any{
    let token = this.userAuthService.getToken();
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return headers;
  }

  getAdmin():Observable<Admin[]>{
    const token = this.userAuthService.getToken();
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`)
    return this.http.get<Admin[]>(this.PATH_OF_API+'/viewAllAdmins',{headers})
  }
  
  downloadFile(fileName:string,propertyProofId: number) {
    const headers = this.getAuthorizationToken();
    this.http.get(this.PATH_OF_API+'/property-file/'+propertyProofId, { headers, responseType: 'blob' }).subscribe(
      res =>{
        this.downloadFileHelper(res,fileName);
      }
    );
}
downloadCustomerIdFile(customerProofId: number, idProofName: string){
  alert(customerProofId);
  
  const headers = this.getAuthorizationToken();
    this.http.get(`${this.PATH_OF_API}/customerIdProof/${customerProofId}`, { headers, responseType: 'blob' }).subscribe(
      res => {
        this.downloadFileHelper(res,idProofName);
      }
    );
}
downloadFileHelper(res:any,idProofName:string){
  const blob = new Blob([res], { type: 'application/octet-stream' });
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        document.body.appendChild(a);
        a.style.display = 'none';
        a.href = url;
        a.download = idProofName;
        a.click();
        window.URL.revokeObjectURL(url);
}
}
