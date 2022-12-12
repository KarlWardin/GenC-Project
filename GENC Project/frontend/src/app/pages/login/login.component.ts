import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Subject, Subscription, takeUntil } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnDestroy {
  userDetails: User = {
    accountNo: localStorage.getItem("mpCurrentAccNo") || "",
    password: ""
  }
  componentDestroyed$: Subject<boolean> = new Subject()

  constructor(private authService: AuthService, private route: Router) { }

  ngOnDestroy(): void {
    this.componentDestroyed$.next(true)
    this.componentDestroyed$.complete()
  }

  onLogin(userForm: NgForm) {

    this.userDetails.accountNo = userForm.value.accountNo;
    this.userDetails.password = userForm.value.password;
    console.log(this.userDetails);

    localStorage.setItem("mpCurrentAccNo", this.userDetails.accountNo);

    this.authService.login(this.userDetails).pipe(takeUntil(this.componentDestroyed$)).subscribe({
      next: data => {
        console.log(data);
        if (data.startsWith('ey')) {
          localStorage.setItem("mpCurrentToken", data);
          this.sendToHome();
        } else {
          alert("Something went wrong !!");
        }
      },
      error: error => {
        console.error(error);
        alert("Log In failed . Please Try Again !!");
      },
      complete: () => { }
    });
    userForm.reset();
  }

  sendToHome() {
    this.route.navigate(["/"]);
  }

}
