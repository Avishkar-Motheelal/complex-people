import {Component, Input} from '@angular/core';
import {Person} from "../../models/person.model";
import {ActivatedRoute, Router} from "@angular/router";
import {PeopleService} from "../../services/people.service";
import {AccessCard} from "../../models/access-card.model";
import {CardsService} from "../../services/cards.service";

import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AccountService} from "../../services/account.service";
import {AlertService} from "../../services/alert.service";
import {Observable} from "rxjs";
import {first} from "rxjs/operators";
import {HttpResponse} from "@angular/common/http";

@Component({
  selector: 'app-resident-details',
  templateUrl: './resident-details.component.html',
  styleUrls: ['./resident-details.component.css']
})
export class ResidentDetailsComponent {
  @Input() person?: Person;
  @Input() cards?: AccessCard[];

  constructor(
    private route: ActivatedRoute,
    private peopleService: PeopleService,
    private formBuilder: FormBuilder,
    private router: Router,
    private accountService: AccountService,
    private alertService: AlertService,
    private cardsService: CardsService,
  ) {
  }

  form!: FormGroup;
  loading = false;
  submitted = false;
  id = Number(this.route.snapshot.paramMap.get('id'));

  ngOnInit(): void {
    this.loadPerson();
    this.getCards();
    this.getPerson().pipe(first()).subscribe({
      next: (person: Person) => {
        this.form = this.formBuilder.group({
          firstName: [person.firstName, Validators.required],
          lastName: [person.lastName, Validators.required],
          phoneNumber: [person.phoneNumber, Validators.required],
          email: [person.emailAddress, Validators.required],
          type: [person.identificationDocumentType, Validators.required],
          number: [person.phoneNumber, Validators.required],
          role: ['RESIDENT'],
        });
      },
      error: error => {
        this.alertService.error(error);
        this.loading = false;
      }
    });
  }

  get formControls() {
    return this.form.controls;
  }

  getPerson(): Observable<Person> {
    return this.peopleService.getPerson(this.id)
  }

  loadPerson() {
    this.peopleService.getPerson(this.id).subscribe(person => {
      this.person = person
    });
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

    this.peopleService.updatePerson(this.form.value, this.id)
      .pipe(first())
      .subscribe({
        next: (response: HttpResponse<any>) => {
          if (response.status !== 200) {
            this.alertService.error("Failed to update. Please try again.");
            this.loading = false;
          } else {
            this.alertService.success('Added your details successfully', {keepAfterRouteChange: true});
            // this.router.navigate(["/dashboard"]);
          }
        },
        error: error => {
          this.alertService.error(error);
          this.loading = false;
        }
      });
  }

  getCards() {
    this.cards = [
      {
        accessCardId: '37d88e22-8f42-4f01-a37d-671a7086f454',
        activated: false,
      },
      {
        accessCardId: 'a4b63a63-79ec-4c19-9124-cfda4d4c0a4b',
        activated: true,
      },
    ];

    // for actual push
    const id = this.route.snapshot.paramMap.get('id');
    this.cardsService.getCardForPerson(id);
  }


}
