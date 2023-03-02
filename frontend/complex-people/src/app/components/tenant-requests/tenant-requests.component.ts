import {Component, OnInit} from '@angular/core';
import {IssuesModel} from "../../models/issues_model";
import {IssuesService} from "../../services/issues.service";
import {Router} from "@angular/router";
import {VisitorsService} from "../../services/visitors.service";

@Component({
  selector: 'app-tenant-requests',
  templateUrl: './tenant-requests.component.html',
  styleUrls: ['./tenant-requests.component.css']
})
export class TenantRequestsComponent {


  issues: IssuesModel[] = [];


  constructor(public issueService: IssuesService, private visitorsService: VisitorsService, private router: Router) {
  }


  ngOnInit() {
    this.getAllIssues();
  }


  getAllIssues() {
    this.issueService.getAllIssues().subscribe(
      {
        next: value => {
          this.issues = value;

        },
        error: err => {

        }
      }
    )
  }

  goTo(newTenant: string) {

    if (newTenant === 'NewTenant') {
      this.router.navigate(['/new-resident']);
    } else if (newTenant === 'NewApart') {
      this.router.navigate(['/new-apartment']);
    }

  }
}
