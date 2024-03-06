import { Component } from '@angular/core';
import { UserAuthService } from './services/user-auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Final_Project';
  constructor(private userAuthService:UserAuthService){}
  
  image='';
  bgUser='/assets/image/Loan.jpg';
  // bgCustomer='/assets/image/bgimg.jpg';
  // bgUser='/assets/image/bg.jpg';
   role:any=this.userAuthService.getRole();
  ngOnInit(){
    this.image = this.bgUser;
  }
}
