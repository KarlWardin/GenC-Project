import { Component, Input, OnInit } from '@angular/core';
import { DeviceService } from 'src/app/device/device.service';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  @Input() mobileNo = '';
  @Input() accountNo = '';
  imeiNo: string = "Get IMEI No";

  constructor(private deviceService: DeviceService) { }

  ngOnInit(): void {
  }

  getImeiNo() {
    this.deviceService.getImeiNo(this.mobileNo, this.accountNo).subscribe(
      (response) => {                           //next() callback
        this.imeiNo = response;
      },
      (error) => {                              //error() callback
        alert("Some problem occured. Please recheck if you are logged in !!");
        console.log(error);
      },
      () => { });                                  //complete() callbac
  }

}
