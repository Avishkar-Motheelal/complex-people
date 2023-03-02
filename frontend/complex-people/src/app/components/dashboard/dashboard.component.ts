import { Component } from '@angular/core';
import {Visit} from "../../models/visit.model";
import {VisitorsService} from "../../services/visitors.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  visitors: Visit[] = []

  visitor: Visit[] = [];

  apartmentNumber: number = 0;
  tenantsNumber: number = 0;
  requestsNumber: number = 0;

  constructor(private visitorsService: VisitorsService, private http: HttpClient) {
  }

  ngOnInit() {
    this.getVisitor();
  }

  getVisitor(): void {
    this.visitorsService.getVisitors().subscribe((visitors) => {
      console.log(visitors);  //Remember to remove this
      this.visitors = visitors.slice(0, 10);
      console.log(visitors);
      this.visitors.sort((a, b) => a.dateIn > b.dateIn ? 1 : -1);

    })
  }

  loginImg() {
    const url = "http://localhost:59078/api/image/login";
    return this.http.get(url, { responseType: 'blob' });
  }

}
