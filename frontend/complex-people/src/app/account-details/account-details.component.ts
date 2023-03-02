import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {first} from "rxjs/operators";
import {HttpResponse} from "@angular/common/http";
import {User} from "../models/user";
import {AddPerson} from "../models/addPerson";
import {AccountService} from "../services/account.service";
import {AlertService} from "../services/alert.service";

@Component({
  selector: 'app-account-details',
  templateUrl: './account-details.component.html',
  styleUrls: ['./account-details.component.css']
})
export class AccountDetailsComponent implements OnInit {
  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private accountService: AccountService,
              private alertService: AlertService) {
  }

  form!: FormGroup;
  loading = false;
  submitted = false;

  ngOnInit() {
    this.form = this.formBuilder.group({
      email: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      type: ['', Validators.required],
      number: ['', Validators.required],
      role: ['VISITOR'],
    });
  }

  get formControls() {
    return this.form.controls;
  }

  onSubmit() {
    this.submitted = true;

    // reset alerts on submit
    this.alertService.clear();

    // stop here if form is invalid
    if (this.form.invalid) {
      return;
    }

    this.loading = true;

    this.accountService.addPerson(this.form.value)
      .pipe(first())
      .subscribe({
        next: (response: HttpResponse<any>) => {
          if (response.status !== 200) {
            this.alertService.error("Failed to add your details, you likely already have an account");
            this.loading = false;
          } else {
            this.alertService.success('Added your details successfully', {keepAfterRouteChange: true});
            this.router.navigateByUrl("/home");
          }
        },
        error: error => {
          this.alertService.error(error);
          this.loading = false;
        }
      });
  }

}
