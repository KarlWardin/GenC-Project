import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/auth.service';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userDetails: User = {
    accountNo: localStorage.getItem("mpCurrentAccNo") || "",
    password: ""
  }

  constructor(private authService: AuthService, private route: Router) { }

  ngOnInit(): void {
  }

  onLogin(userForm: NgForm) {
    this.userDetails.accountNo = userForm.value.accountNo;
    this.userDetails.password = userForm.value.password;
    console.log(this.userDetails);

    this.authService.login(this.userDetails);

    userForm.reset();
  }

  sendToHome() {
    this.route.navigateByUrl("/");
  }

}
