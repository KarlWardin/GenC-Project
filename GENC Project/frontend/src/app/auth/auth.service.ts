import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http"
import { User } from '../model/user';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl: string = "http://localhost:8888/login";

  constructor(private http: HttpClient, private route: Router) { }

  login(user: User) {  // it will return jwt token as response
    localStorage.setItem("mpCurrentAccNo", user.accountNo);
    this.http.post(`${this.loginUrl}`, user, {
      responseType: 'text'
    }).subscribe({
      next: data => {
        console.log(data);
        if (data.startsWith('ey')) {
          localStorage.setItem("mpCurrentToken", data);
          this.route.navigate(['/']);
        } else {
          alert("Something went wrong !!");
        }
      },
      error: error => {
        console.error(error);
        alert("Log In failed . Please Try Again !!");
      }
    });

  }
}
