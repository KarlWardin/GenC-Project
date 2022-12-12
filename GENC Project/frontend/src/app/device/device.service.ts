import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DeviceService {

  private deviceApi: string = "http://localhost:8080/";

  constructor(private http: HttpClient) { }

  getImeiNo(mobileNo: string, accountNo: string): Observable<any> {
    var reqHeader = new HttpHeaders({
      'Content-Type': 'text',
      'Authorization': 'Bearer ' + localStorage.getItem('mpCurrentToken')
    });
    return this.http.get(this.deviceApi + `getImeiNo?mobileNo=` + mobileNo + `&accountNo=` + accountNo, {
      headers: reqHeader,
      responseType: 'text'
    });
  }


  getMobileNO(accountNo: string): Observable<any> {
    var reqHeader = new HttpHeaders({
      'Content-Type': 'text',
      'Authorization': 'Bearer ' + localStorage.getItem('mpCurrentToken')
    });
    return this.http.get(this.deviceApi + `getMobileNo?accountNo=` + accountNo, { headers: reqHeader });
  }
}

