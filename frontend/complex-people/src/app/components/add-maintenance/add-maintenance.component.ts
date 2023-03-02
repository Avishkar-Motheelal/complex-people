import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {IssuesModel} from "../../models/issues_model";
import {IssueHelper} from "../../helper/issue-help";
import {IssuesService} from "../../services/issues.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-maintenance',
  templateUrl: './add-maintenance.component.html',
  styleUrls: ['./add-maintenance.component.css']
})
export class AddMaintenanceComponent {
  maintenanceForm;

  issue: IssueHelper = new IssueHelper(0, '', '');

  constructor(private formBuilder: FormBuilder, private issueService: IssuesService,  private router: Router) {
    this.maintenanceForm = this.formBuilder.group({
      unit: ['', [Validators.required, Validators.minLength(1)]],
      description: ['', [Validators.required, Validators.minLength(1)]],
      type: ['', [Validators.required]]
    });
  }

  onSubmit() {

    this.issueService.saveNewIssue(this.issue).subscribe(
      {
        next: value => {
          let elementById = document.getElementById('form_error')!;
          elementById.innerText = "Saved contents";
          this.router.navigate(['/maintenance']);

        },
        error: err => {
          let elementById = document.getElementById('form_error')!;
          console.log(err);
          elementById.innerText = err.error.message;

        }
      }
    )

  }
}
