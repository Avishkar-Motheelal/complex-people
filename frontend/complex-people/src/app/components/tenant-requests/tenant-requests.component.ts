import { Component, OnInit } from '@angular/core';
import {IssuesModel} from "../../models/issues_model";
import {IssuesService} from "../../services/issues.service";

@Component({
  selector: 'app-tenant-requests',
  templateUrl: './tenant-requests.component.html',
  styleUrls: ['./tenant-requests.component.css']
})
export class TenantRequestsComponent {


  issues: IssuesModel[] = [];

  constructor(public issueService: IssuesService) {
  }


  ngOnInit() {
    this.getAllIssues();
  }

  getAllIssues() {
    this.issueService.getAllIssues().subscribe(
      {
        next: value => {
          this.issues = value;
          console.log(this.issues);
        },
        error: err => {
          console.log(err)
        }
      }
    )
  }
}
