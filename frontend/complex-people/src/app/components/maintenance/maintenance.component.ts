import { Component } from '@angular/core';
import {IssuesModel} from "../../models/issues_model";
import {IssuesService} from "../../services/issues.service";
import {HidecomponentService} from "../../services/hidecomponent.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-maintenance',
  templateUrl: './maintenance.component.html',
  styleUrls: ['./maintenance.component.css']
})
export class MaintenanceComponent {
  issues: IssuesModel[] = [];

  formHidden: boolean = true;

  constructor(private issueService: IssuesService, private sharedService: HidecomponentService, private router: Router) {
    this.sharedService.myMethod$.subscribe(
      (data) => {this.formHidden = data}
    )

  }

  ngOnInit() {
    this.getAllIssues();
  }

  goToForm() {
    this.router.navigate(['/new']);
  }

  updateForm(event: any, issue: IssuesModel) {
    event.preventDefault();
    this.formHidden = false;
    this.sharedService.myMethod(this.formHidden);
    this.sharedService.issueMethod(issue);

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
