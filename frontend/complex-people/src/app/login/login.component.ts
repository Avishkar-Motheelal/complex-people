import {Component, OnInit} from '@angular/core';
import {CredentialResponse} from "google-one-tap";
import {environment} from "../../environments/environment";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {User} from "../models/user";
import {first} from "rxjs/operators";
import {HttpResponse} from "@angular/common/http";
import {AccountService} from "../services/account.service";
import {AlertService} from "../services/alert.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor(private router: Router,
              private route: ActivatedRoute,
              private accountService: AccountService,
              private formBuilder: FormBuilder,
              private alertService: AlertService) {
  }

  private clientId = environment.clientId
  form!: FormGroup;
  submitted = false;
  loading = false;

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });

    // @ts-ignore
    window.onGoogleLibraryLoad = () => {
      // @ts-ignore
      google.accounts.id.initialize({
        client_id: this.clientId,
        callback: this.handleCredentialResponse.bind(this),
        auto_select: false,
        cancel_on_tap_outside: true
      });
      // @ts-ignore
      google.accounts.id.renderButton(
        // @ts-ignore
        document.getElementById("buttonDiv"),
        {theme: "outline", size: "large", width: "50%"}
      );
      // @ts-ignore
      // google.accounts.id.prompt((notification: PromptMomentNotification) => {
      // });
    };
  }

  async handleCredentialResponse(response: CredentialResponse) {
    this.accountService.LoginWithGoogle(response.credential).subscribe(
      (response: HttpResponse<any>) => {
        console.log(response);
        let user: User = response.body;
        let responseCode = response.status;
        localStorage.setItem("user", JSON.stringify(user));

        if (responseCode === 200) {
          this.router.navigate(['/dashboard']);
        } else if (responseCode === 201) {
          this.router.navigate(['/account/details']);
        }
      },
      (error: any) => {
        console.log(error);
      }
    );
  }

  get formControls() { // @ts-ignore
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
    this.accountService.login(this.formControls['email'].value, this.formControls['password'].value)
      .pipe(first())
      .subscribe({
        next: () => {
          const returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/dashboard';
          this.router.navigateByUrl(returnUrl);
        },
        error: error => {
          this.alertService.error(error);
          this.loading = false;
        }
      });
  }

  // async handleCredentialResponse(response: CredentialResponse) {
  //   await this.accountService.LoginWithGoogle(response.credential).subscribe(
  //     (x:any) => {
  //       console.log(x);
  //       localStorage.setItem("token", x.jwttoken);
  //       // this._ngZone.run(() => {
  //       //   this.router.navigate(['/logout']);
  //       // })
  //       },
  //     (error:any) => {
  //       console.log(error);
  //     }
  //   );
  // }
  // get formControls() { // @ts-ignore
  //   return this.authForm.controls; }
  //
  // onSubmit(){
  //   this.isSubmitted = true;
  //   // @ts-ignore
  //   if(this.authForm.invalid){
  //     return;
  //   }
  //   // @ts-ignore
  //   this.authService.signIn(this.authForm.value);
  //   // this.router.navigateByUrl('/admin');
  // }
}
