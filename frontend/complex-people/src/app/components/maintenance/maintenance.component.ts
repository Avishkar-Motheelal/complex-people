import { Component } from '@angular/core';
import {IssuesModel} from "../../models/issues_model";
import {IssuesService} from "../../services/issues.service";

@Component({
  selector: 'app-maintenance',
  templateUrl: './maintenance.component.html',
  styleUrls: ['./maintenance.component.css']
})
export class MaintenanceComponent {
  issues: IssuesModel[] = [];


  constructor(private issueService: IssuesService) {}

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
