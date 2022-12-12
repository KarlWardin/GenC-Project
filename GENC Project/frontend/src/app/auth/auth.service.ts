import { Injectable, OnDestroy } from '@angular/core';
import { HttpClient } from "@angular/common/http"
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl: string = "http://localhost:8888/login";

  constructor(private http: HttpClient) { }

  login(user: User) {  // it will return jwt token as response
    return this.http.post(`${this.loginUrl}`, user, {
      responseType: 'text'
    });
  }
}
