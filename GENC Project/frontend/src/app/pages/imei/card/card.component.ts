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
  imeiNo: any;

  constructor(private deviceService: DeviceService) { }

  ngOnInit(): void {
  }

  getImeiNo() {
    this.deviceService.getImeiNo(this.mobileNo, this.accountNo).subscribe(
      (response) => {                           //next() callback
        this.imeiNo = response;
      },
      (error) => {                              //error() callback
        alert(error);
      },
      () => { });                                  //complete() callbac
  }

}
