import { Component } from '@angular/core';
import {Visit} from "../../models/visit.model";
import {VisitorsService} from "../../services/visitors.service";
import {HttpClient} from "@angular/common/http";
import {ApartmentsService} from "../../services/apartments.service";
import {IssuesService} from "../../services/issues.service";
import {PeopleService} from "../../services/people.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  visitors: Visit[] = []


  apartmentNumber: number = 0;
  tenantsNumber: number = 0;
  requestsNumber: number = 0;

  constructor(private visitorsService: VisitorsService,
              private apartmentService: ApartmentsService,
              private tenantService: PeopleService,
              private issueService: IssuesService,
              private http: HttpClient) {
  }

  ngOnInit() {
    this.getVisitor();
    this.getApartmentCount();
    this.getResidentCount();
    this.getIssues();
  }

  getApartmentCount() {
    this.apartmentService.getApartments()
      .subscribe(value => {
        this.apartmentNumber = value.length;
      });
  }

  getResidentCount() {
    this.tenantService.getPeople().subscribe(
      {
        next: value => {
          this.tenantsNumber = value.length;
        },
        error: err => {}
      }
    )
  }

  getIssues() {
    this.issueService.getAllIssues().subscribe(
      {
        next: value => {
          this.requestsNumber = value.length;
        },
        error: err => {}
      }
    )
  }

  getVisitor(): void {
    this.visitorsService.getVisitors().subscribe((visitors) => {
      this.visitors = visitors.slice(0, 10);
      this.visitors.sort((a, b) => a.dateIn > b.dateIn ? 1 : -1);

    })
  }

  loginImg() {
    const url = "http://localhost:59078/api/image/login";
    return this.http.get(url, { responseType: 'blob' });
  }

}
